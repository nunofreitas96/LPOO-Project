package gwenta.logic;

import java.util.ArrayList;
import java.util.Scanner;

public class Tabuleiro {

	public ArrayList<Card> baralho = new ArrayList<Card>();
	public ArrayList<Card> baralho2 = new ArrayList<Card>();
	public ArrayList<Card> melee = new ArrayList<Card>();
	public ArrayList<Card> ranged = new ArrayList<Card>();
	public ArrayList<Card> siege = new ArrayList<Card>();
	public ArrayList<Card> melee2 = new ArrayList<Card>();
	public ArrayList<Card> ranged2 = new ArrayList<Card>();
	public ArrayList<Card> siege2 = new ArrayList<Card>();
	public int pontosMelee=0;
	public int pontosRanged=0;
	public int pontosSiege=0;
	public int pontosTotal=0;
	public int pontosMelee2=0;
	public int pontosRanged2=0;
	public int pontosSiege2=0;
	public int pontosTotal2=0;
	public int jogada=0;
	public int turno=0;
	public int vida=2;
	public int vida2=2;
	
	public void initialize()
	{
		baralho.add(new Card(1,2,"Melee","Magic"));
		baralho.add(new Card(2,1,"Siege","Magic"));
		baralho.add(new Card(3,2,"Ranged","Magic"));
		baralho.add(new Card(4,3,"Melee","Magic"));
		baralho.add(new Card(5,1,"Siege","Magic"));
		baralho.add(new Card(6,5,"Ranged","Magic"));
		baralho.add(new Card(7,2,"Melee","Magic"));
		baralho.add(new Card(8,7,"Melee","Reviver"));
		baralho.add(new Card(9,8,"Melee","Scorcher"));
		baralho.add(new Card(10,10,"Melee","Unidade"));
		
		baralho2.add(new Card(8,2,"Melee","Unidade"));
		baralho2.add(new Card(8,1,"Siege","Unidade"));
		baralho2.add(new Card(8,2,"Ranged","Unidade"));
		baralho2.add(new Card(8,3,"Melee","Unidade"));
		baralho2.add(new Card(8,1,"Siege","Unidade"));
		baralho2.add(new Card(8,5,"Ranged","Unidade"));
		baralho2.add(new Card(8,2,"Melee","Unidade"));
		baralho2.add(new Card(8,7,"Siege","Unidade"));
		baralho2.add(new Card(8,8,"Melee","Unidade"));
		baralho2.add(new Card(8,10,"Ranged","Unidade"));
		
	}
	
	public void playCard(/*Card card*/int index)
	{
		if (baralho.get(index).getRange()=="Melee")
		{
			melee.add(baralho.get(index));
			pontosMelee+=baralho.get(index).getAtack();
			baralho.remove(baralho.get(index));
			baralho.trimToSize();
			//pontosTotal+=baralho.get(index).getAtack();
			
		}
		else if (baralho.get(index).getRange()=="Ranged")
		{
			ranged.add(baralho.get(index));
			pontosRanged+=baralho.get(index).getAtack();
			baralho.remove(baralho.get(index));
			baralho.trimToSize();
			//pontosTotal+=baralho.get(index).getAtack();
			
		}
		else if (baralho.get(index).getRange()=="Siege")
		{
			siege.add(baralho.get(index));
			pontosSiege+=baralho.get(index).getAtack();
			baralho.remove(baralho.get(index));
			baralho.trimToSize();
			//pontosTotal+=baralho.get(index).getAtack();
			
		}
		pontosTotal= pontosSiege+pontosMelee+pontosRanged;
	}
	public void playCardAi()
	{
		int index = 0 + (int)(Math.random() * (baralho2.size()-1)); 
		if (baralho2.get(index).getRange()=="Melee")
		{
			melee2.add(baralho2.get(index));
			pontosMelee2+=baralho2.get(index).getAtack();
			baralho2.remove(baralho2.get(index));
			baralho2.trimToSize();
			//pontosTotal+=baralho.get(index).getAtack();
			
		}
		else if (baralho2.get(index).getRange()=="Ranged")
		{
			ranged2.add(baralho2.get(index));
			pontosRanged2+=baralho2.get(index).getAtack();
			baralho2.remove(baralho2.get(index));
			baralho2.trimToSize();
			//pontosTotal+=baralho.get(index).getAtack();
			
		}
		else if (baralho2.get(index).getRange()=="Siege")
		{
			siege2.add(baralho2.get(index));
			pontosSiege2+=baralho2.get(index).getAtack();
			baralho2.remove(baralho2.get(index));
			baralho2.trimToSize();
			//pontosTotal+=baralho.get(index).getAtack();
			
		}
		pontosTotal2= pontosSiege2+pontosMelee2+pontosRanged2;
		
		
	}
	public void turno()
	{	
		int i;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Selecione a carta a jogar (indice)");
		i= sc.nextInt();
		playCard(i);
		playCardAi();
		jogada++;
	}
	
	public void play()
	{
		while(turno!=3)
		{
			
			turno();
			tabuleiroDraw();
			if(jogada==3)
			{
				turno++;
				if(pontosTotal<pontosTotal2)
				{
					vida2--;
				}
				else
				{
					vida--;
				}
				if(vida==0)
				{
					System.out.println("YOU LOSE!");
					break;
				}
				if(vida2==0)
				{
					System.out.println("YOU WIN!");
					break;
				}
				melee.clear();
				ranged.clear();
				siege.clear();
				pontosTotal=0;
				pontosMelee=0;
				pontosSiege=0;
				pontosRanged=0;
				melee2.clear();
				ranged2.clear();
				siege2.clear();
				pontosTotal2=0;
				pontosMelee2=0;
				pontosSiege2=0;
				pontosRanged2=0;
				jogada=0;
			}
			
		}
	}
	
	public void tabuleiroDraw()
	{
		//Parte inimiga
		for(int i=0;i< baralho2.size();i++)
		{
			System.out.print(baralho2.get(i).getAtack()+" ");
		}
		System.out.println(" ");
		
		System.out.println(pontosTotal2);
		
		System.out.print(pontosSiege2+" Siege: ");
		for(int i=0;i< siege2.size();i++)
		{
			System.out.print(siege2.get(i).getAtack()+" ");
		}
		System.out.println(" ");
		
		System.out.print(pontosRanged2+" Ranged: ");
		for(int i=0;i< ranged2.size();i++)
		{
			System.out.print(ranged2.get(i).getAtack()+" ");
		}
		System.out.println(" ");
		
		System.out.print(pontosMelee2+" Melee: ");
		for(int i=0;i< melee2.size();i++)
		{
			System.out.print(melee2.get(i).getAtack()+" ");
		}
		System.out.println(" ");
		//Separacao
		System.out.println("----------------");
		//Parte jogador
		System.out.print(pontosMelee+" Melee: ");
		for(int i=0;i< melee.size();i++)
		{
			System.out.print(melee.get(i).getAtack()+" ");
		}
		System.out.println(" ");
		
		System.out.print(pontosRanged+" Ranged: ");
		for(int i=0;i< ranged.size();i++)
		{
			System.out.print(ranged.get(i).getAtack()+" ");
		}
		System.out.println(" ");
		
		System.out.print(pontosSiege+" Siege: ");
		for(int i=0;i< siege.size();i++)
		{
			System.out.print(siege.get(i).getAtack()+" ");
		}
		System.out.println(" ");
		
		System.out.println(pontosTotal);
		
		for(int i=0;i< baralho.size();i++)
		{
			System.out.print(baralho.get(i).getAtack()+" ");
		}
		System.out.println(" ");
	}
}
