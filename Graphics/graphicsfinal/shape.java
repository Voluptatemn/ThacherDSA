package graphicsfinal;
import java.awt.*;

public abstract class shape { // parent class of all the shapes 

    int x;
    int y;
    int width;
    int height;
    Color c;
    int initialX;
    int initialY;
    

    public shape(int x, int y, Color c, int w, int h) {

        this.x = x;
        this.y = y; 
        this.c = c;
        width = w;
        height = h;   
        initialX = x;
        initialY = y;   

    }

    abstract public void draw(Graphics g);

    abstract public boolean isON(int mouseX, int mouseY);

    abstract public void resize(int mouseX, int mouseY); 

    public abstract void move(int mouseX, int mouseY);
    
    abstract public shape copy(int WIDTH, int HEIGHT); 

    abstract public shape history_copy(); 
    
}
