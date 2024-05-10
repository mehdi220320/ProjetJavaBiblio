public class Livre extends Document{
	private String auteur;
	private int nbp;
	public Livre (String titre,String auteur,int nbp)
	{
		super(titre);
		this.auteur=auteur;
		this.nbp=nbp;
	}
	public Livre (String titre,int num, String auteur,int nbp)
	{
		super(titre,num);
		this.auteur=auteur;
		this.nbp=nbp;
	}
	@Override
	public String toString()
	{
		return super.toString()+"--- un livre du "+auteur+" et du nbr des page = "+nbp;
	}
	public String getAuteur() {
		return auteur;
	}
	
	
}
