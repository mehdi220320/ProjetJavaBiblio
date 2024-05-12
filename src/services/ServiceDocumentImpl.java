package services;

import entities.Dictionnaire;
import entities.Document;
import entities.Livre;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ServiceDocumentImpl implements ServiceDocument {
    private ArrayList<Document> T = new ArrayList<>();
    
    @Override
	public void ajouterDoucment(Document document) {
	
        T.add(document);
    }
    @Override
	public Document trouverDocument(int num) 
    {
    	int i;
    	boolean test=false;
    	for (i=0;i<T.size();i++)
    	{
    		if (T.get(i).getNum()==num)
    			{test=true;
    			break;}
    	}
    	if (test)
    		return T.get(i);
    	else
    		return null;
    	
    }
    public String  tousLesAuteurs()
    {
    	String auteur="";
    	for (Document T: T)
    	{
    		if( T instanceof Livre)
    			auteur+=((Livre)T).getAuteur()+" ";
    	}
    	return auteur;
    }
    public ArrayList<Dictionnaire> tousLesDictionnaires() {
    
        ArrayList<Dictionnaire> D = new ArrayList<>();
    	for (Document T:T)
    	{
    		if (T instanceof Dictionnaire)
    			D.add((Dictionnaire)T);
    	}
    	return D;
    }

}
