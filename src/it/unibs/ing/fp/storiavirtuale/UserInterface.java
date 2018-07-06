package it.unibs.ing.fp.storiavirtuale;

import it.unibs.ing.fp.mylib.MyMenu;

public class UserInterface {
	
	private final static String[] VOICES = { "Cercare una storia", "Selezionare una storia da giocare" ,
											 "Visualizzare l'elenco di tutte le storie", "Ordinare le storie alfabeticamente" ,
											 "Ordinare le storie secondo il numero di paragrafi" };
	private final static String END_STORY_MESSAGE = "La storia è terminata!";

	public static void generateStory(Story story, String filename) {
		XMLParser.read(filename, story);

		story.setStartingPoint(story.getParagraphs().get(0));
		story.setEndingPoints();
		story.linkAdjacentParagraphs();
	}
	
	public static void manageInteraction(Story story) { //Si potrebbe fare nel main come pensato inizialmente,
											 			//ma preferisco scrivere l'algoritmo qui per rendere il main più pulito
		System.out.println(story);
		Paragraph currentParagraph = story.getStartingPoint();
		
		while(!story.hasFinished(currentParagraph)) {
			MyMenu menu = new MyMenu(currentParagraph.getDescription(), currentParagraph.getOptionsTitle());
			
			int choice = menu.choose();
			
			if(choice == 0)
				break;
			
			currentParagraph = currentParagraph.getOptions().get(choice - 1); //Baste fare così poichè i titoli e i rispettivi paragrafi
																	  		  //sono stati aggiunti ordinatamente
		}
		System.out.println(currentParagraph.getDescription());
		System.out.println(END_STORY_MESSAGE);
	}
	
	public static void manageCollection(StoryCollection collection) {
	
		MyMenu menu = new MyMenu("Selezionare un'opzione", VOICES);
		int choice;
		
		do {
			choice = menu.choose();
			
			switch(choice) {
				case 0:
					break;
					
				case 1: MyMenu case1Menu = new MyMenu("Inserire il titolo della storia da cercare", collection.getStories());
						int case1Coiche = case1Menu.choose();
						String case1Story = collection.getStories().get(case1Coiche - 1).getTitle();
						System.out.println(collection.searchStory(case1Story)); //Funzione per la ricerca della storia via titolo,
																				//superflua se si struttura il codice come nel case 2
					break;
					
				case 2: MyMenu case2Menu = new MyMenu("Inserire il titolo della storia da giocare", collection.getStories());
						int case2Coiche = case2Menu.choose();
						Story case2Story = collection.getStories().get(case2Coiche - 1);
						manageInteraction(case2Story);
					break;
					
				case 3: System.out.println(collection.toString());
					break;
					
				case 4: collection.alphabeticalOrdering();
						System.out.println(collection); //Per controllo della correttezza dell'ordinamento
					break;
					
				case 5: collection.numberOfParagraphsOrdering();
						for(Story story : collection.getStories()) { //Per controllo della correttezza dell'ordinamento
							System.out.println(story);
						}
					break;
			}
			
		} while(choice != 0);
		
	}
		
}