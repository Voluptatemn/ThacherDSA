package Bank;

public class BankAccount {
	 private double balance;
	 private String name; 
	 
	 
	 public BankAccount(double b, String n) {
		 balance = b;
		 name = n; 
	 }
	 
	 public BankAccount(String n) {
		 balance = 0;
		 name = n; 
	 }
	 
	 
	 public void deposit(double n) {
		 balance += n;
	 }
	 
	 public void withdraw(double n) {
		 balance -= n;
	 }
	 
	 public String toString() {
		  
		return name + "'s bank has " + balance + " dollars";
		 
	 }
	 
	 public double getBalance () {
		 return balance;
	 }
	 
	 
	 public static void main(String[] args) {
		 
		 BankAccount sam = new BankAccount("Sam");
		 System.out.println(sam.toString()); 
		 sam.deposit(10.0);
		 System.out.println(sam.toString()); 
		 sam.withdraw(5.0);
		 System.out.println(sam.toString()); 
		 
	 }
}
