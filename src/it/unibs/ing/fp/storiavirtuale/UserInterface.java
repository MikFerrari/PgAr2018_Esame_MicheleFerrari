package it.unibs.ing.fp.storiavirtuale;

public class UserInterface {

	public static void generateStory(Story story) {
		XMLParser.read("PgAr2018_Story_2.1.xml", story);

		story.setStartingPoint(story.getParagraphs().get(0));
		story.setEndingPoints();
		story.linkAdjacentParagraphs();
	}
	
	public static void manageInteraction(Story story) { //Si potrebbe fare nel main come pensato inizialmente,
											 			//ma preferisco scrivere l'algoritmo qui per rendere il main più pulito
		
	}
	
}
