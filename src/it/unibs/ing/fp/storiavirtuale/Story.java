package it.unibs.ing.fp.storiavirtuale;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Story implements Comparable<Story> {

	private final static String STORY_FORMAT =  "Titolo: %s. La storia si articola in %d capitoli";
	
	private String title;
	private ArrayList<Paragraph> paragraphs; ////cambiata in ArrayList per migliorare efficienza (Utilizzo accessi casuali)
	private Paragraph startingPoint;
	private ArrayList<Paragraph> endingPoints;
	private int numberOfParagraphs;
	
	public Story() {
		paragraphs = new ArrayList<>();
		startingPoint = null;
		endingPoints = new ArrayList<>();
	}
	
	public Story(String title, int numberOfParagraphs) {
		paragraphs = new ArrayList<>();
		startingPoint = null;
		endingPoints = new ArrayList<>();
		this.title = title;
		this.numberOfParagraphs = numberOfParagraphs;
	}

	public void setStartingPoint(Paragraph startingPoint) {
		this.startingPoint = startingPoint;
	}

	public ArrayList<Paragraph> getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(ArrayList<Paragraph> paragraphs) {
		this.paragraphs = paragraphs;
	}

	public Paragraph getStartingPoint() {
		return startingPoint;
	}

	public void setNumberOfParagraphs(int numberOfParagraphs) {
		this.numberOfParagraphs = numberOfParagraphs;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	private ArrayList<Paragraph> findFinalParagraph() {
		ArrayList<Paragraph> temp = new ArrayList<>();
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
	
	@Override
	public String toString() {
		return String.format(STORY_FORMAT, title, numberOfParagraphs);
	}

	public boolean hasFinished(Paragraph current) {
		for(Paragraph par : endingPoints)
			if(current.getId() == par.getId())
				return true;
		return false;
	}

	@Override
	public int compareTo(Story other) { //Per l'ordinamento alfabetico
		return this.title.compareTo(other.title);
	}
	
	public int compareToNumberOfParagraphs(Story other) { //Per l'ordinamento secondo il numero di paragrafi
		return Integer.compare(this.numberOfParagraphs, other.numberOfParagraphs);
	}
	
	public int calculateDimension() { //Metodo implementato per rendere più intuitivo che numberOfParagraphs
		return numberOfParagraphs;	  //individua la dimensione, uno dei parametri usati per calcolare la difficolta'
	}
	
	public double calculateVerbosity() {
		double totalCharacters = 0;
		for(Paragraph par : paragraphs)
			totalCharacters += par.getDescription().length(); //Si potrebbe inserire un .trim() dopo getDescription(),
		return totalCharacters / numberOfParagraphs;		  //ma avendolo gia' inserito nell' XMLParser si puo' evitare 
	}
	
	public double calculatePlotComplexity() {
		double totalOptions = 0;
		for(int i = 1; i < paragraphs.size() - 1; i++)
			totalOptions += paragraphs.get(i).getOptions().size();
		return totalOptions / (numberOfParagraphs - 2); //Escludo il primo e l'ultimo, come da consegna
	}	
	
	public int depthFirstSearch() {
		int pathLength = 0;
		int minPathLenght = Integer.MAX_VALUE;
		int counter = 0;
		Stack<Paragraph> nodesToVisit = new Stack<Paragraph>();
		nodesToVisit.push(startingPoint);
		
		while(!nodesToVisit.isEmpty()) {
			Paragraph currentNode = nodesToVisit.pop();
			if(currentNode.isFinalParagraph()) {
				pathLength = counter;
				counter -= 2; //Tolgo 2 perchè alla fine reincremento di 1
				if(pathLength < minPathLenght)
					minPathLenght = pathLength;
			}
			
			if(!currentNode.isFinalParagraph())
				for(Paragraph par : currentNode.getOptions())
					nodesToVisit.push(par);
			
			counter++;
		}
		
		return pathLength;
		
	}
	
}
