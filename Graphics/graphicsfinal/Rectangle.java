package graphicsfinal;
import java.awt.*;


public class Rectangle extends shape{


    public Rectangle(int x, int y, Color c, int w, int h) {
        super(x, y, c, w, h);
    }


    @Override
    public void draw(Graphics g) { // This method takes in a Graphics object as a parameter and is used to draw the rectangle on the screen. It sets the color of the rectangle and fills a rectangle with the given x, y, width, and height.
    
        g.setColor(c);
        g.fillRect(x, y, width, height);
        
    }

    @Override
    public boolean isON(int mouseX, int mouseY) { // This method takes in the x and y coordinates of the mouse and checks if the mouse is inside the rectangle. It returns true if the mouse is inside the rectangle and false otherwise.
        // TODO Auto-generated method stub

        if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
            return true;
        } 

        return false;
    }

    @Override
    public void resize(int mouseX, int mouseY) { // This method takes in the x and y coordinates of the mouse and is used to resize the rectangle
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
    public shape copy(int WIDTH, int HEIGHT) { // This method creates a copy of the rectangle and returns it.
        // TODO Auto-generated method stub
        Rectangle copy_rectangle = new Rectangle(x + WIDTH / 100, y + HEIGHT / 100, c, width, height);
        return copy_rectangle;
    }


    @Override
    public void move(int mouseX, int mouseY) { //  This method takes in the x and y coordinates of the mouse and is used to move the rectangle to the new position.
        // TODO Auto-generated method stub
        x = mouseX;
        y = mouseY;
    }


    @Override 
    public shape history_copy() { // This method creates a copy of the rectangle and returns it used for history 
        // TODO Auto-generated method stub
        Rectangle copy_rectangle = new Rectangle(x, y, c, width, height);
        return copy_rectangle;
    }
    
}
