package it.unibs.ing.fp.storiavirtuale;

public class Main {

	private final static String WELCOME_MESSAGE = "Benvenuto nel nostro portale per storie virtuali!";
	private final static String BYE_MESSAGE = "Grazie per aver giocato. A presto!";
	
	public static void main(String[] args) {
		
		StoryCollection collection = new StoryCollection();
		Story story1 = new Story();	//Codice del nucleo
		Story story2 = new Story();
		
		welcome();	//Codice del nucleo
		
		UserInterface.generateStory(story1, "PgAr2018_Story_2.1.xml");	//Codice del nucleo
		UserInterface.generateStory(story2, "PgAr2018_Story_2.2.xml");
		
		collection.addStory(story1);
		collection.addStory(story2);
		
		UserInterface.manageCollection(collection);
		
		
		// UserInterface.manageInteraction(story1);		//Codice del nucleo
		
		
		bye();	//Codice del nucleo
		
		
	}
	
	private static void welcome() {
		System.out.println(WELCOME_MESSAGE);
	}
	
	private static void bye() {
		System.out.println(BYE_MESSAGE);
	}

}
