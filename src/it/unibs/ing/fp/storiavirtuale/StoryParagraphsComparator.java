package it.unibs.ing.fp.storiavirtuale;

import java.util.Comparator;

/**
 * Comparatore per l'ordinamento delle storie secondo il numero di paragrafi
 * 
 * @author Michele PC
 *
 */
public class StoryParagraphsComparator implements Comparator<Story> {

	public StoryParagraphsComparator() {}
	
	@Override
	public int compare(Story o1, Story o2) {
		return o1.compareToNumberOfParagraphs(o2);
	}

}
