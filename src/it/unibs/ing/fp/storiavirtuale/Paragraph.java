package it.unibs.ing.fp.storiavirtuale;

import java.util.ArrayList;

public class Paragraph implements Comparable<Paragraph> {

	private String description;
	private int id;
	private ArrayList<Paragraph> options; //cambiata in ArrayList per migliorare efficienza (Utilizzo accessi casuali)
	private String type;
	private ArrayList<Integer> IdAdjacents;
	private ArrayList<String> optionsTitle;
	
	public Paragraph(int id, String type) {
		this.id = id;
		this.type = type;
		options = new ArrayList<>();
		IdAdjacents = new ArrayList<>();
		optionsTitle = new ArrayList<>();
	}
	
	public boolean isFinalParagraph() {
		return options.isEmpty();
	}
	public boolean isInitialParagraph() {
		return id == 0;
	}
	
	public void addOption(Paragraph newOption) { //metodo aggiunto per migliorare la pulizia del codice
		options.add(newOption);
	}
	
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
	
}