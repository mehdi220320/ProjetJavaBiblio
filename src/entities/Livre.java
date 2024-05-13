package entities;

public class Livre  {
	private int id_livre;
	private String auteur;
	private int nbpage;
	private String titre;
	public Livre (String titre,String auteur,int nbpage)
	{

		this.titre=titre;
		this.auteur=auteur;
		this.nbpage=nbpage;
	}

	public Livre (String titre,int num, String auteur,int nbp)
	{

		this.auteur=auteur;
		this.nbpage=nbp;
	}
	@Override
	public String toString()
	{
		return super.toString()+"--- un livre du "+auteur+" et du nbr des page = "+nbpage;
	}

	public int getId_livre() {
		return id_livre;
	}

	public void setId_livre(int id_livre) {
		this.id_livre = id_livre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public int getNbpage() {
		return nbpage;
	}

	public void setNbpage(int nbpage) {
		this.nbpage = nbpage;
	}


	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
}
