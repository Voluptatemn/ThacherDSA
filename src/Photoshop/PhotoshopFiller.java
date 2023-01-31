package Photoshop;
// Photoshop program that can run several manipulations on 
// an image
// Make sure there is an Images folder inside your Java project
// If your new image doesn't show up, make sure you've refreshed your Java project
//
// filler code by Mr. David

import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class PhotoshopFiller extends Component {

	// the name of the output file. will be determined by which methods are called
    private String outputName;
    
    // the 2d array of colors representing the image
    private Color[][] pixels;

    // this method increases each color's rgb value by a given amount.
    // don't forget that rgb values are limited to the range [0,255]
    public void brighten(int amount) {
        outputName = "brightened" + outputName;
        
        
        for (int i = 0; i < pixels.length; i++) {
        	for (int j = 0; j < pixels[i].length; j++) {
        		int r = pixels[i][j].getRed();
        		int g = pixels[i][j].getGreen();
        		int b = pixels[i][j].getBlue();
   
        		r = Math.max(Math.min(255, r + amount), 0);
        		g = Math.max(Math.min(255, g + amount), 0);
        		b = Math.max(Math.min(255, b + amount), 0);
        		
        		
        		pixels[i][j] = new Color(r, g, b);
        	}
        }
        
    }
    
    // flip an image either horizontally or vertically.
    public void flip(boolean horizontally) {
        outputName = (horizontally?"h":"v") + "_flipped_" + outputName;
        if (horizontally) {
        	for (int i = 0; i < pixels.length; i++) {
        		for (int j = 0; j < pixels[i].length / 2; j++) {
        			Color temp = pixels[i][j];
        			pixels[i][j] = pixels[i][pixels[i].length - j - 1];
        			pixels[i][pixels[i].length - j - 1] = temp;
        		}
        	}
        }
        else {
        	for (int i = 0; i < pixels.length/2; i++) {
        		for (int j = 0; j < pixels[i].length; j++) {
        			Color temp = pixels[i][j];
        			pixels[i][j] = pixels[pixels.length - i - 1][j];
        			pixels[pixels.length - i - 1][j] = temp;
        					
        		}
        	}
        }
    }
    
    // negates an image
    // to do this: subtract each pixel's rgb value from 255 
    // and use this as the new value
    public void negate() {
        outputName = "negated_" + outputName;
        
        int h = pixels.length; 
        
        for (int i = 0; i < pixels.length; i++) {
        	for (int j = 0; j < pixels[i].length; j++) {
        		int r = pixels[i][j].getRed();
        		int g = pixels[i][j].getGreen();
        		int b = pixels[i][j].getBlue();
   
        		r = 255 - r;
        		g = 255 - g;
        		b = 255 - b;
        		
        		
        		pixels[i][j] = new Color(r, g, b);
        	}
        }
        
    }
    
    // this makes the image 'simpler' by redrawing it using only a few colors
    // to do this: for each pixel, find the color in the list that is closest to
    // the pixel's rgb value. 
    // use this predefined color as the rgb value for the changed image.
    public void simplify() {
    
    		// the list of colors to compare to. Feel free to change/add colors
    		Color[] colorList = {Color.BLUE, Color.RED,Color.ORANGE, Color.MAGENTA,
                Color.BLACK, Color.WHITE, Color.GREEN, Color.YELLOW, Color.CYAN};
        outputName = "simplified_" + outputName;
        
        
        
        for (int i = 0; i < pixels.length; i++) {
        	for (int j = 0; j < pixels[i].length; j++) {
        		double d = Double.MAX_VALUE; 
        		Color color1 = new Color(0, 0, 0);
        		for (int m = 0; m < colorList.length; m++) {
        			double dis = distance (colorList[m], pixels[i][j]);
        			if (d > dis) {
        				d = dis; 
        				color1 = colorList[m];
        			}
        			
        		}
        		pixels[i][j] = color1; 
        		
        		
        		
        	}
        }
         
    }
    
    // optional helper method (recommended) that finds the 'distance' 
    // between two colors.
    // use the 3d distance formula to calculate
    public double distance(Color c1, Color c2) {
    		int r = c1.getRed() - c2.getRed();
    		int g = c1.getGreen() - c2.getGreen();
    		int b = c1.getBlue() - c2.getBlue();
    		double d = Math.sqrt((r * r + g * g + b * b));
    		
    		
    		return d;	
    }
    
    // this blurs the image
    // to do this: at each pixel, sum the 8 surrounding pixels' rgb values 
    // with the current pixel's own rgb value. 
    // divide this sum by 9, and set it as the rgb value for the blurred image
    public void blur() {
		outputName = "blurred_" + outputName;
		 
		
	}
    
    // this highlights the edges in the image, turning everything else black. 
    // to do this: at each pixel, sum the 8 surrounding pixels' rgb values. 
    // now, multiply the current pixel's rgb value by 8, then subtract the sum.
    // this value is the rgb value for the 'edged' image
    public void edge() {
        outputName = "edged_" + outputName;

        // your code here
    }
    
    public void layer() {
    	outputName = "layered_" + outputName;
    	// grabbing the image from file
    	JFileChooser fc = new JFileChooser();
		File workingDirectory = new File(System.getProperty("user.dir")+System.getProperty("file.separator")+ "Images");
		fc.setCurrentDirectory(workingDirectory);
		fc.showOpenDialog(null);
		File my_file = fc.getSelectedFile();
		if (my_file == null)
			System.exit(-1);
		//convert the file into an image so to use the built-in scaling feature
		Image image = null; 
		try {
		    image = ImageIO.read(my_file);
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
    	image = image.getScaledInstance(pixels.length, pixels[0].length, Image.SCALE_DEFAULT);
    	//convert the image back into buffered image 
    	BufferedImage buffered1 = (BufferedImage) image; 
    	//create a 2d array 
    	Color[][] pixels_layer = create_pixel_array(buffered1);
    	//layer the picture
    	for (int i = 0; i < pixels.length; i++) {
    		for(int j = 0; j < pixels[i].length; j++) {
    			int r = (pixels[i][j].getRed() + pixels_layer[i][j].getRed()) / 2;
        		int g = (pixels[i][j].getGreen() + pixels_layer[i][j].getGreen()) / 2 ;
        		int b = (pixels[i][j].getBlue() + pixels_layer[i][j].getBlue()) / 2; 
        		
        		pixels[i][j] = new Color(r, g, b);
    		}
    	}
    }
    
  
    
    // *************** DON'T MESS WITH THE BELOW CODE (except to change print statements) **************** //
    
    // feel free to check it out, but don't change it unless you've consulted 
    // with Mr. David and understand what the code's doing
    
    

    public PhotoshopFiller() {
    	JFileChooser fc = new JFileChooser();
		File workingDirectory = new File(System.getProperty("user.dir")+System.getProperty("file.separator")+ "Images");
//		fc.setCurrentDirectory(workingDirectory);
		fc.showOpenDialog(null);
		File my_file = fc.getSelectedFile();
		if (my_file == null)
			System.exit(-1);
		
		// reads the image file and creates our 2d array
        BufferedImage image;
		try {
			image = ImageIO.read(my_file);
		
	        pixels = create_pixel_array(image);
			outputName = my_file.getName();
			
			// runs the manipulations determined by the user
			System.out.println("Enter the manipulations you would like to run on the image.\nYour "
					+ "choices are: brighten, flip, negate, blur, edge, layer or simplify.\nEnter each "
					+ "manipulation you'd like to run, then type in 'done'.");
			Scanner in = new Scanner(System.in);
			String action = in.next().toLowerCase();
			while (!action.equals("done")) {
	    			try {
		    			if (action.equals("brighten")) {
		    				System.out.println("enter an amount to increase the brightness by");
		    				int brightness = in.nextInt();
		        			Method m = getClass().getDeclaredMethod(action, int.class);
		        			m.invoke(this, brightness);
		    			}
		    			else if (action.equals("flip")) {
		    				System.out.println("enter \"h\" to flip horizontally, anything else to flip vertically.");
		        			Method m = getClass().getDeclaredMethod(action, boolean.class);
		        			m.invoke(this, in.next().equals("h"));
		    			}
		    			else {
		        			Method m = getClass().getDeclaredMethod(action);
		        			m.invoke(this, new Object[0]);
		    			}
		    			System.out.println("done. enter another action, or type 'done'");
	    			}
	    			catch (NoSuchMethodException e) {
	    				System.out.println("not a valid action, try again");
	    			} catch (IllegalAccessException e) {e.printStackTrace();System.exit(1);} 
	    			catch (IllegalArgumentException e) {e.printStackTrace();System.exit(1);}
	    			catch (InvocationTargetException e) {e.printStackTrace();System.exit(1);}
	    			
	    			action = in.next().toLowerCase();
	    		} 
	        in.close();
	        
	        // turns our 2d array of colors into a new png file
	        BufferedImage new_image = create_new_image(pixels);
	        File output_file = new File("Images/" + outputName);
	        ImageIO.write(new_image, "png", output_file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
		
    
    public Color[][] create_pixel_array(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        
        Color[][] pixels = new Color[h][];
        for (int i = 0; i < h; i++) {
            pixels[i] = new Color[w];
            for (int j = 0; j < w; j++) {
                pixels[i][j] = new Color(image.getRGB(j,i));
            }
        }
        return pixels;
    }

    public BufferedImage create_new_image(Color[][] pixels) {
        BufferedImage new_image = new BufferedImage(pixels[0].length,
                pixels.length, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
            		new_image.setRGB(j, i, pixels[i][j].getRGB());
            }
        }
        return new_image;
    }

    public static void main(String[] args) {
		new PhotoshopFiller();
	}
}