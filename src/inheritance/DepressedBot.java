package inheritance;

public class DepressedBot extends ChatBot{

	@Override
	public void sayHi() {
		System.out.println("I am ruined.");
		
	}

	@Override
	public void sayBye() {
		System.out.println("I got rejected.");
		
	}

	@Override
	public void startConversation() {
		System.out.println(" ");
		
	}

	@Override
	public void askFirstQuestion() {
		System.out.println("Why are we here?");
		
	}

	@Override
	public void askSecondQuestion() {
		System.out.println("Why is the world like this?");
		
	}

	@Override
	public void askThirdQuestion() {
		System.out.println("Should I kill myself?");
		
	}
	
}
