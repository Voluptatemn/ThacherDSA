package inheritance;

public class Computer {

	private int height, width;
	private String name, operating_system; 
	
	public Computer(int h, int w, String name, String os) {
		height = h;
		width = w;
		this.name = name; 
		operating_system = os; 
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName) {
		
		this.name = newName; 
		
	}
	
	public int getheight() {
		return height;
	}
	
	
	public int getwidth() {
		return width;
	}
	
	public void broken() {
		System.out.println("Broken Computer"); 
	}
}
