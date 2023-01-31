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
import java.util.ArrayList;

public class GraphicInputRectangles {

    final int WIDTH = 600;
    final int HEIGHT = 600;
    final int BUTTONHEIGHT = 50;
    int radious = 50;

    ArrayList squareXList = new ArrayList<Integer>();
    ArrayList squareYList = new ArrayList<Integer>();
    

    public void draw(Graphics g) {

        Color c = new Color(250, 0, 0);

        for (int i = 0; i < squareXList.size(); i++){
            int squareX = (int) squareXList.get(i);
            int squareY = (int) squareYList.get(i);
            g.setColor(c);
            g.fillOval(squareX, squareY, radious, radious);
        }
    
    }


    public void startGraphics() {
        
        JFrame frame = new JFrame("Draw Rectangles");

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

        JButton button = new JButton("Clear Circles");

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                squareXList = new ArrayList<Integer>();
                squareYList = new ArrayList<Integer>();
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
                int squareX = e.getX();
                int squareY = e.getY();
                squareXList.add(squareX);
                squareYList.add(squareY); 

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
        new GraphicInputRectangles().startGraphics();
    }
    
}; 