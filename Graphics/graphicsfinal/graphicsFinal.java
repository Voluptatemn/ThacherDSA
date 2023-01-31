package graphicsfinal;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class graphicsFinal {
    

    // the setup of the canvas 
    final int WIDTH = 600; //canvas height
    final int HEIGHT = 700; //canvas width
    final int BUTTONHEIGHT = 40; //buttonheight for the containers and layouts
    ArrayList<ArrayList<shape>> shape_history = new ArrayList<ArrayList<shape>>(); //a list of the history of th lists of the shapes 
    String status = "Rectangle"; // the initial status 
    String Last_button_pressed = "Rectangle";  //a string to record the current shape to draw, mainly used so to remember the status after setting the status to resize after adding a shape
    Color c = new Color(0, 0, 0); //a color to record the current color to draw
    boolean was_Move = false; // the condition to test if an object was moved after the mouse realsed 
    boolean movable = false;  // the condition to prevent random movings
    boolean was_Resize = false; // the condition to see if an object was recently resized
    boolean resiable = false; // the condition to prevent random resizing
    int historyIndex = 0; // history index for the redo-undo feature
    int width = 30; // the beginning width for all shapes 
    int height = 10; // the beginning height for all shapes

    

    public void save() { // save function for redo and undo: saving the current status 
        ArrayList<shape> copy_shapes = new ArrayList<shape>(); // making a deep copy of the current status 
        for (int i = 0; i < shape_history.get(historyIndex).size(); i++) { // loop through the shape list to copy all the shapes
            copy_shapes.add(shape_history.get(historyIndex).get(i).history_copy());
        }
        historyIndex++; 
        shape_history.add(historyIndex, copy_shapes); // add the copyed shape list to the shape history list 
    }

    public void delete() { // delete function for deleting the histories after adding an object or making a change after undo 
        for (int i = 0; i < shape_history.size(); i++) {
            if (i > historyIndex) {
                shape_history.remove(i); // removing any shape history after the current one 
                i--;
            }
        }
    }
    

    public void draw(Graphics g) { //method to draw out the shapes in the lists 

    
        for (int i = 0; i < shape_history.get(historyIndex).size(); i++) {
    
            shape_history.get(historyIndex).get(i).draw(g);
        }
    
    }

    public void startGraphics() {

        ArrayList<shape> shapes = new ArrayList<shape>(); // saving the beginning blank to the history 
        shape_history.add(shapes); 
        
        JFrame frame = new JFrame("Graphics grapher"); // title 

        frame.setSize(WIDTH, HEIGHT); 
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //standard initialization of the frame to work with

        JPanel container = new JPanel(); // the overall container of everything
        JPanel canvas = new JPanel() { // the canvas to present the shapes

            @Override
            public void paint(Graphics g) { // canvas draw 
                
                draw(g);
            }

        };

        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT - 2 * BUTTONHEIGHT)); // setting the size of the canvas, adjust with the number of button rows

        JPanel containerButton1 = new JPanel(); // the container for the buttons for the shapes, will include rectangle, circle, line, text

        JButton rectangleButton = new JButton("Rectangle"); // the button for rectangle
        rectangleButton.addActionListener(new ActionListener() { // the actionlistener that will set the shape status string to rectangle, the cirlce actionlistener serves the same purpose

            @Override
            public void actionPerformed(ActionEvent e) {
                
                status = "Rectangle";
                Last_button_pressed = status; 
                
            }
            
        });

        JButton circleButton = new JButton("Circle"); // button for circle
        circleButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                status = "Circle"; 
                Last_button_pressed = status; 
            
            }

            
        }); 
        
        JButton line = new JButton("Line"); // button for line 
        line.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //to do 
                status = "Line";
                Last_button_pressed = status;  
                
            
            }

            
        }); 

        JButton polygon = new JButton("Triangle"); // button for triangle 
        polygon.addActionListener(new ActionListener() { 

            @Override
            public void actionPerformed(ActionEvent e) { 
                // to do
                status = "Triangle";
                Last_button_pressed = status; 
                
            }

            
        });

        JButton text = new JButton("text"); // button for text
        text.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //to do 
                
                status = "Text";
            
            }

            
        }); 


        JPanel utilityPanel = new JPanel(); // the container for all the utility buttons, including delete, choose color, clear, undo, redo 

        JButton clear = new JButton("Clear"); // button for clearing the entire canvas
        clear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) { // just clearing the entire canvas by reinitialize the list of shapes, all the utility button needs to repaint except for choosing color 

                save();
                ArrayList<shape> shapes = new ArrayList<shape>(); // add a blank canvas
                shape_history.add(historyIndex, shapes);
                delete();
            
                frame.getContentPane().repaint();
                
                
            }
            
        });

        JButton undo = new JButton("Undo"); // button for unding the last action 
        undo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (historyIndex < 1) { // when there is no more histories, prevent undo 
                    System.out.println("No more undos.");
                } else {
                    historyIndex--; // remove the index of the current back one 
                    frame.getContentPane().repaint(); 
                }
                
                
            }
            
        });
        
        JButton choosingColor = new JButton("Color"); // choosing the color of the shapes, using jcolorchooser
        choosingColor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Color initialcolor = Color.RED;    
                c = JColorChooser.showDialog(choosingColor, "Choose a color", initialcolor); 
                
            }

            
        });

        JButton delete = new JButton("Delete"); // delete the selected shape
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // to do
                status = "Delete";
                Last_button_pressed = status; 
                
            }

            
        });

        JButton move = new JButton("Move"); // move the selected shape
        move.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                status = "Move";
                Last_button_pressed = status; 
                
            }

            
        });

        JButton resize = new JButton("Resize"); // resize the shape
        resize.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // to do
                status = "Resize";
                Last_button_pressed = status; 
                
            }

            
        });

        JButton copy = new JButton("Copy"); // copy existing graphs
        copy.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // to do
                status = "Copy";
                Last_button_pressed = status; 
                
            }

            
        });

        JButton redo = new JButton("Redo"); // redo the last undoed action 
        redo.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (historyIndex == shape_history.size() - 1) { // move the history index forward 1  
                    System.out.println("No more redos");
                } else {
                    historyIndex++;
                    frame.getContentPane().repaint(); 
                }

            }
        });

        // need to change the entire drawing situation to drag

        canvas.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                
                
            }

            @Override
            public void mousePressed(MouseEvent e) { // actions do when the mouse is clicked based on the status 
            

                System.out.println(status); 

                if (status == "Rectangle") { // adding a rectangle 
                    
                    save(); // save first and then add the rectangle to the newly saved shape history, save first then change, all the save functions follows 
                    Rectangle new_rectangle = new Rectangle(e.getX(), e.getY(), c, width, height); // creating new shapes, circle, triangle, text follows
                    shape_history.get(historyIndex).add(new_rectangle); 
                    status = "Resize"; // setting the status to resize so that the user can resize the shape after the input 
                    resiable = true; 
                    delete(); // delete the histories after this history in case of the action is performed after an undo, all adding follows 
                    
                     
                } else if (status == "Circle") { // adding a circle 

                    save();
                    Circle new_Circle = new Circle(e.getX(), e.getY(), c, width, height); 
                    shape_history.get(historyIndex).add(new_Circle);
                    status = "Resize";
                    resiable = true; 
                    delete();
                    

                } else if (status == "Line") { // adding a line 
                    
                    save();
                    Line new_Line = new Line(e.getX(), e.getY(), c, width, height, e.getX(), e.getY());
                    shape_history.get(historyIndex).add(new_Line);
                    status = "Resize";
                    resiable = true; 
                    delete();
                    

                } else if (status == "Delete") { // delete the shape 
                    for (int i = 0; i < shape_history.get(historyIndex).size(); i++) {
                        if (shape_history.get(historyIndex).get(shape_history.get(historyIndex).size() - i - 1).isON(e.getX(), e.getY())) {
                            save(); // save before change 
                            shape_history.get(historyIndex).remove(shape_history.get(historyIndex).size() - i - 1); // delete the choosen shape from the list 
                            i--; 
                            delete();
                            break;
                        }
                    }
                } else if (status == "Move") { // moving an object
                    for (int i = 0; i < shape_history.get(historyIndex).size(); i++) {
                        if (shape_history.get(historyIndex).get(i).isON(e.getX(), e.getY())) {
                            save(); 
                            shape_history.get(historyIndex).add(shape_history.get(historyIndex).size(), shape_history.get(historyIndex).get(i)); // move the selected shape to the last of the list so that the mouse dragged function have a secure target 
                            shape_history.get(historyIndex).remove(i); // since there is a copy at the last of the list, delete the original one 
                            movable = true; // set the condition to true
                            break;
                        }
                    }
                } else if (status == "Copy") { // copy an existing shape 
                    for (int i = 0; i < shape_history.get(historyIndex).size(); i++) {
                        if (shape_history.get(historyIndex).get(shape_history.get(historyIndex).size() - 1 - i).isON(e.getX(), e.getY())) {
                            save();
                            shape_history.get(historyIndex).add(shape_history.get(historyIndex).get(shape_history.get(historyIndex).size() - i - 1).copy(WIDTH, HEIGHT)); // use the copy function of each shape 
                            delete(); 
                            break;
                        }
                    }
                } else if (status == "Triangle") { // add a triangle 
                    
                    save();
                    Triangle new_Triangle = new Triangle(e.getX(), e.getY(), c, width, height);
                    shape_history.get(historyIndex).add(new_Triangle);
                    status = "Resize";
                    resiable = true; 
                    delete();

                } else if (status == "Text") { // add the text 
                    try {  // using a try and catch because the user might not input anything and cause errors 

                        save();
                        String in = JOptionPane.showInputDialog("Enter: "); // asking the user the string to print on the canvas 
                        Text new_text = new Text(e.getX(), e.getY(), c, width, height, in);
                        shape_history.get(historyIndex).add(new_text);
                        delete();

                    } catch (Exception exc){
                        System.out.println(exc);
                    }
                    
                } else if (status == "Resize") { // resize a shape 
                    for (int i = 0; i < shape_history.get(historyIndex).size(); i++) {
                        if (shape_history.get(historyIndex).get(i).isON(e.getX(), e.getY())) {
                            save();
                            shape_history.get(historyIndex).add(shape_history.get(historyIndex).size(), shape_history.get(historyIndex).get(i)); // similar logic with move, moving the shape to the last place of the list so the mouse dragged function can target it
                            shape_history.get(historyIndex).remove(i); 
                            resiable = true; // set the resize condition to be true
                            break;
                        }
                    }
                } else if (status == "") { // if by any chance that this happens, ask the user to choose an action to perform 
                    System.out.println("No action choosen.");
                }

                frame.getContentPane().repaint();
            
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                status = Last_button_pressed; // change the status back to the last button pressed , if was moved, it can change it back to the shape it was 
                if (was_Move) { 
                    delete(); // delete history after a change in case of undo 
                    movable = false; // set the movable to be false, so that no shapes will be randomly moving 
                } else if (was_Resize) {
                    delete(); // same logic as the was_move check 
                    resiable = false; 
                }
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


        canvas.addMouseMotionListener(new MouseMotionListener(){

            

            @Override
            public void mouseDragged(MouseEvent e) {
                // TODO Auto-generated method stub


                if (status == "Move" && movable) {
                    shape_history.get(historyIndex).get(shape_history.get(historyIndex).size() - 1).move(e.getX(), e.getY()); // moving the object using the move method in the object 
                    was_Move = true;
                } else if (status == "Delete") { // delete and copy is in case the status is in these two and random dragging would cause actions to be performed 
                    frame.getContentPane().repaint();
                } else if (status == "Copy") {
                    frame.getContentPane().repaint();
                } else if (status == "Resize" && resiable) { 
                    shape_history.get(historyIndex).get(shape_history.get(historyIndex).size() - 1).resize(e.getX(), e.getY()); // using the resize function of each shape 
                    was_Resize = true;
                } 

                frame.getContentPane().repaint();
                
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

        });
      
        // adding all the buttons to the canvas 
        containerButton1.add(rectangleButton);
        containerButton1.add(circleButton);
        containerButton1.add(line);
        containerButton1.add(text); 
        containerButton1.add(polygon);
        containerButton1.add(resize); 

        containerButton1.setPreferredSize(new Dimension(WIDTH, BUTTONHEIGHT));

        utilityPanel.add(clear);
        utilityPanel.add(undo);
        utilityPanel.add(redo);
        utilityPanel.add(choosingColor);

        // create a square to present the color for the user

        utilityPanel.add(delete); 
        utilityPanel.add(move);
        utilityPanel.add(copy);

        utilityPanel.setPreferredSize(new Dimension(WIDTH, BUTTONHEIGHT));

        container.add(canvas); 
        container.add(containerButton1);
        container.add(utilityPanel);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        
        frame.add(container);
        frame.setVisible(true);

        

    }

    public static void main (String[] args) { // run the entire thing 
        new graphicsFinal().startGraphics(); 
    }

}


