package it.unibs.ing.fp.storiavirtuale;

//import it.unibs.ing.fp.mylib.InputDati;

/**
 * Classe contenente il main, sono indicate con il commento "//Codice del nucleo" le righe
 * di codice che da sole implementavano il nucleo del programma nella sua versione originale.
 * 
 * La classe main prevede la lettura di due storie di default, affinchè possa essere testata immediatamente.
 * Sono indicate come commenti le linee di codice che permettono di generalizzare l'inserimento dei nomi dei file da leggere.
 * 
 * @author Michele Ferrari
 *
 */
public class Main {

	private final static String WELCOME_MESSAGE = "Benvenuto nel nostro portale per storie virtuali!";
	private final static String BYE_MESSAGE = "Grazie per aver giocato. A presto!";
//	private final static String FILENAME_MESSAGE = "Inserire il nome del file XML che si vuole leggere: ";
	
	public static void main(String[] args) {
		
		StoryCollection collection = new StoryCollection();
		Story story1 = new Story();	//Codice del nucleo
		Story story2 = new Story();
		
		welcome();	//Codice del nucleo
		
//		String fileName = InputDati.leggiStringaNonVuota(FILENAME_MESSAGE); Queste due righe di codice consentono all'utente di inserire
//		UserInterface.generateStory(story1, fileName);						un qualsiasi nome di file XML da leggere.
//																			Le lascio commentate per agevolare la fase di testing.

		
		UserInterface.generateStory(story1, "PgAr2018_Story_2.1.xml");	//Codice del nucleo
		UserInterface.generateStory(story2, "PgAr2018_Story_2.2.xml");
		
		collection.addStory(story1);
		collection.addStory(story2);
		
		UserInterface.manageCollection(collection);
		
		
		// UserInterface.manageInteraction(story1);		//Codice del nucleo
		
		
		bye();	//Codice del nucleo
		
		
	}
	
	private static void welcome() {
		System.out.println(WELCOME_MESSAGE);
	}
	
	private static void bye() {
		System.out.println(BYE_MESSAGE);
	}

}
