package inheritance;

public class Human {
	
	private int height, age;
	private String name; 
	
	public Human(int h, int a, String name) {
		height = h;
		setAge(a);
		this.name = name; 
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName; 
	}
	
	public void birthday() {
		setAge(getAge() + 1); 
	}
	
	public int getAge() {
		return age; 
	}
	
	public static void main(String[] args) {
		Human gage = new Human(60,10, "gage");
		Human jeremy = new Human(50,16, "jeremy");
		
		System.out.println(gage.getAge());
		gage.birthday();
		System.out.println(gage.getAge());
		System.out.println(jeremy.getAge());
		jeremy.birthday();
		System.out.println(jeremy.getAge());
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
