package entities;

public class demande {

    private int id ;
    private String titre ;
    private String auteur ;
private int user_id ;

    public demande(String titre, String auteur, int user_id) {
        this.titre = titre;
        this.auteur = auteur;
        this.user_id = user_id;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
