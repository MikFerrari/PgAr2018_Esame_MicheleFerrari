package it.unibs.ing.fp.storiavirtuale;

public class Main {

	private final static String WELCOME_MESSAGE = "Benvenuto nel nostro portale per storie virtuali!";
	private final static String BYE_MESSAGE = "Grazie per aver giocato. A presto!";
	
	public static void main(String[] args) {
		
		Story story = new Story();
		
		welcome();
		
		UserInterface.generateStory(story);
		
		UserInterface.manageInteraction(story);
		
		bye();
		

	}
	
	private static void welcome() {
		System.out.println(WELCOME_MESSAGE);
	}
	
	private static void bye() {
		System.out.println(BYE_MESSAGE);
	}

}
