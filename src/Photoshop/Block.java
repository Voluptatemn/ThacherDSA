package Photoshop;

import java.awt.Color;

public class Block {
	private int size, x, y, speed; 
	private Color color; 
	public Block (int size, int x, int y, int speed, Color c  ) {
		
		this.size = size; 
		this.x = x;
		this.y = y;
		this.speed = speed;
		color = c; 
	}
}
