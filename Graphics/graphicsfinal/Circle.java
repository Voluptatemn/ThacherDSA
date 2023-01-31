package graphicsfinal;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends shape{


    public Circle(int x, int y, Color c, int w, int h) {
        super(x, y, c, w, h);
    }

    @Override
    public void draw(Graphics g) { // This method takes in a Graphics object as a parameter and is used to draw the circle on the screen. It sets the color of the circle and fills an oval with the given x, y, width, and height.
        g.setColor(c);
        g.fillOval(x, y, width, height);
        
    }

    @Override
    public boolean isON(int mouseX, int mouseY) { // This method takes in the x and y coordinates of the mouse and checks if the mouse is inside the circle. It calculates the distance of the mouse from the center of the circle and compares it to the radius. If the mouse is inside the circle, it returns true, otherwise it returns false.
        // TODO Auto-generated method stub
        double centerX = x + width / 2;
        double centerY = y + height / 2;
        double result = Math.pow(mouseX - centerX, 2) / Math.pow(width / 2, 2) + Math.pow(mouseY - centerY, 2) / Math.pow(height / 2, 2);
        if (result <= 1) {
            return true;
        }

        return false;
    }

    @Override
    public void resize(int mouseX, int mouseY) { // This method takes in the x and y coordinates of the mouse and is used to resize the circle. It resizes the circle based on the position of the mouse and the initial position of the circle.
        // TODO Auto-generated method stub

        if (mouseX > initialX && mouseY > initialY) {

            width = mouseX - x;
            height = mouseY - y; 

        } else if (mouseX < initialX && mouseY > initialY) {
            
            x = mouseX;
            width = initialX - mouseX;
            height = mouseY - initialY;

        } else if (mouseX > initialX && mouseY < initialY) {

            width = mouseX - x;
            y = mouseY;
            height = initialY - mouseY;

        } else if (mouseX < initialX && mouseY < initialY) {
            
            x = mouseX;
            y = mouseY;
            width = initialX - mouseX;
            height = initialY - mouseY; 

        }
        
    }

    @Override
    public shape copy(int WIDTH, int HEIGHT) { // This method creates a copy of the circle and returns it.
        // TODO Auto-generated method stub
        Circle copy_circle = new Circle(x + WIDTH / 100, y + HEIGHT / 100, c, width, height); 
        return copy_circle;
    }

    @Override 
    public void move(int mouseX, int mouseY) { // This method takes in the x and y coordinates of the mouse and is used to move the circle to the new position.
        // TODO Auto-generated method stub
        x = mouseX;
        y = mouseY;
        
    }

    @Override
    public shape history_copy() { // This method creates a copy of the circle and returns it for the history of the shape list 
        // TODO Auto-generated method stub 
        Circle copy_circle = new Circle(x, y, c, width, height); 
        return copy_circle;
    }
    
}
