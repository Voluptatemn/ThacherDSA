package graphicsfinal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Text extends shape{

    String input;  // the string to output 
    int charCount; // the character count so to calculate the estimated width of the text 

    public Text(int x, int y, Color c, int w, int h, String in) { // constructor 
        super(x, y, c, w, h);
        //TODO Auto-generated constructor stub
        this.input = in; 
        charCount = input.length(); 
        width = charCount * height; // times height becasue most of the alphabet has a 1 to 1 ratio between height and width 
        
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        g.setColor(c);
        g.setFont(new Font("TimesRoman", Font.PLAIN, height)); // set the font of the text to timesRoman, so to fit the ratio between the width and the height of the character 
        g.drawString(input, x, y);  // draw the string 
         
        
    }

    @Override
    public boolean isON(int mouseX, int mouseY) { 
        // TODO Auto-generated method stub
        if (mouseX >= x && mouseX <= x + width && mouseY <= y && mouseY >= y - height) { // test if the mouse is on the shape, the mouseY is like this becasue text has its x and y in the botton left 
            return true;
        } 
        return false;
    }

    @Override
    public void resize(int mouseX, int mouseY) { // resizing the object 
        // TODO Auto-generated method stub
        if (mouseY < y) { // since only height matters, the change of height will determine the change of font 
            height = y - mouseY; 
        }
        
    }

    @Override
    public void move(int mouseX, int mouseY) { // moving the object 
        // TODO Auto-generated method stub
        x = mouseX; // changing x and y 
        y = mouseY;
        
    }

    @Override
    public shape copy(int WIDTH, int HEIGHT) { // copying the object, return a new text with the same attributes 
        // TODO Auto-generated method stub 
        Text copy_text = new Text(x + WIDTH / 100, y + HEIGHT / 100, c, width, height, input);
        return copy_text;
    }

    @Override 
    public shape history_copy() { // history copy function, access only when saving into the history 
        Text copy_text = new Text(x, y, c, width, height, input);
        return copy_text;
    }
    
}
