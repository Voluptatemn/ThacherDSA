package graphicsfinal;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends shape{

    int initialX_Line; // for copying the shape, so to not have the x and final x be the same point 
    int initialY_Line; // not to have the y and the final y to be the same point 

    public Line(int x, int y, Color c, int w, int h, int initialX_Line, int initialY_Line) { // constructor 
        super(x, y, c, w, h); 
        this.initialX_Line = initialX_Line; 
        this.initialY_Line = initialY_Line; 
        //TODO Auto-generated constructor stub
    }

    @Override
    public void draw(Graphics g) { // draw the line 
        // TODO Auto-generated method stub
        g.setColor(c);
        g.drawLine(initialX_Line, initialY_Line, x, y);
        
    }

    @Override
    public boolean isON(int mouseX, int mouseY) { // check it mouse is on the line using the slope of the line and the slope of the mouse and the two end points of the line 
        // TODO Auto-generated method stub
        int slope;

        if (Math.min(initialX_Line, x) <= mouseX && mouseX <= Math.max(initialX_Line, x) && Math.min(initialY_Line, y) <= mouseY && mouseY <= Math.max(initialY_Line, y)) {
            if (initialX_Line > x) {
                slope = (initialX_Line - x) / (initialY_Line - y);
                int slope_mouse = (mouseX - x) / (mouseY - y);
                if (slope == slope_mouse) {
                    return true; 
                } else {
                    return false;
                }
            } else if (initialX_Line < x) {
                slope = (x - initialX_Line) / (y - initialY_Line); 
                int slope_mouse = (mouseX - initialX_Line) / (mouseY - initialY_Line);
                if (slope == slope_mouse) {
                    return true; 
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }
            
        return false;
    }

    @Override
    public void resize(int mouseX, int mouseY) { // resize the line 
        // TODO Auto-generated method stub
        x = mouseX;
        y = mouseY;

        
    }

    @Override
    public shape copy(int WIDTH, int HEIGHT) { // copy the line 
        // TODO Auto-generated method stub
        Line copy_line = new Line(x + WIDTH / 100, y + HEIGHT / 100, c, width, height, initialX_Line, initialY_Line);
        return copy_line;
    }

    @Override
    public void move(int mouseX, int mouseY) { // move the line 
        // TODO Auto-generated method stub
        int tempX = initialX;
        int tempY = initialY;
        initialX = mouseX;
        initialY = mouseY;
        int changeX = tempX - mouseX;
        int changeY = tempY - mouseY;
        x = x - changeX;
        y = y - changeY;

        
        
    }

    @Override
    public shape history_copy() { // history copy 
        // TODO Auto-generated method stub
        Line copy_line = new Line(x, y, c, width, height, initialX_Line, initialY_Line);
        return copy_line;
    }
    
}
