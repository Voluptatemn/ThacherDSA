package Labyrinth; 

import java.awt.Color;
import java.net.CookieStore;
import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class MyBot2 extends Bot {

	public MyBot2(MazeRunner mr, Color c) {
		super(mr, c);
		//TODO Auto-generated constructor stub
	}

    boolean forward = true; // checks if it is moving forward or tracing backward
    int mode = 1; // 1 going forward, 2 turned left, 3 going forward, 4 going right, 5 going forward 
    ArrayList<Integer> history = new ArrayList<Integer>(); // the history for keeping track of the moves  
    int left_turn_count_for_right_turn = 0; 
    ArrayList<int[]> coordinate = new ArrayList<int[]>(); 
    int[] initial = {0,0};
    int direction = 1; // 1 right, 2 up, 3 left, 4 down
    
    
    public int repeat() {
        
        ArrayList<int[]> seen = new ArrayList<int[]>();
        for (int i = 0; i < coordinate.size(); i++) {
            if (seen.contains(coordinate.get(i))) {
                return seen.indexOf(coordinate.get(i)); 
            }
        }
        return null; 
        
    }

    public void changeDirection(int m) {
        
        if (m == 3) {
            direction ++; 
            if (direction == 5) {
                direction = 1; 
            }
        } else {
            direction --; 
            if (direction == 0) {
                direction = 4; 
            }
        }

    }

    public void changeCoordinate(int d) {

        if (d == 1) {
            initial[1] ++; 
        } else if (d == 2) {
            initial[0] ++;
        } else if (d == 3) {
            initial[1] --;
        } else if (d == 4) {
            initial[0] --; 
        }

    }

	
	public void move() {

        // try going forward 
        // then try going left
        // then try going right
        // after hitting a deadend, 
        // trace backwards, 
        // try the new thing 

        if (repeat()) {
            int lastMode = coordinate.get(coordinate.size() - 1);
            if (lastMode == 1) {
                
            }

        }

        if (forward) {

            if (mode == 1 || mode == 3 || mode == 5) {
                if (moveForward()) {
                    if (coordinate.size() == 0) {
                        coordinate.add(initial);
                    } 
                    changeDirection(mode);
                    changeCoordinate(direction);
                    coordinate.add(initial);
                    history.add(mode); 
                    mode = 1; 
                } else {
                    mode ++;
                }
            } else if (mode == 2) {
                turnLeft();
                mode ++; 
            } else if (mode == 4) {
                turnLeft();
                left_turn_count_for_right_turn ++;
                if (left_turn_count_for_right_turn == 2) {
                    mode ++;
                    left_turn_count_for_right_turn = 0; 
                }
            } else {
                turnLeft();
                left_turn_count_for_right_turn ++;
                if (left_turn_count_for_right_turn == 3) {
                    forward = false;
                    left_turn_count_for_right_turn = 0; 
                } 
            }

        } else {
            moveForward(); 
            forward = true; 
        }



    }
}
