package it.unibs.ing.fp.storiavirtuale;

import java.io.FileInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

/**
 * Classe per la lettura di files XML
 * 
 * @author Michele PC
 *
 */
public class XMLParser {
	
	/**
	 * Metodo static che legge un file XML e salva i dati da esso estrapolati negli attributi di una Story passata come parametro
	 * 
	 * @param fileName
	 * @param story
	 */
	public static void read(String fileName, Story story) {
        
		String storyTitle;
		int ParagraphId;
		int size;
		int linkTo;
		String option;
		String type;
		String description;
		
		String temp = "";
		int cont = 0;
		Paragraph par = null;
        
        try {
        	
            XMLInputFactory xmlif = XMLInputFactory.newInstance();
            XMLStreamReader xmlr = xmlif.createXMLStreamReader(fileName, new FileInputStream(fileName));
            
            while(xmlr.hasNext()) {
            	
                switch(xmlr.getEventType()) {
                    
                	case XMLStreamConstants.START_DOCUMENT:
                		//System.out.println("Start Read Doc "+ fileName);
                		break;
                		
                	case XMLStreamConstants.COMMENT:
                		//System.out.println("Reading comment");
                		break;
	            	
                		
                	case XMLStreamConstants.START_ELEMENT:
                		temp = xmlr.getLocalName();
                		//System.out.println(temp);
                       
                		if(xmlr.getLocalName().equals("story")) {
                			storyTitle = xmlr.getAttributeValue(0);
                			size = Integer.valueOf(xmlr.getAttributeValue(1));
                			story.setTitle(storyTitle);
                			story.setNumberOfParagraphs(size);
                			//System.out.println(storyTitle + " " + size);
                		}
                		
                		if(xmlr.getLocalName().equals("paragraph")) {
                			ParagraphId = Integer.valueOf(xmlr.getAttributeValue(0));
                			type = xmlr.getAttributeValue(1);
                			par = new Paragraph(ParagraphId, type);
                			cont = 0;
                			story.getParagraphs().add(par);
                			//System.out.println(ParagraphId + " " + type);
                		}
                		
                		if(xmlr.getLocalName().equals("option")) {
                			linkTo = Integer.valueOf(xmlr.getAttributeValue(0));
                			par.getIdAdjacents().add(linkTo);
                			//System.out.println(linkTo);
                		}
                		
                		break;
               
            		case XMLStreamConstants.CHARACTERS:
            			if(temp.equals("description") && cont == 0) {
            				description = xmlr.getText().trim();
            				par.setDescription(description);
            				//System.out.println(par.toString());
            				//System.out.println(par.getDescription());
            				cont++; 
            			}
            			
            			if(temp.equals("option")) {
                			option = xmlr.getText().trim();
                			if(option.equals(""))
                				break;
                			par.getOptionsTitle().add(option);            
                			//System.out.println(option);
            			}
            				
            			break;
                    
                	default:
                		break;
                }
                xmlr.next();
            }
            
        }
        
        catch(Exception e){
            System.err.println("Error detected");
            System.err.println(e.getMessage());
        }
    }
}