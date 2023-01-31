package inheritance;

public class GageBot extends ChatBot {

	@Override
	public void sayHi() {
		System.out.println("Yo, What's up!!!!");
		
	}

	@Override
	public void sayBye() {
		System.out.println("See ya!");
		
	}

	@Override
	public void startConversation() {
		System.out.println("How are you Justin!");
		
	}

	@Override
	public void askFirstQuestion() {
		System.out.println("Who is going to survive in a school wide death match?");
		
	}

	@Override
	public void askSecondQuestion() {
		System.out.println("Do you think there is free will?");
		
	}

	@Override
	public void askThirdQuestion() {
		System.out.println("Amen");
		
	}

}
