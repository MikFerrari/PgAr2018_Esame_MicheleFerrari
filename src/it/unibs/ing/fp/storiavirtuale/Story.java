package it.unibs.ing.fp.storiavirtuale;

import java.util.LinkedList;

public class Story {

	private String title;
	private LinkedList<Paragraph> paragraphs;
	private Paragraph startingPoint;
	private LinkedList<Paragraph> endingPoints;
	private int numberOfParagraphs;
	
	public Story() {
		paragraphs = new LinkedList<>();
		startingPoint = null;
		endingPoints = new LinkedList<>();
	}
	
	public Story(String title, int numberOfParagraphs) {
		paragraphs = new LinkedList<>();
		startingPoint = null;
		endingPoints = new LinkedList<>();
		this.title = title;
		this.numberOfParagraphs = numberOfParagraphs;
	}

	public void setStartingPoint(Paragraph startingPoint) {
		this.startingPoint = startingPoint;
	}

	public LinkedList<Paragraph> getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(LinkedList<Paragraph> paragraphs) {
		this.paragraphs = paragraphs;
	}

	public Paragraph getStartingPoint() {
		return startingPoint;
	}

	public void setNumberOfParagraphs(int numberOfParagraphs) {
		this.numberOfParagraphs = numberOfParagraphs;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	private LinkedList<Paragraph> findFinalParagraph() {
		LinkedList<Paragraph> temp = new LinkedList<>();
		for(Paragraph par : paragraphs)
			if(par.getId() == (numberOfParagraphs - 1))
				temp.add(par);
		return temp;
	}

	public void setEndingPoints() {
		endingPoints = findFinalParagraph();
	}
	
	public void linkAdjacentParagraphs() {
		for(int i = 0; i < numberOfParagraphs; i++) {
			Paragraph parentParagraph = paragraphs.get(i);
			for(int j = 0; j < parentParagraph.getIdAdjacents().size(); j++) {
				Paragraph childParagraph = searchParagraph(parentParagraph.getIdAdjacents().get(j));
				parentParagraph.getOptions().add(childParagraph);
			}
		}
			
	}
	
	private Paragraph searchParagraph(int id) {
		for(Paragraph par : paragraphs)
			if(par.getId() == id)
				return par;
		return null;
	}
	
}
