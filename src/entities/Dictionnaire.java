package entities;

public class Dictionnaire extends Document {
	private String langue;
	private int nbt;
	public Dictionnaire(String titre , String langue ,int nbt)
	{
		super(titre);
		this.langue=langue;
		this.nbt=nbt;
	}
	public Dictionnaire(String titre ,int num, String language ,int nbt)
	{
		super(titre,num);
		this.langue=language;
		this.nbt=nbt;
	}
	@Override
	public String toString()
	{
		return super.toString()+"--- un dictionnaire du langue "+langue+" et du nombre de tombes ="+nbt;
	}
	
}
