package it.unibs.ing.fp.storiavirtuale;

import java.util.ArrayList;

public class StoryCollection {

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
	
	public Story searchStory(String title) {
		for(Story story : stories)
			if(story.getTitle().equals(title))
				return story;
		return null;
	}
	
	@Override
	public String toString() {
		StringBuffer res = new StringBuffer("\nElenco di tutte le storie: \n");
		for(Story story : stories)
			res.append(story.getTitle() + "\n");
		return res.toString();
	}
	
	public void alphabeticalOrdering() { //Ordinamento alfabetico delle storie
		StoryAlphabeticalComparator comp = new StoryAlphabeticalComparator();
		stories.sort(comp);
	}
	
	public void numberOfParagraphsOrdering() { //Ordinamento delle storie secondo il numero di paragrafi
		StoryParagraphsComparator comp = new StoryParagraphsComparator();
		stories.sort(comp);
	}
	
}
