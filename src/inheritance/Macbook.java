package inheritance;

public class Macbook extends Computer{

	private String preinstalled;
	
	public Macbook(int h, int w, String name, String os, String pi) {
		super(h, w, name, os);
		preinstalled = pi; 
	}
	
	
	public String getPreinstalled() {
		return preinstalled;
	}
	
	
	public void broken() {
		super.broken();
		System.out.println("Go repair"); 
	}

}
