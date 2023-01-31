package graphics;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Text {

    final int WIDTH = 600;
    final int HEIGHT = 600;
    final int BUTTONHEIGHT = 50;
    String text = "";


    public void startGraphics() {

        try {
            BufferedReader in = new BufferedReader(new FileReader("text.txt"));
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                text += line;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        JFrame frame = new JFrame("Text");

        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea textBox = new JTextArea();

        textBox.setSize(new Dimension(WIDTH, HEIGHT - BUTTONHEIGHT));

        textBox.setText(text);

        textBox.setEditable(false);

        JPanel container = new JPanel();

        JButton button = new JButton("Word Count");

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int wordNumber = 0;
                
                for(int i = 0; i < text.length(); i++) {
                    char word = text.charAt(i);
                    if (word != ' ') {
                        wordNumber ++;
                    }
                }

                String output = "The number of this text is " + wordNumber;
    
                textBox.setText(textBox.getText() + "\n" + output); 
            }
            
        });

        JButton button2 = new JButton("Replace L");

        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String modified = "";
                
                for (int i = 0; i < text.length(); i++) {
                    if (text.charAt(i) == 'l' || text.charAt(i) == 'L') {
                        modified += "a";
                    } else {
                        char word = text.charAt(i);
                        modified += word; 
                    }
                }

                textBox.setText(textBox.getText() + "\n" + modified); 

                
            }

            

        });

        JPanel buttonContainer = new JPanel();

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(textBox);
        buttonContainer.add(button);
        buttonContainer.add(button2);
        container.add(buttonContainer);

        frame.add(container);

        frame.setVisible(true);

    }

        
    public static void main (String[] args) {
        new Text().startGraphics();

        


        
    }
    
}; 
