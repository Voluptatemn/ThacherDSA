package inheritance;

import java.awt.Color;

public class ShrinkingBall extends ColorBall {
	
	private int counterRad = 0; 
	private boolean Shrinking = true; 
	
	
	public ShrinkingBall(int startx, int starty, int startrad, int startxspeed, int startyspeed, Color startcolor) {
		super(startx, starty, startrad, startxspeed, startyspeed, startcolor);
	}
	
	public void move() {
		
		super.move();
		counterRad ++ ;
		
		if(counterRad > 5) {
			
			if (getRad() <= 40 && Shrinking) {
				setRad(getRad() - 1);
				if (getRad() <= 1) {
					Shrinking = false;
				}
			} else {
				
				setRad(getRad() + 1);
				if(getRad() >= 40) {
					Shrinking = true; 
				}
				
			}
			
			
			
			counterRad = 0;
		}
	}

}
