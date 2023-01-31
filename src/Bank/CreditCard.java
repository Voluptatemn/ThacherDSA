package Bank;

public class CreditCard extends InterestAccount{

	public CreditCard(double b, String n, double i) {
		super(b, n, i);
	}
	
	public void withdraw(double n) {
		if(getBalance() - n >= 100) {
			super.withdraw(n);
		} else {
			System.out.println("After this withdraw, your credit card is going to go below 100 dollars. You can't withdraw.");
			
		}
	}
	
	public void addInterest() {
		super.addInterest();
		withdraw(10.0);
	}
	
	public static void main(String[] args) {
		CreditCard sam = new CreditCard(100.0, "sam", 9.0);
		sam.withdraw(25);
		System.out.println(sam.toString());
		sam.addInterest();
		System.out.println(sam.toString());
	
	}

}
