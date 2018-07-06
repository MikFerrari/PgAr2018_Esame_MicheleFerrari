package it.unibs.ing.fp.storiavirtuale;

import it.unibs.ing.fp.mylib.MyMenu;

public class UserInterface {
	
	private final static String[] VOICES = { "Cercare una storia", "Selezionare una storia da giocare" ,
											 "Visualizzare l'elenco di tutte le storie", "Ordinare le storie alfabeticamente" ,
											 "Ordinare le storie secondo il numero di paragrafi", "Calcolare livelli di difficolta'" };
	private final static String END_STORY_MESSAGE = "La storia � terminata!";
	private final static String COLLECTION_MENU_TITLE = "Selezionare un'opzione";
	private final static String FINDSTORY_MENU_TITLE = "Inserire il titolo della storia da cercare";
	private final static String PLAYSTORY_MENU_TITLE = "Inserire il titolo della storia da giocare";
	private final static String DIFFICULTY_MENU_TITLE = "Selezionare uno dei seguenti algoritmi per eseguirlo";
	private final static String[] DIFFICULTY_MENU_CHOICES = { "Calcola la dimensione della storia", "Calcola la verbosita' della storia",
															  "Calcola la complessita' dell'intreccio della storia" };
	private final static String DIMENSION_FORMAT = "Dimensione della storia selezionata: %d";
	private final static String VERBOSITY_FORMAT = "Verbosita' della storia selezionata: %.2f";
	private final static String PLOT_COMPLEXITY_FORMAT = "Complessita' dell'intreccio della storia selezionata: %.2f";

	public static void generateStory(Story story, String filename) {
		XMLParser.read(filename, story);

		story.setStartingPoint(story.getParagraphs().get(0));
		story.setEndingPoints();
		story.linkAdjacentParagraphs();
	}
	
	public static void manageInteraction(Story story) { //Si potrebbe fare nel main come pensato inizialmente,
											 			//ma preferisco scrivere l'algoritmo qui per rendere il main pi� pulito
		System.out.println(story);
		Paragraph currentParagraph = story.getStartingPoint();
		
		while(!story.hasFinished(currentParagraph)) {
			MyMenu menu = new MyMenu(currentParagraph.getDescription(), currentParagraph.getOptionsTitle());
			
			int choice = menu.choose();
			
			if(choice == 0)
				break;
			
			currentParagraph = currentParagraph.getOptions().get(choice - 1); //Baste fare cos� poich� i titoli e i rispettivi paragrafi
																	  		  //sono stati aggiunti ordinatamente
		}
		System.out.println(currentParagraph.getDescription());
		System.out.println(END_STORY_MESSAGE);
	}
	
	public static void manageCollection(StoryCollection collection) {
	
		MyMenu menu = new MyMenu(COLLECTION_MENU_TITLE, VOICES);
		int choice;
		
		do {
			choice = menu.choose();
			
			switch(choice) {
				case 0:
					break;
					
				case 1: MyMenu case1Menu = new MyMenu(FINDSTORY_MENU_TITLE, collection.getStories());
						int case1Coiche = case1Menu.choose();
						String case1Story = collection.getStories().get(case1Coiche - 1).getTitle();
						System.out.println(collection.searchStory(case1Story)); //Funzione per la ricerca della storia via titolo,
																				//superflua se si struttura il codice come nel case 2
					break;
					
				case 2: Story case2Story = selectStory(collection);
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
					
				case 6: MyMenu case6Menu = new MyMenu(DIFFICULTY_MENU_TITLE, DIFFICULTY_MENU_CHOICES);
						int case6Coiche = case6Menu.choose();
						Story case6Story = selectStory(collection);

						switch(case6Coiche) {
							case 0:
								break;
							
							case 1: System.out.println(String.format(DIMENSION_FORMAT, case6Story.calculateDimension()));
								break;
							
							case 2: System.out.println(String.format(VERBOSITY_FORMAT, case6Story.calculateVerbosity()));
								break;
							
							case 3: System.out.println(String.format(PLOT_COMPLEXITY_FORMAT, case6Story.calculatePlotComplexity()));
								break;
						}
						
					break;
			}
			
		} while(choice != 0);
		
	}
	
	private static Story selectStory(StoryCollection collection) {
		MyMenu menu = new MyMenu(PLAYSTORY_MENU_TITLE, collection.getStories());
		int choice = menu.choose();
		Story story = collection.getStories().get(choice - 1);
		return story;
	}
		
}