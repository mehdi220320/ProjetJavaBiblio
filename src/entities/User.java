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
private int nblivre ;
    public User() {

    }

    public int getNblivre() {
        return nblivre;
    }

    public void setNblivre(int nblivre) {
        this.nblivre = nblivre;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nblivre='" + nblivre + '\'' +
                '}';
    }


    public User(String nom, String prenom, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
this.role="user";
        this.nblivre=0 ;

    }

    public User(String nom, String prenom, String email, String password, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
        this.nblivre=0 ;
    }

    public boolean equals(User us) {
        if(this.nom.equals(us.getNom())||this.prenom.equals(us.getPrenom())||this.email.equals(us.getEmail()))
            return true;
        else
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
