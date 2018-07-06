package it.unibs.ing.fp.storiavirtuale;

import java.util.ArrayList;

/**
 * Classe che rappresenta un insieme di storie
 * 
 * @author Michele Ferrari
 *
 */
public class StoryCollection {

	private final static String STORY_COLLECTION_HEADING = "\nElenco di tutte le storie: \n";
	
	private ArrayList<Story> stories;
	
	public StoryCollection() {
		stories = new ArrayList<Story>();
	}
	
	public ArrayList<Story> getStories() {
		return stories;
	}

	public void addStory(Story newStory) {
		stories.add(newStory);
	}
	
	/**
	 * Metodo per cercare una storia passando il titolo da cercare
	 * 
	 * @param title il titolo da cercare
	 * @return Story la storia cercata
	 */
	public Story searchStory(String title) {
		for(Story story : stories)
			if(story.getTitle().equals(title))
				return story;
		return null;
	}
	
	@Override
	public String toString() {
		StringBuffer res = new StringBuffer(STORY_COLLECTION_HEADING);
		for(Story story : stories)
			res.append(story.getTitle() + "\n");
		return res.toString();
	}
	
	/**
	 * Metodo per l'ordinamento alfabetico delle storie
	 */
	public void alphabeticalOrdering() { 
		StoryAlphabeticalComparator comp = new StoryAlphabeticalComparator();
		stories.sort(comp);
	}
	
	/**
	 * Metodo per l'ordinamento delle storie secondo il numero di paragrafi
	 */
	public void numberOfParagraphsOrdering() {
		StoryParagraphsComparator comp = new StoryParagraphsComparator();
		stories.sort(comp);
	}
	
}