package inheritance;

public class A {
	private int x;
	
	public A(int start) {
		x = start;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int newx) {
		x = newx;
	}
	
	public void doublex() {
		x *=2;
	}
	
	public static void main(String[]args) {
		A test1 = new A(4);
		test1.x+=3; 
		System.out.println(test1.getX());
		
	}
}
