package inheritance;

import java.awt.Color;

public class ColorBall extends BouncingBall{
	
	private int counter = 0;

	public ColorBall(int startx, int starty, int startrad, int startxspeed, int startyspeed, Color startcolor) {
		super(startx, starty, startrad, startxspeed, startyspeed, startcolor);
	} 
	
	
	
	public void move() {
		
		super.move();
		counter++;
		
		if(counter > 100) {
			int r = (int) (Math.floor(Math.random()*255));
			int g = (int) (Math.floor(Math.random()*255));
			int b = (int) (Math.floor(Math.random()*255));

			Color c = new Color(r, g, b);
			super.setColor(c);
			counter = 0; 
		}
		
	}
	
	
}
