package it.unibs.ing.fp.storiavirtuale;

import java.util.Comparator;

public class StoryAlphabeticalComparator implements Comparator<Story> {
	
	public StoryAlphabeticalComparator() {}

	@Override
	public int compare(Story o1, Story o2) {
		return o1.compareTo(o2);
	}

}
