package it.unibs.ing.fp.storiavirtuale;

import java.util.ArrayList;

/**
 * Classe che modellizza un paragrafo di una storia
 * 
 * @author Michele Ferrari
 *
 */
public class Paragraph implements Comparable<Paragraph> {

	private String description;
	private int id;
	private ArrayList<Paragraph> options; //cambiata in ArrayList per migliorare efficienza (Utilizzo accessi casuali)
	private String type; //Attributo utile per un Modulo che non e' stato sviluppato, il parser lo legge comunque
	private ArrayList<Integer> IdAdjacents;
	private ArrayList<String> optionsTitle;
	private boolean alreadyVisited = false;
	
	public Paragraph(int id, String type) {
		this.id = id;
		this.type = type;
		options = new ArrayList<>();
		IdAdjacents = new ArrayList<>();
		optionsTitle = new ArrayList<>();
	}
	
	/**
	 * Metodo per verificare che un paragrafo sia terminale
	 * 
	 * @return true se il paragrafo è un paragrafo finale
	 */
	public boolean isFinalParagraph() {
		return options.isEmpty();
	}
	
	/**
	 * Metodo per verificare che un paragrafo sia iniziale
	 * 
	 * @return true se il paragrafo è un paragrafo iniziale
	 */
	public boolean isInitialParagraph() {
		return id == 0;
	}
	
	public void addOption(Paragraph newOption) { //metodo aggiunto per migliorare la pulizia del codice
		options.add(newOption);
	}
	
	/**
	 * Metodo per confrontare due paragrafi sulla base del loro id
	 * 
	 * @param Paragraph other
	 */
	@Override
	public int compareTo(Paragraph other) {
		return Integer.compare(this.id, other.id);
	}
	
	@Override
	public String toString() {
		StringBuffer res = new StringBuffer("");
		res.append(description);
		for(int i = 0; i < options.size(); i++) {
			res.append("OPTION " + (i + 1) + ": " + options.get(i).description);
		}
			
		return res.toString();
	}

	//Da qui in poi solamente getters e setters
	
	public ArrayList<Integer> getIdAdjacents() {
		return IdAdjacents;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public ArrayList<String> getOptionsTitle() {
		return optionsTitle;
	}

	public int getId() {
		return id;
	}

	public ArrayList<Paragraph> getOptions() {
		return options;
	}

	public boolean isAlreadyVisited() {
		return alreadyVisited;
	}

	public void setAlreadyVisited(boolean alreadyVisited) {
		this.alreadyVisited = alreadyVisited;
	}
	
}