package Bank;

public class InterestAccount extends BankAccount{
	
	private double interest;
	
	public InterestAccount(double b, String n, double i) {
		super(b, n);
		interest = i/100; 
	}

	
	public void addInterest() {
		double interest_month = getBalance() * interest;
		deposit(interest_month); 
	}
	
	public static void main(String[] args) {
		InterestAccount sam = new InterestAccount(10.0, "sam", 3.0);
		System.out.println(sam.toString()); 
		sam.addInterest();
		System.out.println(sam.toString()); 
	}
}
