package graphicsfinal;

import java.awt.Color;
import java.awt.Graphics;

public class Triangle extends shape{

    public Triangle(int x, int y, Color c, int w, int h) { // constructor 
        super(x, y, c, w, h);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void draw(Graphics g) { // draw 
        // TODO Auto-generated method stub
        g.setColor(c);
        int xpoints[] = {x, x + width, x - width}; // x coordinates of the 3 points 
        int ypoints[] = {y, y + height, y + height}; // y coordinates 
        g.fillPolygon(xpoints, ypoints, 3);
        
    }

    @Override
    public boolean isON(int mouseX, int mouseY) { // is on function  
        // TODO Auto-generated method stub
        try {
            double result1 = (height/width) * (mouseX - x) + y; // calculate two sides  
            double result2 = (-height/width) * (mouseX - x) + y;  
            int y_cap = y + height;
        
            if (height > 0) { 
                if (result1 < mouseY && result2 < mouseY && mouseY < y_cap && mouseY >= y) { // see if the mouse is within the two sides 
                    return true;
            }
            } else {
                if (result1 > mouseY && result2 > mouseY && mouseY > y_cap && mouseY <= y) {
                    return true;
            }
            }
       } catch (Exception exc) {
            return false;
       }

        return false;
    }

    @Override
    public void resize(int mouseX, int mouseY) { // resizing the triangle 
        // TODO Auto-generated method stub
        width = Math.abs(mouseX - x);
        height = mouseY - y; 
        
    }

    @Override
    public void move(int mouseX, int mouseY) { // move the triangle 
        // TODO Auto-generated method stub
        x = mouseX;
        y = mouseY;
        
    }

    @Override
    public shape copy(int WIDTH, int HEIGHT) { // copy the triangle 
        // TODO Auto-generated method stub
        Triangle copy_Triangle = new Triangle(x + WIDTH / 10, y + HEIGHT / 100, c, width, height);
        return copy_Triangle;
    }

    @Override 
    public shape history_copy() { // make a history copy of the triangle 
        // TODO Auto-generated method stub
        Triangle copy_Triangle = new Triangle(x, y , c, width, height);
        return copy_Triangle;
    }
    
}
