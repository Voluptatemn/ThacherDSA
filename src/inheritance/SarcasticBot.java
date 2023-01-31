package inheritance;

public class SarcasticBot extends ChatBot{
	
	@Override
	public void sayHi() {
		System.out.println("Hello, sunshine!");
		
	}

	@Override
	public void sayBye() {
		System.out.println("Don't call us, we will call you.");
		
	}

	@Override
	public void startConversation() {
		System.out.println("Do you believe love in first sight?");
		
	}

	@Override
	public void askFirstQuestion() {
		System.out.println("Is cereal soup?");
		
	}

	@Override
	public void askSecondQuestion() {
		System.out.println("What secret conspiracy do you want to start with?");
		
	}

	@Override
	public void askThirdQuestion() {
		System.out.println("Can you dream?");
		
	}
	
}
