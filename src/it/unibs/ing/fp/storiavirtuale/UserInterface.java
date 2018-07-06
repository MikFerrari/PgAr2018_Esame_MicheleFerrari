package it.unibs.ing.fp.storiavirtuale;

import it.unibs.ing.fp.mylib.MyMenu;

public class UserInterface {
	
	public static void generateStory(Story story) {
		XMLParser.read("PgAr2018_Story_2.1.xml", story);

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
			
			currentParagraph = currentParagraph.getOptions().get(choice - 1); //Baste fare così poichè i titoli e i rispettivi paragrafi
																	  		  //sono stati aggiunti ordinatamente
		}
		
	}
	
}
