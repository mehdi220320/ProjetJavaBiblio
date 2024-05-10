package services;

import entities.Document;

public interface ServiceDocument {
	public void ajouterDoucment(Document document);
	public Document trouverDocument(int num) ;
}
