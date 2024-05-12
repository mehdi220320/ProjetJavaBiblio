package entities;

import java.util.Map;
import java.util.TreeMap;

public class User {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String role;

    public User() {

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private Map<Document,Integer> ListedesDocuments;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    public User(String nom, String prenom, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        ListedesDocuments=new TreeMap<>();

    }

    public User(String nom, String prenom, String email, String password, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public boolean equals(User us) {
        if(this.nom.equals(us.getNom())||this.prenom.equals(us.getPrenom())||this.email.equals(us.getEmail()))
            return true;
        else
            return false;
    }
    public Boolean AjouterDocumentsListe(Document document){
        if(alreadyExiste(document))
            return false;
        ListedesDocuments.put(document,0);
        return  true;
    }
    public void setListedesDocuments(Map<Document, Integer> listedesDocuments) {
        ListedesDocuments = listedesDocuments;
    }
    public Map<Document, Integer> getListedesDocuments() {
        return ListedesDocuments;
    }
    public Boolean alreadyExiste(Document document){
        for(Document doc:ListedesDocuments.keySet()){
            if(doc.equal(document)){
                return  false;
            }
        }
        return true;
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
