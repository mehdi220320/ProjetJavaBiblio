package entities;

import entities.Document;

import java.util.Map;
import java.util.TreeMap;

public class etudiant extends User {

    private String school;
    private Map<Document,Integer> ListedesDocuments;

    public etudiant(long id, String nom, String prenom, String email, String password) {
        super(id, nom, prenom, email, password);
        ListedesDocuments=new TreeMap<>();

    }
    public Boolean alreadyExiste(Document document){
        for(Document doc:ListedesDocuments.keySet()){
            if(doc.equal(document)){
                return  false;
            }
        }
        return true;
    }
    public Boolean AjouterDocumentsListe(Document document){
        if(alreadyExiste(document))
            return false;
        ListedesDocuments.put(document,0);
        return  true;
    }
    public Boolean updatePages(String titre,int nbpages){
        for(Document doc:ListedesDocuments.keySet()){
            if(titre.equals(doc.getTitre())){
                ListedesDocuments.put(doc, nbpages);
                return true;
            }
        }
        System.out.println("entities.Document doesn't exist");
        return false;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Map<Document, Integer> getListedesDocuments() {
        return ListedesDocuments;
    }

    public void setListedesDocuments(Map<Document, Integer> listedesDocuments) {
        ListedesDocuments = listedesDocuments;
    }
}
