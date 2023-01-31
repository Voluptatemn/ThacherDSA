package Bank;

public class GreatAccount extends BankAccount{

	private int withdraw_month;
	private int month_count;
	
	public GreatAccount(double b, String n) {
		super(b, n);
	}
	
	public void withdraw(double n) {
		if (withdraw_month < 3) {
			super.withdraw(n);
			withdraw_month++;
		} else {
			System.out.println("You have already withdrown 3 times this month, you can't withdraw anymore");
		}
	}
	
	public void nextmonth() {
		if (withdraw_month == 0) {
			month_count++;
		} else {
			withdraw_month = 0; 
		}
	}
	
	public void nextyear() {
		if (month_count == 12) {
			deposit(getBalance());
		} else {
			month_count = 0; 
		}
	}
	
	public static void main(String[] args) {
		GreatAccount sam = new GreatAccount(1000.0, "sam");
		for (int i = 0; i < 12; i++) {
			sam.nextmonth(); 
			System.out.println(sam.toString()); 
		}
		sam.nextyear();
		System.out.println(sam.toString()); 
		
	}
	
	
}
