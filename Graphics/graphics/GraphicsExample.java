package graphics;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GraphicsExample {


    final int WIDTH = 600;
    final int HEIGHT = 600;
    final int BUTTONHEIGHT = 50;
    int squareX = 100;
    int squareY = 100;

    boolean drawRectangle = false;

    public void draw(Graphics g) {
        
        if (drawRectangle) {
            Color c = new Color(250, 0, 0);
            g.setColor(c);
            g.fillRect(squareX, squareY, 50, 50);
        }
    }

    public void startGraphics() {
        
        JFrame frame = new JFrame("Graphics Example");

        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel canvas = new JPanel() {

            @Override
            public void paint(Graphics g) {

                draw(g);
            }

        };

        canvas.setSize(new Dimension(WIDTH, HEIGHT - BUTTONHEIGHT));

        JPanel container = new JPanel();

        JButton button = new JButton("Draw Rectangle");

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                drawRectangle = !drawRectangle; 

                frame.getContentPane().repaint();
                
            }
            
        });
        

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                squareX = e.getX();
                squareY = e.getY();
                
                frame.getContentPane().repaint();
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

        });

        container.add(canvas);
        container.add(button);
        frame.add(container);

        frame.setVisible(true);
        

    } 

    public static void main (String[] args) {
        new GraphicsExample().startGraphics();
    }
    
}



