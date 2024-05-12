import entities.Dictionnaire;
import entities.Document;
import entities.Livre;
import services.ServiceDocumentImpl;

import java.io.IOException;
import java.util.ArrayList;

public class test {

	public static void main(String[] args) {
		
		Document d0 = new Document("ttttttt");
        Document d1 = new Document("titre", 100);
        Document d2 = new Livre("titre2", "hamed",1000);
        Document d3 = new Dictionnaire("habla", "fr",180);
        Document d4= new Dictionnaire("sihamed", "amazigh",130);
        Document d5 = new Dictionnaire("titre5",1000, "eng",200);
        ServiceDocumentImpl k = new ServiceDocumentImpl();
        System.out.println(d0.getNum());
        k.ajouterDoucment(d1);
        k.ajouterDoucment(d2);
        k.ajouterDoucment(d3);
        k.ajouterDoucment(d4);
        k.ajouterDoucment(d5);
        System.out.println("les auteur son : \n"+k.tousLesAuteurs());
        ArrayList<Dictionnaire> D = new ArrayList<>();

		}

}
