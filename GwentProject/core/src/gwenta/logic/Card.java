package gwenta.logic;

public class Card {

	private int id;
	private String range;
	private	int atack;
	private String description,efeito;
	
	public Card()
	{
		this.id=1;
		this.atack=3;
		this.range="Melee";
		this.description= "you know nothing Jonh Snow";
	}
	//TEST PURPOSES
	public Card(int atack,String range)
	{
		this.id=1;
		this.atack=atack;
		this.range=range;
		this.description= "you know nothing Jonh Snow";
	}
	public Card(int id,int atack,String range,String efeito)
	{
		this.id=id;
		this.atack=atack;
		this.range=range;
		this.efeito=efeito;
		this.description= "you know nothing Jonh Snow";
	}
	public String getRange() {
		return range;
	}

	public int getId() {
		return id;
	}

	public int getAtack() {
		return atack;
	}
	
	public String getEfeito(){
		return efeito;
	}

	public String getDescription() {
		return description;
	};
	
	
	
	
}
