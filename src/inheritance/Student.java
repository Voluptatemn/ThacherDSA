package inheritance;

public class Student extends Human{

	private int grade;
	
	public Student(int h, int a, String name, int grade) {
		
		super(h, a, name); 
		this.grade = grade; 
	}
	
	public void takeTest() {
		System.out.println("stress"); 
	}
	
	public void birthday() {
		
		super.birthday(); 
		System.out.println("happy"); 
		
	}

	public static void main(String[] args) {
		
		Student marg = new Student(60, 10, "margaret", 11); 
		
		marg.birthday();
		System.out.println(marg.getAge());
		
	}
	
}
