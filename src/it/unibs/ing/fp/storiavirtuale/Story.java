package it.unibs.ing.fp.storiavirtuale;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Classe che modellizza una storia virtuale a bivi
 * 
 * @author Michele Ferrari
 *
 */
public class Story implements Comparable<Story> {

	private final static String STORY_FORMAT =  "Titolo: %s. La storia si articola in %d capitoli";
	
	private String title;
	private ArrayList<Paragraph> paragraphs;	//cambiata in ArrayList per migliorare efficienza (Utilizzo accessi casuali)
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

	/**
	 * Metodo per trovare i paragrafi finali di una storia
	 * 
	 * @return  ArrayList<Paragraph> l'insieme dei paragrafi finali trovati
	 */
	private ArrayList<Paragraph> findFinalParagraph() {
		ArrayList<Paragraph> temp = new ArrayList<>();
		for(Paragraph par : paragraphs)
			if(par.getId() == (numberOfParagraphs - 1))
				temp.add(par);
		return temp;
	}

	/**
	 * Metodo per associare i paragrafi adiacenti a questo paragrafo
	 * mediante l'utilizzo di un ArrayList ausiliario di interi (idAdjacents)
	 */
	public void linkAdjacentParagraphs() {
		for(int i = 0; i < numberOfParagraphs; i++) {
			Paragraph parentParagraph = paragraphs.get(i);
			for(int j = 0; j < parentParagraph.getIdAdjacents().size(); j++) {
				Paragraph childParagraph = searchParagraph(parentParagraph.getIdAdjacents().get(j));
				parentParagraph.getOptions().add(childParagraph);
			}
		}
			
	}
	
	/**
	 * Metodo per trovare un paragrafo dato il suo id
	 * 
	 * @param id
	 * @return Paragraph il paragrafo cercato
	 */
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

	/**
	 * Metodo per verificare se una storia sia giunta alla fine o meno
	 * 
	 * @param current
	 * @return true se la storia e' finita
	 */
	public boolean hasFinished(Paragraph current) {
		for(Paragraph par : endingPoints)
			if(current.getId() == par.getId())
				return true;
		return false;
	}

	/**
	 * Metodo per l'ordinamento alfabetico
	 */
	@Override
	public int compareTo(Story other) { 
		return this.title.compareTo(other.title);
	}
	
	/**
	 * Metodo per l'ordinamento secondo il numero di paragrafi
	 */
	public int compareToNumberOfParagraphs(Story other) { 
		return Integer.compare(this.numberOfParagraphs, other.numberOfParagraphs);
	}
	
	/**
	 * Metodo implementato per rendere più intuitivo che numberOfParagraphs
	 * individua la dimensione, uno dei parametri usati per calcolare la difficolta'
	 * 
	 * @return int il numero di paragrafi di questa storia
	 */
	public int calculateDimension() { 
		return numberOfParagraphs;	  
	}

	/**
	 * Metodo per calcolare il parametro "verbosita'"
	 * 
	 * @return double il valore di verbosita' medio
	 */
	public double calculateVerbosity() {
		double totalCharacters = 0;
		for(Paragraph par : paragraphs)
			totalCharacters += par.getDescription().length(); //Si potrebbe inserire un .trim() dopo getDescription(),
		return totalCharacters / numberOfParagraphs;		  //ma avendolo gia' inserito nell' XMLParser si puo' evitare 
	}
	
	/**
	 * Metodo per calcolare il parametro "complessita' dell'intreccio'"
	 * 
	 * @return double il valore di complessita' medio
	 */
	public double calculatePlotComplexity() {
		double totalOptions = 0;
		for(int i = 1; i < paragraphs.size() - 1; i++)
			totalOptions += paragraphs.get(i).getOptions().size();
		return totalOptions / (numberOfParagraphs - 2); //Escludo il primo e l'ultimo, come da consegna
	}	
	
	/**
	 * Metodo per calcolare la storia costituita dal numero minimo di paragrafi
	 * 
	 * @return int il valore minimo di paragrafi della storia
	 */
	public int depthFirstSearchShortest() { //Algoritmo per calcolare il percorso minimo nel grafo
		int pathLength = 0;
		int minPathLenght = Integer.MAX_VALUE;
		int counter = 0;
		Stack<Paragraph> nodesToVisit = new Stack<Paragraph>();
		nodesToVisit.push(startingPoint);
		startingPoint.setAlreadyVisited(true);
		
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
					if(!par.isAlreadyVisited()) {
						nodesToVisit.push(par);
						par.setAlreadyVisited(true);
					}
			
			counter++;
		}
		
		return pathLength;
		
	}
	
	/**
	 * Metodo per calcolare il parametro "difficolta' complessiva"
	 * 
	 * @return double il valore di complessita' calcolabile secondo l'algoritmo specificato
	 */
	public double calculateDifficulty() {
		return calculatePlotComplexity() * depthFirstSearchShortest();
	}
	
	//Da qui in poi solo getters e setters
	
	public Paragraph getStartingPoint() {
		return startingPoint;
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

	public void setEndingPoints() {
		endingPoints = findFinalParagraph();
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
	
}