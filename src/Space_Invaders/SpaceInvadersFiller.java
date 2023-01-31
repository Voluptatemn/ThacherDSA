package Space_Invaders;

// Space Invaders Filler Code by Mr. David

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class SpaceInvadersFiller {
	
	// constants for various aspects of the game
	// feel free to change them to make the game harder/easier
	private final int WIDTH = 1000, HEIGHT = 700, NUMALIENS = 1, ALIENSPEED = 4, LASERSPEED = 60, PLAYERSPEED = 12,
			LASERWIDTH = 20, LASERHEIGHT = 20, PLAYERENEMYWIDTH = 50, PLAYERENEMYHEIGHT = 35, PLAYERWIDTH = 50, PLAYERHEIGHT = 35, LIVES = 1;
	
	// determines the difficulty. The closer to 1.0, the easier the game 
	private final double DIFFICULTY = 0.99;
	
	// our list of aliens
	private ArrayList<SpaceThing> aliens = new ArrayList<SpaceThing>();
	
	// our list of list of lasers for both the player and the aliens
	private ArrayList<Laser> alienLasers = new ArrayList<Laser>();
	private ArrayList<Laser> playerLasers = new ArrayList<Laser>();
	
	//A list of shields and shields on the player
	private ArrayList<Shield> shields = new ArrayList<Shield>();
	private ArrayList<Shield> playerShields = new ArrayList<Shield>(); 	
	// the player
	private SpaceThing player;
	
	// the current speed of the player as well as their remaining lives
	private int lives = LIVES, playerSpeed = 0;
	
	// booleans to keep track of the game's progress
	private boolean lost = false, paused = true;
	private boolean won = false;
	
	
	// move the aliens, the lasers, and the player. Loops aliens when necessary, 
	// and randomly shoots lasers from the aliens
	public void move() {
		
		//check if already won
		if (lives == 0) {
			lost = true; 
		}
		
		if (aliens.size() == 0) {
			won = true; 
		}
		
		//stop moving after losing or winning
		if (lost != true && won != true) {
		//move aliens
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).moveX(ALIENSPEED);
			//shift the position to the left of the screen if reaches the right edge
			if (aliens.get(i).x >= WIDTH) {
				aliens.get(i).x = 0; 
				aliens.get(i).y += HEIGHT/7; //aliens move down after reaching the right edge
				if (aliens.get(i).y >= HEIGHT) { //aliens move outside the screen, lost
					lost = true;
				}
			}
			//create alien lasers at the current position 
			if(Math.random() >= DIFFICULTY) {
				Laser Laser = new Laser(aliens.get(i).x, aliens.get(i).y, LASERWIDTH, LASERHEIGHT, "/Users/qiangangsamwang/eclipse-workspace/Unit 1/Images/218-2186445_bullet-clipart.png-2-removebg-preview.png");
				alienLasers.add(Laser); 
			}
		}
		
		//add a shield with frequency depends on the difficulty, only have 3 shields at a time
		if (shields.size() < 3) {
				int x = (int) (Math.random() * (WIDTH - 1));
				Shield shield = new Shield(x, HEIGHT/7*6, PLAYERWIDTH, PLAYERHEIGHT, "/Users/qiangangsamwang/eclipse-workspace/Unit 1/Images/shield-icon-protection-symbol-security-emblem-in-line-style-2H9PN54-removebg-preview.png");
				shields.add(shield);
		}
		
		//move the shield with the player
		for (int i = 0; i < playerShields.size(); i++) {
			playerShields.get(i).x = player.x; 
			playerShields.get(i).moveX(playerSpeed); 
		}
		
		
		
		//player movement
		player.moveX(playerSpeed);
		//shift position when reach the edge
		if (player.x > WIDTH) {
			player.x = 0;
		}
		if (player.x < 0) {
			player.x = WIDTH; 
		}
		
		
		
		//alien laser movement
		for (int i = 0; i < alienLasers.size(); i++) {
			if (LASERSPEED >= 50) {
				alienLasers.get(i).moveY(50); 
			} else {
				alienLasers.get(i).moveY(LASERSPEED); 
			}
			if (alienLasers.get(i).y >= HEIGHT) {
				alienLasers.remove(i);
				i--; 
			}
		}
		
		//player laser movement
		for (int i = 0; i < playerLasers.size(); i++) {
			if (LASERSPEED >= 50) {
				playerLasers.get(i).moveY(-50); 
			} else {
				playerLasers.get(i).moveY(-LASERSPEED); 
			}
			if (playerLasers.get(i).y <= 0) {
				playerLasers.remove(i);
				i--; 
			}
		}
		
		}
		
		
		
	}
	
	// check for collisions between alien lasers and the player
	// and between player lasers and the aliens
	// check if the aliens have reached the ground
	public void checkCollisions() {
		
		
		//check the collisions between shields and alien laser
		for (int i = 0; i < alienLasers.size(); i++) {
			for (int j = 0; j < playerShields.size(); j++) {
				if (alienLasers.get(i).intersects(playerShields.get(j))) {
					playerShields.remove(j);
					alienLasers.remove(i);
					break; 
				}
			}
		}
		
		//check the collisions between alien lasers and player
		for (int i = 0; i < alienLasers.size(); i++) {
			if (alienLasers.get(i).intersects(player)) {
				lives--;
				alienLasers.remove(i);
				i--; 
				if (lives == 0) {
					lost = true; 
				}
			}
		}
		// check the opposite
			for (int j = 0; j < aliens.size(); j++) {
				for (int i = 0; i < playerLasers.size(); i++) {
				if (playerLasers.get(i).intersects(aliens.get(j))) { //never hit the last alien
					aliens.remove(j);
					playerLasers.remove(i);
					if (aliens.size() == 0) {
						won = true; 
					}
					break;
				}
				
			}
		}
			
			//check for the collisions between shields and player
			for (int i = 0; i < shields.size(); i++) {
				if (shields.get(i).intersects(player)) {
					if (playerShields.size() < 1) {
						playerShields.add(shields.get(i));
						shields.remove(i);
						break; 
					}
				}
			}
			
			for (int i = 0; i < aliens.size(); i++) { //check for the collisions between player and aliens
				if (aliens.get(i).intersects(player)) {
					lost = true; 
				}
			}
			
		
	}
	
	// set up your variables, lists, etc here
	public void setup() {
		
		 player = new SpaceThing(WIDTH/2, HEIGHT/7*6, PLAYERWIDTH, PLAYERHEIGHT, "/Users/qiangangsamwang/eclipse-workspace/Unit 1/Images/working-removebg-preview.png");
		 
		 for (int i = 0; i < NUMALIENS; i++) {
			 if (NUMALIENS == 1) { // only works it there is one alien
				 SpaceThing alien = new SpaceThing(WIDTH/2, HEIGHT/7, PLAYERENEMYWIDTH, PLAYERENEMYHEIGHT, "/Users/qiangangsamwang/eclipse-workspace/Unit 1/Images/130-1308618_transparent-alien-spaceship-png-space-ship-2d-png-removebg-preview.png");
				 aliens.add(alien);
			 } else {
			 if (i >= (NUMALIENS / 2)) { // second row
				 SpaceThing alien = new SpaceThing(WIDTH / (NUMALIENS/2) * (i - (NUMALIENS/2)) , HEIGHT/7 * 3 / 2, PLAYERENEMYWIDTH, PLAYERENEMYHEIGHT,  "/Users/qiangangsamwang/eclipse-workspace/Unit 1/Images/130-1308618_transparent-alien-spaceship-png-space-ship-2d-png-removebg-preview.png");
				 aliens.add(alien); 
			 } else { //first
				 SpaceThing alien = new SpaceThing(WIDTH / (NUMALIENS/2) * i , HEIGHT/7, PLAYERENEMYWIDTH, PLAYERENEMYHEIGHT,  "/Users/qiangangsamwang/eclipse-workspace/Unit 1/Images/130-1308618_transparent-alien-spaceship-png-space-ship-2d-png-removebg-preview.png");
				 aliens.add(alien); 
			 }
			 }
			
			
			
		 }		
	}
	
	// fires a player laser. if there are currently less than 4 lasers on the screen,
	// adds to the list. if there are 4 lasers on the screen, removes a laser and 
	// replaces it with this new one
	public void fireLaser() {
		if (playerLasers.size() < 4) { //check if there is 4 lasers
			Laser Laser = new Laser(player.x, player.y, LASERWIDTH, LASERHEIGHT, "/Users/qiangangsamwang/eclipse-workspace/Unit 1/Images/BulletUpwards.png");
			playerLasers.add(Laser); 
		} else {
			playerLasers.remove(0);
			Laser Laser = new Laser(player.x, player.y, LASERWIDTH, LASERHEIGHT, "/Users/qiangangsamwang/eclipse-workspace/Unit 1/Images/BulletUpwards.png");
			playerLasers.add(Laser); 
		}
	}
	
	// draw a black background along with your lasers, aliens, and player here
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		player.draw(g);
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).draw(g);
		}
		for (int i = 0; i < alienLasers.size(); i++) {
			alienLasers.get(i).draw(g);
		}
		for (int i = 0; i < playerLasers.size(); i++) {
			playerLasers.get(i).draw(g);
		}
		for (int i = 0; i < shields.size(); i++) {
			shields.get(i).draw(g);
		}
		for (int i = 0; i < playerShields.size(); i++) {
			playerShields.get(i).draw(g); 
		}
		

		g.setColor(Color.red);
		g.drawString("Lives: "+lives, 15, 15);
		
		if (lost) 
			g.drawString("You lose, press R to restart", WIDTH/2-80, HEIGHT/2);
		if (won) 
			g.drawString("You win! Press R to restart", WIDTH/2-80, HEIGHT/2);
	}
	
	public void restart() { // restart after losing or winning the game 
			won = false;
			lost = false;
			lives = LIVES; 
			paused = true;
			// reinitializied the things
			aliens = new ArrayList<SpaceThing>();
			alienLasers = new ArrayList<Laser>();
			playerLasers = new ArrayList<Laser>();
			shields = new ArrayList<Shield>();
			playerShields = new ArrayList<Shield>(); 	
			setup();
			
	}
	
	// ******* DON'T TOUCH BELOW CODE ************//
	
	public SpaceInvadersFiller() {
		setup();
		JFrame frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel canvas = new JPanel() {
			public void paint(Graphics g) {draw(g);}
		};
		canvas.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "Left");
		canvas.getActionMap().put("Left", new LeftAction());
		canvas.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "LeftRelease");
		canvas.getActionMap().put("LeftRelease", new LeftReleaseAction());
		canvas.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), " ");
		canvas.getActionMap().put(" ", new SpaceAction());
		canvas.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "Right");
		canvas.getActionMap().put("Right", new RightAction());
		canvas.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "RightRelease");
		canvas.getActionMap().put("RightRelease", new RightReleaseAction());
		canvas.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0, false), "Pause");
		canvas.getActionMap().put("Pause", new PauseAction());
		canvas.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0, false), "Restart");
		canvas.getActionMap().put("Restart", new RestartAction());
		frame.add(canvas);
		frame.setVisible(true);
		
		while (true) {
			if (!paused) {
				move();
				checkCollisions();
				frame.getContentPane().repaint();
			}
			try {Thread.sleep(20);} 
			catch (InterruptedException e) {}
		}
	}
	
	private class RightAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			playerSpeed = PLAYERSPEED;
		}
	}
	private class LeftAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			playerSpeed = -PLAYERSPEED;
		}
	}
	private class LeftReleaseAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			playerSpeed = 0;
		}
	}
	private class RightReleaseAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			playerSpeed = 0;
		}
	}
	private class SpaceAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			fireLaser();
		}
	}
	
	private class RestartAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			if (won == true || lost == true) { //only if you lost or win 
			restart();
			paused = !paused;
			}
		}
	}
	
	private class PauseAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			paused = !paused;
		}
	}
	
	public static void main(String[] args) {
		new SpaceInvadersFiller();
	}
}
