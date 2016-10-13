package gwenta.logic;

import java.io.Serializable;

public abstract class ACard implements Serializable{

	protected int id;
	protected String Texture;
	/*private String range;
	private	int atack;*/
	//private String description;
	
	protected ACard()
	{

	}

	public String findRange(){
		int id= this.getId();
		if(id==1)
		{
			return "Melee";
		}
		else if(id==2)
		{
			return "Ranged";
		}
		else if(id==3)
		{
			return "Siege";
		}
		else if(id>=4 && id<=7)
		{
			return "Melee";
		}
		else if(id>=8 && id <=15)
		{
			return "Melee";
		}
		else if(id>=16 && id <=27)
		{
			return "Ranged";
		}
		else if(id>=28 && id <=33)
		{
			return "Siege";
		}
		
		return "";
	}
	public String findEffect(){
		int id= this.getId();
		if(id>=1 && id <=7)
		{
			return "Magic";
		}
		else if(id>=8 && id<=11)
		{
			return "Unidade";
		}
		else if(id==12)
		{
			return "Spy";
		}
		else if(id==13)
		{
			return "Unidade";
		}
		else if(id==14)
		{
			return "Scorcher";
		}
		else if(id==15)
		{
			return "Booster";
		}
		else if(id>=16&&id<=19)
		{
			return "Unidade";
		}
		else if(id==20)
		{
			return "Spy";
		}
		else if(id==21)
		{
			return "Unidade";
		}
		else if(id==22)
		{
			return "Unidade";
		}
		else if(id==23)
		{
			return "Reviver";
		}
		else if(id==24)
		{
			return "Reviver";
		}
		else if(id==25)
		{
			return "Scorcher";
		}
		else if(id==26)
		{
			return "Booster";
		}
		else if(id==27)
		{
			return "Booster";
		}
		else if(id>=28 && id<=30)
		{
			return "Unidade";
		}
		else if(id==31)
		{
			return "Spy";
		}
		else if(id==32)
		{
			return "Reviver";
		}
		else if(id==33)
		{
			return "Booster";
		}
		return "";
	}


	public abstract  int getId();

	protected abstract String getTexture();

	public int findPower(){
		int id= this.getId();
		switch (id){
			case 8:
				return  4;

			case 9:
				return  5;

			case 10:
				return  6;

			case 11:
				return  8;

			//spy
			case 12:
				return  5;

			//hero
			case 13:
				return  15;

			//scorcher
			case 14:
				return  4;

			//booster
			case 15:
				return  3;

			//rangeds start
			case 16:
				return  3;

			case 17:
				return  4;

			case 18:
				return  5;

			case 19:
				return  9;

			//spy
			case 20:
				return  4;

			//Hero
			case 21:
				return  8;

			//hero
			case 22:
				return  10;

			//reviver
			case 23:
				return  4;

			//reviver hero
			case 24:
				return  8;

			//scorcher hero
			case 25:
				return 10;

			//booster
			case 26:
				return 1;

			//booster hero
			case 27:
				return 6;

			//sieges
			case 28:
				return 8;

			case 29:
				return 9;

			case 30:
				return 10;

			//spy
			case 31:
				return 5;

			//reviver
			case 32:
				return 6;

			//booster
			case 33:
				return 1;





		}
		return 0;
	}
	
	public int findPower(int id){
		
		switch (id){
			case 8:
				return  4;

			case 9:
				return  5;

			case 10:
				return  6;

			case 11:
				return  8;

			//spy
			case 12:
				return  5;

			//hero
			case 13:
				return  15;

			//scorcher
			case 14:
				return  4;

			//booster
			case 15:
				return  3;

			//rangeds start
			case 16:
				return  3;

			case 17:
				return  4;

			case 18:
				return  5;

			case 19:
				return  9;

			//spy
			case 20:
				return  4;

			//Hero
			case 21:
				return  8;

			//hero
			case 22:
				return  10;

			//reviver
			case 23:
				return  4;

			//reviver hero
			case 24:
				return  8;

			//scorcher hero
			case 25:
				return 10;

			//booster
			case 26:
				return 1;

			//booster hero
			case 27:
				return 6;

			//sieges
			case 28:
				return 8;

			case 29:
				return 9;

			case 30:
				return 10;

			//spy
			case 31:
				return 5;

			//reviver
			case 32:
				return 6;

			//booster
			case 33:
				return 1;





		}
		return 0;
	}
	
	
}
