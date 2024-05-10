package entities;

public class Document {
	private String titre;
	private static int num=-1;
	int id;
	
	public Document (String titre)
 {
	 this.titre=titre;
	 this.num++;
	this.id=this.num;
 }
 	public Document(String titre,int num)
 {
	 this.titre=titre;
	 if(this.num<num)
	 {
			this.num=num;
	 }
	 else
		 this.num++;
	 this.id=this.num;
 }
 	public Boolean equal(Document d){
		if (d.titre.equals(this.titre)&& d.id==this.id )
			return true;
		return false;
	}
	public String getTitre() {
		return titre;
	}
	public  int getNum() {
		return this.id;
	}
	@Override
	public String toString()
	{
		return "titre= "+this.titre+" num : "+this.id;
	}
 	
 
 
}
