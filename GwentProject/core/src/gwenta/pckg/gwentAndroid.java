package gwenta.pckg;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import gwenta.Gwent;
import gwenta.logic.ACard;
import gwenta.logic.Card;
import gwenta.logic.Tabuleiro;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * Screen de jogo 
 * @author Paulo Babo
 *
 */
public class gwentAndroid implements Screen{
	final Gwent game;
	Texture tabuleiro;
	Sprite tab;
	Texture roundText;
	Sprite round;
	Texture buttonText;
	Sprite button;
	Texture buttonClickedText;
	Sprite buttonClicked;
	Texture winText;
	Sprite win;
	Texture loseText;
	Sprite lose;
	Texture blizzardText;
	Sprite blizzard;
	Texture exitText;
	Sprite exit;
	Button btn;
	Button btnSiege;
	Button btnMelee;
	Button btnRanged;
	Button btnExit;
	Texture meleeText;
	Sprite meleeSprite;
	Texture rangedText;
	Sprite rangedSprite;
	Texture siegeText;
	Sprite siegeSprite;
	Stage stage;
	Texture empowerText;
	Sprite empower;
	Tabuleiro jogo;
	TextureAtlas blizzardAtlas;
	Animation blizzardAnimation;
	TextureAtlas empowerAtlas;
	Animation empowerAnimation;
	TextureAtlas sunnyAtlas;
	Animation sunnyAnimation;
	Saver saver;
	ArrayList<MyCard> cartas= new ArrayList<MyCard>();
	ArrayList<MyCard> melee= new ArrayList<MyCard>();
	ArrayList<MyCard> ranged= new ArrayList<MyCard>();
	ArrayList<MyCard> siege= new ArrayList<MyCard>();
	ArrayList<MyCard> cartasEnemie= new ArrayList<MyCard>();
	ArrayList<MyCard> meleeEnemie= new ArrayList<MyCard>();
	ArrayList<MyCard> rangedEnemie= new ArrayList<MyCard>();
	ArrayList<MyCard> siegeEnemie= new ArrayList<MyCard>();
	ArrayList<MyCard> graveyard= new ArrayList<MyCard>();
	ArrayList<MyCard> graveyardEnemie= new ArrayList<MyCard>();
	ArrayList<ArrayList<ACard>> Decks = new ArrayList<ArrayList<ACard>>();
	ArrayList<ACard> decktmp= new ArrayList<ACard>();
	ArrayList<MyCard> deck= new ArrayList<MyCard>();
	ArrayList<MyCard> deckEnemie= new ArrayList<MyCard>();
	int pontosRanged=0, pontosMelee=0,pontosSiege=0,totalPontos=0, jogada=0,vida=2;
	int pontosRanged2=0, pontosMelee2=0,pontosSiege2=0,totalPontos2=0, jogada2=0,vida2=2;
	boolean wait;
	int passTurn=0, passTurnEnemie=0, decoychecker=0;
	float timepassed=0;
	float timepassedsunny=0;
	ArrayList<Integer> states= new ArrayList<Integer>();
	BitmapFont pontosRangedtxt,pontosMeleetxt,pontosSiegetxt,totalPontostxt;
	OrthographicCamera camera;
	Viewport viewport;
	
	/**
	 * Construtor do ecra de jogo
	 * @param gam jogo principal
	 */
	public gwentAndroid(final Gwent gam) {
		camera = new OrthographicCamera();
		viewport = new StretchViewport(1280, 720, camera);
		game = gam;
		tabuleiro= new Texture ("campo.png");
		tab= new Sprite(tabuleiro);
		tab.setSize(1280, 720);
		buttonText= new Texture("button.png");
		button= new Sprite(buttonText);
		buttonClickedText= new Texture("buttonClicked.png");
		buttonClicked= new Sprite(buttonClickedText);
		blizzardText=new Texture("blizzard.png");
		blizzard= new Sprite(blizzardText);
		blizzard.setSize(916, 99);
		meleeText= new Texture("meleeBtn.png");
		meleeSprite= new Sprite(meleeText);
		rangedText=new Texture("rangedBtn.png");
		rangedSprite=new Sprite(rangedText);
		siegeText=new Texture("siegeBtn.png");
		siegeSprite=new Sprite(siegeText);
		empowerText = new Texture("empower.png");
		empower= new Sprite(empowerText);
		exitText = new Texture("exit.png");
		exit= new Sprite(exitText);
		blizzardAtlas=new TextureAtlas(Gdx.files.internal("blizzardAtlas.atlas"));
		blizzardAnimation= new Animation(1/16f,blizzardAtlas.getRegions());
		empowerAtlas=new TextureAtlas(Gdx.files.internal("empowerAtlas.atlas"));
		empowerAnimation= new Animation(1/30f,empowerAtlas.getRegions());
		sunnyAtlas=new TextureAtlas(Gdx.files.internal("sunnyAtlas.atlas"));
		sunnyAnimation= new Animation(1/40f,sunnyAtlas.getRegions());
		empower.setSize(90, 99);
		stage=new Stage();
		stage.setViewport(viewport);
		jogo= new Tabuleiro();
		saver = new Saver(game);
		try {
            Decks = Saver.readDecks();


        } catch (IOException e) {
            e.printStackTrace();
            Decks = new ArrayList<ArrayList<ACard>>();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Decks = new ArrayList<ArrayList<ACard>>();

        }
		decktmp=Decks.get(game.deckIndex);
		
		
		jogo.initialize();
		pontosRangedtxt= new BitmapFont();
		pontosMeleetxt= new BitmapFont();
		pontosSiegetxt= new BitmapFont();
		totalPontostxt= new BitmapFont();
		btnExit = new Button(new SpriteDrawable(exit),
	            new SpriteDrawable(exit));
	    btnExit.addListener(new ClickListener() {
	        public void clicked(InputEvent event, float x, float y) {
	           ((Button) event.getTarget()).setChecked(true);
	           game.setScreen(new MainMenu(game));
	        }
	    });
	    btnExit.setSize(100, 50);
	    btnExit.setPosition(130, 20);
	    stage.addActor(btnExit);
		
		btn = new Button(new SpriteDrawable(button),
	            new SpriteDrawable(buttonClicked));
		btn.getStyle().checked=btn.getStyle().down;
	    btn.addListener(new ClickListener() {
	        public void clicked(InputEvent event, float x, float y) {
	           ((Button) event.getTarget()).setChecked(true);
	           passTurn=1;
	        }
	    });
	    btn.setSize(100, 50);
	    btn.setPosition(20, 20);
	    stage.addActor(btn);
	    //btn raise moral
	    
	    btnMelee = new Button(new SpriteDrawable(meleeSprite),
	            new SpriteDrawable(meleeSprite));
	    btnMelee.addListener(new ClickListener() {
	        public void clicked(InputEvent event, float x, float y) {
	          states.add(8);
	          ((Button) event.getTarget()).setVisible(false);
	          btnSiege.setVisible(false);
	          btnRanged.setVisible(false);
	          for(int j=0;j<melee.size();j++)
				{
					melee.get(j).atack=2*melee.get(j).atack;
					pontosMelee=2*pontosMelee;
				}
	        }
	    });
	    btnMelee.setPosition(540, 400);
	    btnMelee.setVisible(false);
	    //stage.addActor(btnMelee);
	    
	    btnRanged = new Button(new SpriteDrawable(rangedSprite),
	            new SpriteDrawable(rangedSprite));
	    btnRanged.addListener(new ClickListener() {
	        public void clicked(InputEvent event, float x, float y) {
	          states.add(9);
	          ((Button) event.getTarget()).setVisible(false);
	          btnSiege.setVisible(false);
	          btnMelee.setVisible(false);
	          for(int j=0;j<ranged.size();j++)
				{
					ranged.get(j).atack=2*ranged.get(j).atack;
					pontosRanged=2*pontosRanged;
				}
	        }
	    });
	    btnRanged.setPosition(540, 300);
	    btnRanged.setVisible(false);
	   // stage.addActor(btnRanged);
	    
	    btnSiege = new Button(new SpriteDrawable(siegeSprite),
	            new SpriteDrawable(siegeSprite));
	    btnSiege.addListener(new ClickListener() {
	        public void clicked(InputEvent event, float x, float y) {
	           states.add(10);
	           ((Button) event.getTarget()).setVisible(false);
	           btnRanged.setVisible(false);
		       btnMelee.setVisible(false);
		       for(int j=0;j<siege.size();j++)
				{
					siege.get(j).atack=2*siege.get(j).atack;
					pontosSiege=2*pontosSiege;
				}
	        }
	    });
	    btnSiege.setPosition(540, 200);
	    btnSiege.setVisible(false);
	    //stage.addActor(btnSiege);
	    System.out.println("HEYYYYsss "+decktmp.size());
	    for (int i=0; i<decktmp.size();i++)
		{
			MyCard carta= new MyCard(decktmp.get(i).getId(),decktmp.get(i).findRange(),decktmp.get(i).findPower(),decktmp.get(i).findEffect());
			deck.add(carta);
		}
		for(int i=0; i<10; i++)
		{
			int index = 0 + (int)(Math.random() * (deck.size()-1));
			cartas.add(deck.get(index));
			deck.remove(index);
			cartas.get(i).setSize(102, 120);
			cartas.get(i).initialx=(int) (260+i*cartas.get(i).getWidth());
			cartas.get(i).initialy=0;
			cartas.get(i).setPosition(260+(i*cartas.get(i).getWidth()), 0);
			cartas.get(i).setTouchable(Touchable.enabled);
			stage.addActor(cartas.get(i));
		}
		
		for(int i=0; i<jogo.baralho2.size(); i++)
		{
			cartasEnemie.add(new MyCard(jogo.baralho2.get(i).getId(),jogo.baralho2.get(i).getRange(),jogo.baralho2.get(i).getAtack(),jogo.baralho2.get(i).getEfeito()));
			
			System.out.println(deckEnemie.size());
		}
		Gdx.input.setInputProcessor(stage);
	}
	/**
	 * Actor que simula a carta 
	 * @author Paulo Babo
	 *
	 */
	public class MyCard extends Actor {
	
	        TextureRegion _texture;
	        float actorX = 0, actorY = 0;
	        public boolean started = false;
	        int id,initialx,initialy;
        	String range,efeito;
        	int atack,atackInicial;
            String description;
            BitmapFont atacktxt= new BitmapFont();
            /**
             * Construtor de MyCard, cria uma carta
             * @param id id da carta
             * @param range range da carta
             * @param atack valor do ataque da carta
             * @param efeito efeito especial da carta
             */
            public MyCard(int id, String range,int atack,String efeito){
	        	
	        	this.id=id;
	        	this.range=range;
	        	this.atack=atack;
	        	this.atackInicial=atack;
	        	this.efeito=efeito;
	        	_texture = new TextureRegion(new Texture(id+".png"));
	            setBounds(getX(),getY(),_texture.getRegionWidth(),_texture.getRegionHeight());
	            addListener(new InputListener(){
	                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	                	if(states.size()>0)
	                	{
	                	if(states.get(states.size()-1)==21)
	                	{
	                		if(meleeEnemie.size()>0||rangedEnemie.size()>0||siegeEnemie.size()>0)
	                		{
	                		MyCard decoy= new MyCard(5,((MyCard)event.getTarget()).range,0,"Magic");
	                		 
	                		if(decoy.range=="Melee")
	                		{
	                			decoy.setPosition(((MyCard)event.getTarget()).getX(), ((MyCard)event.getTarget()).getY());
	                			decoy.setSize(90,99);
	                			meleeEnemie.add(decoy);
	                			meleeEnemie.remove(((MyCard)event.getTarget()));
	                		}
	                		if(decoy.range=="Ranged")
	                		{
	                			decoy.setPosition(((MyCard)event.getTarget()).getX(), ((MyCard)event.getTarget()).getY());
	                			decoy.setSize(90,99);
	                			rangedEnemie.add(decoy);
	                			rangedEnemie.remove(((MyCard)event.getTarget()));
	                			
	                		}
	                		if(decoy.range=="Siege")
	                		{
	                			decoy.setPosition(((MyCard)event.getTarget()).getX(), ((MyCard)event.getTarget()).getY());
	                			decoy.setSize(90,99);
	                			siegeEnemie.add(decoy);
	                			siegeEnemie.remove(((MyCard)event.getTarget()));
	                		}
	                		((MyCard)event.getTarget()).setSize(102, 120);
	                		((MyCard)event.getTarget()).setPosition(260+cartas.size()*((MyCard)event.getTarget()).getWidth(), 0);
	                		((MyCard)event.getTarget()).initialx=(int) (260+cartas.size()*((MyCard)event.getTarget()).getWidth());
	                		((MyCard)event.getTarget()).initialy=0;
	                		cartas.add(((MyCard)event.getTarget()));
	          
	                		for (int i=0; i< meleeEnemie.size();i++)
	                		{
	                			meleeEnemie.get(i).setTouchable(null);
	                		}
	                		for (int i=0; i< rangedEnemie.size();i++)
	                		{
	                			rangedEnemie.get(i).setTouchable(null);
	                		}
	                		for (int i=0; i< siegeEnemie.size();i++)
	                		{
	                			siegeEnemie.get(i).setTouchable(null);
	                		}
	                		for (int i=0; i<cartas.size();i++)
	                		{
	                			cartas.get(i).setTouchable(Touchable.enabled);
	                		}
	                		states.remove(states.size()-1);
	                		decoy.setVisible(true);
	                		stage.addActor(decoy);
	                		decoychecker=1;
	                		return true;
	                		}
	                		
	            
	                	}
	                	}
	                	
	                	((MyCard)event.getTarget()).scaleBy((float) 0.25);
	                    return true;
	                }
	                public void touchDragged(InputEvent event, float x, float y, int pointer)
	                {
	                	 ((MyCard)event.getTarget()).setPosition((getX()+x)-getWidth()/2, getY()+y-getHeight()/2);
	                }
	                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	                	if(decoychecker==0)
	                	((MyCard)event.getTarget()).scaleBy((float) -0.25);
	                	decoychecker=0;
	                    if (((MyCard)event.getTarget()).getX()>360 && ((MyCard)event.getTarget()).getY()>120)
	                    {
	                    	if (((MyCard)event.getTarget()).id>=1&&((MyCard)event.getTarget()).id<=7)
	                    	{
	                    		FazerJogadaMagica(((MyCard)event.getTarget()));
	                    		FazerJogadaAi();
	                    		for(int i=0; i<states.size();i++)
	                    		{
	                    			EscolherState(states.get(i));
	                    		}
	                    	}
	                    	else if(((MyCard)event.getTarget()).efeito!="Unidade")
	                    	{
	                    		FazerJogadaEfeito(((MyCard)event.getTarget()));
	                    		FazerJogadaAi();
	                    		for(int i=0; i<states.size();i++)
	                    		{
	                    			EscolherState(states.get(i));
	                    		}
	                    	}
	                    	else
	                    	{
		                    	if( ((MyCard)event.getTarget()).range=="Melee")
		                    	{
		                    		
		                    		FazerJogadaMelee(((MyCard)event.getTarget()));
		                    		FazerJogadaAi();
		                    	}
		                    	if( ((MyCard)event.getTarget()).range=="Ranged")
		                    	{
		                    		FazerJogadaRanged(((MyCard)event.getTarget()));
		                    		FazerJogadaAi();
		                    	}
		                    	if( ((MyCard)event.getTarget()).range=="Siege")
		                    	{
		                    		FazerJogadaSiege(((MyCard)event.getTarget()));
		                    		FazerJogadaAi();
		                    	}
		                    	for(int i=0; i<states.size();i++)
		                		{
		                			EscolherState(states.get(i));
		                		}
	                    	}
	                    	for (int i=0; i< cartas.size();i++)
	                		{
	                			cartas.get(i).setPosition(260+(i*cartas.get(i).getWidth()), 0);
	                			cartas.get(i).initialx=(int) (260+(i*cartas.get(i).getWidth()));
	                			cartas.get(i).initialy=0;
	                		}
	                    }
	                    else
	                    ((MyCard)event.getTarget()).setPosition(initialx, initialy);
	                    
	                }
	            });
	        }
	        
	       /**
	        * Override do metodo draw , desenha a textura e o ataque da carta
	        */
	        @Override
	        public void draw(Batch batch, float alpha){
	            batch.draw(_texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	            atacktxt.draw(batch,Integer.toString(atack),(float) (getX()+((2.2*getWidth())/3)), (float) (getY()+getHeight()/3.3));
	         
	        }
	        
	       
	      
	    }
	    
	 
	/**
	 * Metodo de dispose
	 */
	@Override
	public void dispose() {
		
	}

	/**
	 * Metodo de show (nao utilizado)
	 */
	@Override
	public void show() {

	}
	
	/**
	 * Metodo de render
	 */
	@Override
	public void render(float delta) {
		if(passTurnEnemie==2||wait==true)
		{
			try {
			    Thread.sleep(1000);                
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			if(passTurnEnemie==2)
			{
				passTurnEnemie=3;
			}
			wait=false;
		}
		if(vida==0||vida2==0)
		{
			if(vida==0)
			{
				game.setScreen(new winScreen(game));
			}
			else if(vida2==0)
			{
				game.setScreen(new loseScreen(game));
			}
		}
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();

		timepassed+=Gdx.graphics.getDeltaTime();
		tab.draw(game.batch);
		totalPontos= pontosMelee+pontosRanged+pontosSiege;
		totalPontos2= pontosMelee2+pontosRanged2+pontosSiege2;
		pontosMeleetxt.draw(game.batch, Integer.toString(pontosMelee), 210, 370);
		pontosRangedtxt.draw(game.batch, Integer.toString(pontosRanged), 210, 270);
		pontosSiegetxt.draw(game.batch, Integer.toString(pontosSiege), 210, 170);
		pontosMeleetxt.draw(game.batch, Integer.toString(pontosMelee2), 210, 470);
		pontosRangedtxt.draw(game.batch, Integer.toString(pontosRanged2), 210, 570);
		pontosSiegetxt.draw(game.batch, Integer.toString(pontosSiege2), 210, 670);
		totalPontostxt.draw(game.batch, Integer.toString(totalPontos), 95, 180);
		totalPontostxt.draw(game.batch, Integer.toString(totalPontos2), 95, 480);
		
		
			
		
		game.batch.end();
		
		stage.act();
		
		stage.draw();
		if(states.size()>0)
		{
		if(states.get(states.size()-1)==20)
		{
			for(int i=0; i <meleeEnemie.size();i++)
			{
				meleeEnemie.get(i).setTouchable(Touchable.enabled);
			}
			for(int i=0; i <rangedEnemie.size();i++)
			{
				rangedEnemie.get(i).setTouchable(Touchable.enabled);
			}
			for(int i=0; i <siegeEnemie.size();i++)
			{
				siegeEnemie.get(i).setTouchable(Touchable.enabled);
			}
			for(int i=0; i<cartas.size();i++)
			{
				cartas.get(i).setTouchable(null);
			}
			states.remove(states.size()-1);
			states.add(21);
		}
		}
		DesenhaStates();
		if(passTurnEnemie==1)
		{
			game.batch.begin();
			roundText=new Texture("Turn.png");
			round= new Sprite(roundText);
			round.draw(game.batch);
			game.batch.end();
			passTurnEnemie=2;
		}
		if(passTurn==1&&passTurnEnemie==3)
		{
			
			if(totalPontos>totalPontos2)
			{
				game.batch.begin();
				winText=new Texture("winRound.png");
				win= new Sprite(winText);
				win.draw(game.batch);
				game.batch.end();
				vida--;
			}
			else if(totalPontos<=totalPontos2)
			{
				game.batch.begin();
				loseText=new Texture("loseRound.png");
				lose= new Sprite(loseText);
				lose.draw(game.batch);
				game.batch.end();
				vida2--;
			}
			TurnoAcabado();
			wait=true;
		}
		else if(passTurn==1)
		{
			if(passTurnEnemie==2)
			{
				
			}
			else while(passTurnEnemie!=1)
			{
				FazerJogadaAi();
				for(int i=0; i<states.size();i++)
	    		{
	    			EscolherState(states.get(i));
	    		}
			
			}
			
			wait=true;
			
		}
		
	}
	/**
	 * Metodo de resize
	 */
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height,true);
	}
	/**
	 * Metodo de pause
	 */
	@Override
	public void pause() {
	}
	/**
	 * Metodo de resume
	 */
	@Override
	public void resume() {
	}

	@Override
	public void hide() {

	}
	/**
	 * Realiza uma jogada "melee" de uma carta sem efeito
	 * @param carta carta sobre a qual  feita a jogada
	 */
	void FazerJogadaMelee(MyCard carta)
	{
		carta.setSize(90, 99);
		carta.setPosition((float) (364+melee.size()*carta.getWidth()), 318);
		carta.setTouchable(null);
		melee.add(carta);
		pontosMelee+=carta.atack;
		cartas.remove(carta);
		jogada++;
		
	}
	/**
	 * Realiza uma jogada "ranged" de uma carta sem efeito
	 * @param carta carta sobre a qual  feita a jogada
	 */
	void FazerJogadaRanged(MyCard carta)
	{
		carta.setSize(90, 99);
		carta.setPosition((float) (364+ranged.size()*(carta.getWidth())), 219);
		carta.setTouchable(null);
		ranged.add(carta);
		pontosRanged+=carta.atack;
		cartas.remove(carta);
		jogada++;
	}
	/**
	 * Realiza uma jogada "siege" de uma carta sem efeito
	 * @param carta carta sobre a qual  feita a jogada
	 */
	void FazerJogadaSiege(MyCard carta)
	{
		carta.setSize(90,99);
		carta.setPosition((float) (364+siege.size()*(carta.getWidth())), 120);
		carta.setTouchable(null);
		siege.add(carta);
		pontosSiege+=carta.atack;
		cartas.remove(carta);
		jogada++;
	}
	/**
	 * Termina um turno
	 */
	void TurnoAcabado()
	{
		for(int i=0;i<melee.size();i++)
		{
			melee.get(i).setVisible(false);
			melee.get(i).atack=melee.get(i).atackInicial;
			graveyard.add(melee.get(i));
			melee.remove(i);
			
			i--;
		}	
		for(int i=0;i<ranged.size();i++)
		{
			ranged.get(i).setVisible(false);
			ranged.get(i).atack=ranged.get(i).atackInicial;
			graveyard.add(ranged.get(i));
			ranged.remove(i);
			i--;
		}	
		for(int i=0;i<siege.size();i++)
		{
			siege.get(i).setVisible(false);
			siege.get(i).atack=siege.get(i).atackInicial;
			graveyard.add(siege.get(i));
			siege.remove(i);
			i--;
		}	
		//Enemy
		for(int i=0;i<meleeEnemie.size();i++)
		{
			meleeEnemie.get(i).setVisible(false);
			meleeEnemie.get(i).atack=meleeEnemie.get(i).atackInicial;
			graveyardEnemie.add(meleeEnemie.get(i));
			meleeEnemie.remove(i);
			i--;
		}	
		for(int i=0;i<rangedEnemie.size();i++)
		{
			rangedEnemie.get(i).setVisible(false);
			rangedEnemie.get(i).atack=rangedEnemie.get(i).atackInicial;
			graveyardEnemie.add(rangedEnemie.get(i));
			rangedEnemie.remove(i);
			i--;
		}	
		for(int i=0;i<siegeEnemie.size();i++)
		{
			siegeEnemie.get(i).setVisible(false);
			siegeEnemie.get(i).atack=siegeEnemie.get(i).atackInicial;
			graveyard.add(siegeEnemie.get(i));
			siegeEnemie.remove(i);
			i--;
		}	
        melee.clear();
        siege.clear();
        ranged.clear();
        meleeEnemie.clear();
        siegeEnemie.clear();
        rangedEnemie.clear();
        states.clear();
        pontosMelee=0;
        pontosRanged=0;
        pontosSiege=0;
        pontosMelee2=0;
        pontosRanged2=0;
        pontosSiege2=0;
        jogada=0;
        passTurn=0;
        passTurnEnemie=0;
        btn.setChecked(false);
	}
	/**
	 * Realiza uma jogada do inimigo
	 */
	void FazerJogadaAi()
	{
		if(passTurnEnemie==0)
		{	int index;
			if(cartasEnemie.size() >= 6){
			 index = 0 + (int)(Math.random() * (cartasEnemie.size()+1));}
			else if(cartasEnemie.size() >= 4 && cartasEnemie.size()<6){
				index = 0 + (int)(Math.random() * (cartasEnemie.size()+3));
			}
			else if(vida2 == 1){
				 index = 0 + (int)(Math.random() * (cartasEnemie.size()+1));
			}
			else{
				index = 0 + (int)(Math.random() * (cartasEnemie.size()+50));
			}
			if(index>=cartasEnemie.size())
			{

				System.out.println("OLAAAAAAA2");
				passTurnEnemie=1;
			}
			else if(cartasEnemie.get(index).id>=1 && cartasEnemie.get(index).id<=7)
			{
				switch(cartasEnemie.get(index).id){
					case 1:
						if(pontosMelee2 > pontosMelee && cartasEnemie.size() > 2){
							FazerJogadaAi();
							return;

						}
						else{
							FazerJogadaMagicaAi(cartasEnemie.get(index));
						}
						break;
					case 2:
						if(pontosRanged2 > pontosRanged && cartasEnemie.size() > 2){
							FazerJogadaAi();
							return;

						}
						else{
							FazerJogadaMagicaAi(cartasEnemie.get(index));
						}
						break;
					case 3:
						if(pontosSiege2 > pontosSiege && cartasEnemie.size() > 2){
							FazerJogadaAi();
							return;

						}
						else{
							FazerJogadaMagicaAi(cartasEnemie.get(index));
						}
						break;
				}
				System.out.println("magicaaaaaaaaa");
				FazerJogadaMagicaAi(cartasEnemie.get(index));
			}
			else if(cartasEnemie.get(index).efeito!="Unidade")
			{
				FazerJogadaEfeitoAi(cartasEnemie.get(index));
			}
			else if(cartasEnemie.get(index).range=="Melee")
			{
				System.out.println("OLAAAAAAA1");
				pontosMelee2+=cartasEnemie.get(index).atack;
				cartasEnemie.get(index).setSize(90, 99);
				cartasEnemie.get(index).setPosition(364+meleeEnemie.size()*cartasEnemie.get(index).getWidth(), 423);
				cartasEnemie.get(index).setTouchable(null);
				meleeEnemie.add(cartasEnemie.get(index));
				cartasEnemie.remove(index);
				stage.addActor(meleeEnemie.get(meleeEnemie.size()-1));
			}
			else if(cartasEnemie.get(index).range=="Ranged")
			{
				System.out.println("OLAAAAAAA3");
				pontosRanged2+=cartasEnemie.get(index).atack;
				cartasEnemie.get(index).setSize(90, 99);
				cartasEnemie.get(index).setPosition(364+rangedEnemie.size()*cartasEnemie.get(index).getWidth(), 522);
				cartasEnemie.get(index).setTouchable(null);
				rangedEnemie.add(cartasEnemie.get(index));
				cartasEnemie.remove(index);
				stage.addActor(rangedEnemie.get(rangedEnemie.size()-1));
			}
			else if(cartasEnemie.get(index).range=="Siege")
			{
				System.out.println("OLAAAAAAA4");
				pontosSiege2+=cartasEnemie.get(index).atack;
				cartasEnemie.get(index).setSize(90, 99);
				cartasEnemie.get(index).setPosition(364+siegeEnemie.size()*cartasEnemie.get(index).getWidth(), 621);
				cartasEnemie.get(index).setTouchable(null);
				siegeEnemie.add(cartasEnemie.get(index));
				cartasEnemie.remove(index);
				stage.addActor(siegeEnemie.get(siegeEnemie.size()-1));
			}
		}
		
	}
	/**
	 * Realiza uma jogada de uma carta magica
	 * @param carta carta jogada
	 */
	void FazerJogadaMagica(MyCard carta)
	{
		if(carta.id==1)
		{	
			cartas.remove(carta);
			graveyard.add(carta);
			states.add(1);
			carta.setVisible(false);
		}
		if(carta.id==2)
		{
			
			cartas.remove(carta);
			graveyard.add(carta);
			states.add(2);
			carta.setVisible(false);
		}
		if(carta.id==3)
		{
			
			cartas.remove(carta);
			graveyard.add(carta);
			states.add(3);
			carta.setVisible(false);
		}
		if(carta.id==4)
		{
			pontosMelee=0;
			pontosRanged=0;
			pontosSiege=0;
			pontosMelee2=0;
			pontosRanged2=0;
			pontosSiege2=0;
			for (int i=0; i< melee.size();i++)
			{
				melee.get(i).atack=melee.get(i).atackInicial;
				pontosMelee+=melee.get(i).atack;
				
			}
			for (int i=0; i< ranged.size();i++)
			{
				ranged.get(i).atack=ranged.get(i).atackInicial;
				pontosRanged+=ranged.get(i).atack;
				
			}
			for (int i=0; i< siege.size();i++)
			{
				siege.get(i).atack=siege.get(i).atackInicial;
				pontosSiege+=siege.get(i).atack;
				
			}
			//enimigo
			for (int i=0; i< meleeEnemie.size();i++)
			{
				meleeEnemie.get(i).atack=meleeEnemie.get(i).atackInicial;
				pontosMelee2+=meleeEnemie.get(i).atack;
				
			}
			for (int i=0; i< rangedEnemie.size();i++)
			{
				rangedEnemie.get(i).atack=rangedEnemie.get(i).atackInicial;
				pontosRanged2+=rangedEnemie.get(i).atack;
				
			}
			for (int i=0; i< siegeEnemie.size();i++)
			{
				siegeEnemie.get(i).atack=siegeEnemie.get(i).atackInicial;
				pontosSiege2+=siegeEnemie.get(i).atack;
				
			}
			cartas.remove(carta);
			graveyard.add(carta);
			states.add(15);
			carta.setVisible(false);
		}
		if(carta.id==5)
		{
			if(meleeEnemie.size()>0||rangedEnemie.size()>0||siegeEnemie.size()>0)
			{
			states.add(20);
			cartas.remove(carta);
			carta.setVisible(false);
			}
		}
		if(carta.id==6)
		{
			for (int i=0; i< melee.size();i++)
			{
				if(melee.get(i).atack>=7)
				{
					graveyard.add(melee.get(i));
					pontosMelee-=melee.get(i).atack;
					melee.get(i).setVisible(false);
					melee.remove(i);
					
					i--;
					
				}
				
			}
			for (int i=0; i< ranged.size();i++)
			{
				if(ranged.get(i).atack>=7)
				{
					graveyard.add(ranged.get(i));
					pontosRanged-=ranged.get(i).atack;
					ranged.get(i).setVisible(false);
					ranged.remove(i);
					i--;
				}
			}
			for (int i=0; i< siege.size();i++)
			{
				if(siege.get(i).atack>=7)
				{
					graveyard.add(siege.get(i));
					pontosSiege-=siege.get(i).atack;
					siege.get(i).setVisible(false);
					siege.remove(i);
					i--;
				}
			}
			//enimigo
			for (int i=0; i< meleeEnemie.size();i++)
			{
				if(meleeEnemie.get(i).atack>=7)
				{
					graveyardEnemie.add(meleeEnemie.get(i));
					pontosMelee2-=meleeEnemie.get(i).atack;
					meleeEnemie.get(i).setVisible(false);
					meleeEnemie.remove(i);
					i--;
					
				}
				
			}
			for (int i=0; i< rangedEnemie.size();i++)
			{
				if(rangedEnemie.get(i).atack>=7)
				{
					graveyardEnemie.add(rangedEnemie.get(i));
					pontosRanged2-=rangedEnemie.get(i).atack;
					rangedEnemie.get(i).setVisible(false);
					rangedEnemie.remove(i);
					i--;
				}
			}
			for (int i=0; i< siegeEnemie.size();i++)
			{
				if(siegeEnemie.get(i).atack>=7)
				{
					graveyardEnemie.add(siegeEnemie.get(i));
					pontosSiege2-=siegeEnemie.get(i).atack;
					siegeEnemie.get(i).setVisible(false);
					siegeEnemie.remove(i);
					i--;
				}
			}
			cartas.remove(carta);
			graveyard.add(carta);
			carta.setVisible(false);
		}
		if(carta.id==7)
		{
			states.add(7);
			cartas.remove(carta);
			graveyard.add(carta);
			carta.setVisible(false);
		}

	}   
	/**
	 * Escolhe o estado de jogo correspondende
	 * @param state numero do estado
	 */
	void EscolherState(int state)
	{
		if(state==1)
		{
			int count=0;
			int count2=0;
			for (int i=0; i< melee.size();i++)
			{
				melee.get(i).atack=1;
				count++;
			}
			for (int i=0; i< meleeEnemie.size();i++)
			{
				meleeEnemie.get(i).atack=1;
				count2++;
			}
			pontosMelee=count;
			pontosMelee2=count2;
			game.batch.begin();
			roundText=new Texture("Turn.png");
			round= new Sprite(roundText);
			round.draw(game.batch);
			game.batch.end();
		}
		else if(state==2)
		{
			int count=0;
			int count2=0;
			for (int i=0; i< ranged.size();i++)
			{
				ranged.get(i).atack=1;
				count++;
			}
			for (int i=0; i< rangedEnemie.size();i++)
			{
				rangedEnemie.get(i).atack=1;
				count2++;
			}
			
			pontosRanged=count;
			pontosRanged2=count2;
		}
		else if(state==3)
		{
			int count=0;
			int count2=0;
			for (int i=0; i< siege.size();i++)
			{
				siege.get(i).atack=1;
				count++;
			}
			for (int i=0; i< siegeEnemie.size();i++)
			{
				siegeEnemie.get(i).atack=1;
				count2++;
			}
			pontosSiege2=count2;
			pontosSiege=count;
		}
		else if(state==8)
		{	
			if(melee.size()>0)
			{
			pontosMelee=0;
			for(int i =0; i<melee.size();i++)
			{
				melee.get(i).atack= 2*melee.get(i).atackInicial;
				pontosMelee+=melee.get(i).atack;
			}
			}
			
		}
		else if(state==9)
		{
			if(ranged.size()>0)
			{
			pontosRanged=0;
			for(int i =0; i<ranged.size();i++)
			{
				ranged.get(i).atack= 2*ranged.get(i).atackInicial;
				pontosRanged+=ranged.get(i).atack;
			}
			}
		}
		else if(state==10)
		{
			if(siege.size()>0)
			{
			pontosSiege=0;
			for(int i =0; i<siege.size();i++)
			{
				siege.get(i).atack= 2*siege.get(i).atackInicial;
				pontosSiege+=siege.get(i).atack;
			}
			}
		}
		else if(state==11)
		{
			pontosMelee2=0;
			for(int i =0; i<meleeEnemie.size();i++)
			{
				meleeEnemie.get(i).atack= 2*meleeEnemie.get(i).atackInicial;
				pontosMelee2+=meleeEnemie.get(i).atack;
			}
		}
		else if(state==12)
		{
			pontosRanged2=0;
			for(int i =0; i<rangedEnemie.size();i++)
			{
				rangedEnemie.get(i).atack= 2*rangedEnemie.get(i).atackInicial;
				pontosRanged2+=rangedEnemie.get(i).atack;
			}
		}
		else if(state==13)
		{
			pontosSiege2=0;
			for(int i =0; i<siegeEnemie.size();i++)
			{
				siegeEnemie.get(i).atack= 2*siegeEnemie.get(i).atackInicial;
				pontosSiege2+=siegeEnemie.get(i).atack;
			}
		}
	}
	/**
	 * Realiza uma jogada inimiga de uma carta magica
	 * @param carta carta jogada
	 */
	void FazerJogadaMagicaAi(MyCard carta) 
	{
		if(carta.id==1)
		{	
			cartasEnemie.remove(carta);
			graveyardEnemie.add(carta);
			states.add(1);
			carta.setVisible(false);
		}
		if(carta.id==2)
		{
			
			cartasEnemie.remove(carta);
			graveyardEnemie.add(carta);
			states.add(2);
			carta.setVisible(false);
		}
		if(carta.id==3)
		{
			
			cartasEnemie.remove(carta);
			graveyardEnemie.add(carta);
			states.add(3);
			carta.setVisible(false);
		}
		if(carta.id==4)
		{
			pontosMelee=0;
			pontosRanged=0;
			pontosSiege=0;
			pontosMelee2=0;
			pontosRanged2=0;
			pontosSiege2=0;
			for (int i=0; i< melee.size();i++)
			{
				melee.get(i).atack=melee.get(i).atackInicial;
				pontosMelee+=melee.get(i).atack;
				
			}
			for (int i=0; i< ranged.size();i++)
			{
				ranged.get(i).atack=ranged.get(i).atackInicial;
				pontosRanged+=ranged.get(i).atack;
				
			}
			for (int i=0; i< siege.size();i++)
			{
				siege.get(i).atack=siege.get(i).atackInicial;
				pontosSiege+=siege.get(i).atack;
				
			}
			//enimigo
			for (int i=0; i< meleeEnemie.size();i++)
			{
				meleeEnemie.get(i).atack=meleeEnemie.get(i).atackInicial;
				pontosMelee2+=meleeEnemie.get(i).atack;
				
			}
			for (int i=0; i< rangedEnemie.size();i++)
			{
				rangedEnemie.get(i).atack=rangedEnemie.get(i).atackInicial;
				pontosRanged2+=rangedEnemie.get(i).atack;
				
			}
			for (int i=0; i< siegeEnemie.size();i++)
			{
				siegeEnemie.get(i).atack=siegeEnemie.get(i).atackInicial;
				pontosSiege2+=siegeEnemie.get(i).atack;
				
			}
			cartasEnemie.remove(carta);
			graveyardEnemie.add(carta);
			states.clear();
			carta.setVisible(false);
		}
		if(carta.id==5)
		{
			//TODO decoy;
		}
		if(carta.id==6)
		{
			for (int i=0; i< melee.size();i++)
			{
				if(melee.get(i).atack>=7)
				{
					graveyard.add(melee.get(i));
					pontosMelee-=melee.get(i).atack;
					melee.get(i).setVisible(false);
					melee.remove(i);
					
					i--;
					
				}
				
			}
			for (int i=0; i< ranged.size();i++)
			{
				if(ranged.get(i).atack>=7)
				{
					graveyard.add(ranged.get(i));
					pontosRanged-=ranged.get(i).atack;
					ranged.get(i).setVisible(false);
					ranged.remove(i);
					i--;
				}
			}
			for (int i=0; i< siege.size();i++)
			{
				if(siege.get(i).atack>=7)
				{
					graveyard.add(siege.get(i));
					pontosSiege-=siege.get(i).atack;
					siege.get(i).setVisible(false);
					siege.remove(i);
					i--;
				}
			}
			//enimigo
			for (int i=0; i< meleeEnemie.size();i++)
			{
				if(meleeEnemie.get(i).atack>=7)
				{
					graveyardEnemie.add(meleeEnemie.get(i));
					pontosMelee2-=meleeEnemie.get(i).atack;
					meleeEnemie.get(i).setVisible(false);
					meleeEnemie.remove(i);
					i--;
					
				}
				
			}
			for (int i=0; i< rangedEnemie.size();i++)
			{
				if(rangedEnemie.get(i).atack>=7)
				{
					graveyardEnemie.add(rangedEnemie.get(i));
					pontosRanged2-=rangedEnemie.get(i).atack;
					rangedEnemie.get(i).setVisible(false);
					rangedEnemie.remove(i);
					i--;
				}
			}
			for (int i=0; i< siegeEnemie.size();i++)
			{
				if(siegeEnemie.get(i).atack>=7)
				{
					graveyardEnemie.add(siegeEnemie.get(i));
					pontosSiege2-=siegeEnemie.get(i).atack;
					siegeEnemie.get(i).setVisible(false);
					siegeEnemie.remove(i);
					i--;
				}
			}
			cartasEnemie.remove(carta);
			graveyardEnemie.add(carta);
			carta.setVisible(false);
		}
		if(carta.id==7)
		{
			Random ran = new Random();
			int x = ran.nextInt(3);
			if(x==0)
			states.add(11);
			else if(x==1)
			states.add(12);	
			else if(x==2)
			states.add(13);
			cartasEnemie.remove(carta);
			graveyardEnemie.add(carta);
			carta.setVisible(false);
		}

	}   
	/**
	 * Escolhe o range para fazer uma jogada com efeito
	 * @param carta carta jogada
	 */
	void FazerJogadaEfeito(MyCard carta)
	{
		if (carta.range=="Melee")
		{
			FazerJogadaEfeitoMelee(carta);
		}
		if (carta.range=="Ranged")
		{
			FazerJogadaEfeitoRanged(carta);
		}
		if (carta.range=="Siege")
		{
			FazerJogadaEfeitoSiege(carta);
		}
	}
	/**
	 * Realiza uma jogada "Melee" de uma carta com efeito
	 * @param carta carta jogada
	 */
	void FazerJogadaEfeitoMelee(MyCard carta)
	{
		if (carta.efeito=="Spy")
		{
			System.out.println("Spyyy2");
			carta.setSize(90,99);
			carta.setPosition((float) (364+meleeEnemie.size()*(carta.getWidth())), 423);
			carta.setTouchable(null);
			meleeEnemie.add(carta);
			pontosMelee2+=carta.atack;
			cartas.remove(carta);
			jogada++;
			int j=0;
			if(cartas.size()<=8)
			{
				j=0;
			}
			if(cartas.size()==9)
			{
				j=1;
			}
			if(cartas.size()==10)
			{
				j=2;
			}
			for (int i=j; i<2;i++)
			{
				System.out.println("Spyyy");
				int index = 0 + (int)(Math.random() * (deck.size()-1)); 
				cartas.add(deck.get(index));
				deck.remove(index);
				cartas.get(cartas.size()-1).setSize(102,120);
				cartas.get(cartas.size()-1).initialx=(int) (260+cartas.size()*cartas.get(i).getWidth());
				cartas.get(cartas.size()-1).initialy=0;
				cartas.get(cartas.size()-1).setPosition(260+cartas.size()*cartas.get(i).getWidth(), 0);
				cartas.get(cartas.size()-1).setTouchable(Touchable.enabled);	
				cartas.get(cartas.size()-1).setVisible(true);
				stage.addActor(cartas.get(cartas.size()-1));
			}
			
		}
		if (carta.efeito=="Scorcher")
		{
			carta.setSize(90,99);
			carta.setPosition((float) (364+melee.size()*(carta.getWidth())), 318);
			carta.setTouchable(null);
			melee.add(carta);
			pontosMelee+=carta.atack;
			cartas.remove(carta);
			jogada++;
			scorcher();
			
		}
		if(carta.efeito=="Reviver")
		{
			carta.setSize(90,99);
			carta.setPosition((float) (364+melee.size()*(carta.getWidth())), 318);
			carta.setTouchable(null);
			melee.add(carta);
			pontosMelee+=carta.atack;
			cartas.remove(carta);
			jogada++;
			int index = 0 + (int)(Math.random() * (graveyard.size()-1));
			if(graveyard.size()>0)
			{
			cartas.add(graveyard.get(index));
			graveyard.remove(index);
			cartas.get(cartas.size()-1).setVisible(true);
			cartas.get(cartas.size()-1).setTouchable(Touchable.enabled);
			cartas.get(cartas.size()-1).setSize(102, 120);
			cartas.get(cartas.size()-1).initialx=(int) (260+cartas.size()*cartas.get(cartas.size()-1).getWidth());
			cartas.get(cartas.size()-1).initialy=0;
			cartas.get(cartas.size()-1).setPosition((float) (260+cartas.size()*(carta.getWidth())), 0);
			}
						
			
		}
		if(carta.efeito=="Booster")
		{
			System.out.println("BOOSTER");
			pontosMelee=0;
			carta.setSize(90,99);
			carta.setPosition((float) (364+melee.size()*(carta.getWidth())), 318);
			carta.setTouchable(null);
			melee.add(carta);
			pontosMelee+=carta.atack;
			cartas.remove(carta);
			jogada++;
			for(int i=0;i <melee.size()-1;i++)
			{
				melee.get(i).atack++;
				pontosMelee+=melee.get(i).atack;
			}
		}
	}
	/**
	 * Realiza uma jogada "Ranged" de uma carta com efeito
	 * @param carta carta jogada
	 */
	void FazerJogadaEfeitoRanged(MyCard carta)
	{
		if (carta.efeito=="Spy")
		{
			carta.setSize(90,99);
			carta.setPosition((float) (364+rangedEnemie.size()*(carta.getWidth())), 522);
			carta.setTouchable(null);
			rangedEnemie.add(carta);
			pontosRanged2+=carta.atack;
			cartas.remove(carta);
			jogada++;
			
			int j=0;
			if(cartas.size()<=8)
			{
				j=0;
			}
			if(cartas.size()==9)
			{
				j=1;
			}
			if(cartas.size()==10)
			{
				j=2;
			}
			for (int i=j; i<2;i++)
			{
				int index = 0 + (int)(Math.random() * (deck.size()-1)); 
				cartas.add(deck.get(index));
				deck.remove(index);
				cartas.get(cartas.size()-1).setSize(102,120);
				cartas.get(cartas.size()-1).initialx=(int) (260+cartas.size()*cartas.get(i).getWidth());
				cartas.get(cartas.size()-1).initialy=0;
				cartas.get(cartas.size()-1).setPosition(260+cartas.size()*cartas.get(i).getWidth(), 0);
				cartas.get(cartas.size()-1).setTouchable(Touchable.enabled);	
				cartas.get(cartas.size()-1).setVisible(true);
				stage.addActor(cartas.get(cartas.size()-1));
			}
			
		}
		if (carta.efeito=="Scorcher")
		{
			carta.setSize(90,99);
			carta.setPosition((float) (364+ranged.size()*(carta.getWidth())), 219);
			carta.setTouchable(null);
			ranged.add(carta);
			pontosRanged+=carta.atack;
			cartas.remove(carta);
			jogada++;
			scorcher();
			
		}
		if(carta.efeito=="Reviver")
		{
			carta.setSize(90,99);
			carta.setPosition((float) (364+ranged.size()*(carta.getWidth())), 219);
			carta.setTouchable(null);
			ranged.add(carta);
			pontosRanged+=carta.atack;
			cartas.remove(carta);
			jogada++;
			int index = 0 + (int)(Math.random() * (graveyard.size()-1));
			if(graveyard.size()>0)
			{
			cartas.add(graveyard.get(index));
			graveyard.remove(index);
			cartas.get(cartas.size()-1).setVisible(true);
			cartas.get(cartas.size()-1).setTouchable(Touchable.enabled);
			cartas.get(cartas.size()-1).setSize(102, 120);
			cartas.get(cartas.size()-1).initialx=(int) (260+cartas.size()*cartas.get(cartas.size()-1).getWidth());
			cartas.get(cartas.size()-1).initialy=0;
			cartas.get(cartas.size()-1).setPosition((float) (260+cartas.size()*(carta.getWidth())), 0);
			}

			
			
		}
		if(carta.efeito=="Booster")
		{
			pontosRanged=0;
			carta.setSize(90,99);
			carta.setPosition((float) (364+ranged.size()*(carta.getWidth())), 219);
			carta.setTouchable(null);
			ranged.add(carta);
			pontosRanged+=carta.atack;
			cartas.remove(carta);
			jogada++;
			for(int i=0;i <ranged.size()-1;i++)
			{
				ranged.get(i).atack++;
				pontosRanged+=ranged.get(i).atack;
			}
		}
	}
	/**
	 * Realiza uma jogada "Siege" de uma carta com efeito
	 * @param carta carta jogada
	 */
	void FazerJogadaEfeitoSiege(MyCard carta)
	{
		if (carta.efeito=="Spy")
		{
			carta.setSize(90,99);
			carta.setPosition((float) (364+siegeEnemie.size()*(carta.getWidth())), 621);
			carta.setTouchable(null);
			siegeEnemie.add(carta);
			pontosSiege2+=carta.atack;
			cartas.remove(carta);
			jogada++;
			int j=0;
			if(cartas.size()<=8)
			{
				j=0;
			}
			if(cartas.size()==9)
			{
				j=1;
			}
			if(cartas.size()==10)
			{
				j=2;
			}
			for (int i=j; i<2;i++)
			{
				System.out.println("Spyyy");
				int index = 0 + (int)(Math.random() * (deck.size()-1)); 
				cartas.add(deck.get(index));
				deck.remove(index);
				cartas.get(cartas.size()-1).setSize(102,120);
				cartas.get(cartas.size()-1).initialx=(int) (260+cartas.size()*cartas.get(i).getWidth());
				cartas.get(cartas.size()-1).initialy=0;
				cartas.get(cartas.size()-1).setPosition(260+cartas.size()*cartas.get(i).getWidth(), 0);
				cartas.get(cartas.size()-1).setTouchable(Touchable.enabled);	
				cartas.get(cartas.size()-1).setVisible(true);
				stage.addActor(cartas.get(cartas.size()-1));
			}
			
		}
		if (carta.efeito=="Scorcher")
		{
			carta.setSize(90,99);
			carta.setPosition((float) (364+siege.size()*(carta.getWidth())), 120);
			carta.setTouchable(null);
			siege.add(carta);
			pontosSiege+=carta.atack;
			cartas.remove(carta);
			jogada++;
			scorcher();
			
		}
		if(carta.efeito=="Reviver")
		{
			carta.setSize(90,99);
			carta.setPosition((float) (364+siege.size()*(carta.getWidth())), 120);
			carta.setTouchable(null);
			siege.add(carta);
			pontosSiege+=carta.atack;
			cartas.remove(carta);
			jogada++;
			int index = 0 + (int)(Math.random() * (graveyard.size()-1));
			if(graveyard.size()>0)
			{
			cartas.add(graveyard.get(index));
			graveyard.remove(index);
			cartas.get(cartas.size()-1).setVisible(true);
			cartas.get(cartas.size()-1).setTouchable(Touchable.enabled);
			cartas.get(cartas.size()-1).setSize(102, 120);
			cartas.get(cartas.size()-1).initialx=(int) (260+cartas.size()*cartas.get(cartas.size()-1).getWidth());
			cartas.get(cartas.size()-1).initialy=0;
			cartas.get(cartas.size()-1).setPosition((float) (260+cartas.size()*(carta.getWidth())), 0);
			}

			
			
		}
		if(carta.efeito=="Booster")
		{
			pontosSiege=0;
			carta.scaleBy((float) -0.5);
			carta.setPosition((float) (250+siege.size()*(carta._texture.getRegionWidth()*0.75)), 450);
			carta.setTouchable(null);
			siege.add(carta);
			pontosSiege+=carta.atack;
			cartas.remove(carta);
			jogada++;
			for(int i=0;i <siege.size()-1;i++)
			{
				siege.get(i).atack++;
				pontosSiege+=siege.get(i).atack;
			}
		}
	}
	/**
	 * Elimina as cartas inimigas de maior atack
	 */
	void scorcher()
	{
		int atackMax=0;
		//encontrar o maximo
		for (int i=0; i< meleeEnemie.size();i++)
		{
		if (meleeEnemie.get(i).atack>atackMax)
		{
			atackMax=meleeEnemie.get(i).atack;
		}
		}
		
		for (int i=0; i< rangedEnemie.size();i++)
		{
		if (rangedEnemie.get(i).atack>atackMax)
		{
			atackMax=rangedEnemie.get(i).atack;
		}
		}
		
		for (int i=0; i< siegeEnemie.size();i++)
		{
		if (siegeEnemie.get(i).atack>atackMax)
		{
			atackMax=siegeEnemie.get(i).atack;
		}
		}
		
		//encontrar a carta
		
		for (int i=0; i< meleeEnemie.size();i++)
		{
		if (meleeEnemie.get(i).atack>=atackMax)
		{	
			meleeEnemie.get(i).setVisible(false);
			pontosMelee2-=meleeEnemie.get(i).atack;
			meleeEnemie.get(i).atack=meleeEnemie.get(i).atackInicial;
			graveyardEnemie.add(meleeEnemie.get(i));
			meleeEnemie.remove(i);
			i--;
		}
		}
		
		for (int i=0; i< rangedEnemie.size();i++)
		{
		if (rangedEnemie.get(i).atack>=atackMax)
		{	
			rangedEnemie.get(i).setVisible(false);
			pontosRanged2-=rangedEnemie.get(i).atack;
			rangedEnemie.get(i).atack=rangedEnemie.get(i).atackInicial;
			graveyardEnemie.add(rangedEnemie.get(i));
			rangedEnemie.remove(i);
			i--;
		}
		}
		
		for (int i=0; i< siegeEnemie.size();i++)
		{
		if (siegeEnemie.get(i).atack>=atackMax)
		{	
			siegeEnemie.get(i).setVisible(false);
			pontosSiege2-=siegeEnemie.get(i).atack;
			siegeEnemie.get(i).atack=siegeEnemie.get(i).atackInicial;
			graveyardEnemie.add(siegeEnemie.get(i));
			siegeEnemie.remove(i);
			i--;
		}
		}
		
		
	}
	/**
	 * Elimina as cartas de maior atack
	 */
	void scorcherAi()
	{
		int atackMax=0;
		//encontrar o maximo
		for (int i=0; i< melee.size();i++)
		{
		if (melee.get(i).atack>atackMax)
		{
			atackMax=melee.get(i).atack;
		}
		}
		
		for (int i=0; i< ranged.size();i++)
		{
		if (ranged.get(i).atack>atackMax)
		{
			atackMax=ranged.get(i).atack;
		}
		}
		
		for (int i=0; i< siege.size();i++)
		{
		if (siege.get(i).atack>atackMax)
		{
			atackMax=siege.get(i).atack;
		}
		}
		
		//encontrar a carta
		
		for (int i=0; i< melee.size();i++)
		{
		if (melee.get(i).atack==atackMax)
		{	
			melee.get(i).setVisible(false);
			pontosMelee-=melee.get(i).atack;
			melee.get(i).atack=melee.get(i).atackInicial;
			graveyard.add(melee.get(i));
			melee.remove(i);
			i--;
		}
		}
		
		for (int i=0; i< ranged.size();i++)
		{
		if (ranged.get(i).atack==atackMax)
		{	
			ranged.get(i).setVisible(false);
			pontosRanged-=ranged.get(i).atack;
			ranged.get(i).atack=ranged.get(i).atackInicial;
			graveyard.add(ranged.get(i));
			ranged.remove(i);
			i--;
		}
		}
		
		for (int i=0; i< siege.size();i++)
		{
		if (siege.get(i).atack==atackMax)
		{	
			siege.get(i).setVisible(false);
			pontosSiege-=siege.get(i).atack;
			siege.get(i).atack=siege.get(i).atackInicial;
			graveyard.add(siege.get(i));
			siege.remove(i);
			i--;
		}
		}
		
	}
	/**
	 * Escolhe o range para fazer uma jogada com efeito enimiga
	 * @param carta
	 */
	void FazerJogadaEfeitoAi(MyCard carta)
	{
		if (carta.range=="Melee")
		{
			FazerJogadaEfeitoMeleeAi(carta);
		}
		if (carta.range=="Ranged")
		{
			FazerJogadaEfeitoRangedAi(carta);
		}
		if (carta.range=="Siege")
		{
			FazerJogadaEfeitoSiegeAi(carta);
		}
	}
	/**
	 * Realiza uma jogada inimiga "Melee" de uma carta com efeito
	 * @param carta carta jogada
	 */
	void FazerJogadaEfeitoMeleeAi(MyCard carta)
	{
		if (carta.efeito=="Spy")
		{
			carta.setSize(90,99);
			carta.setPosition((float) (364+melee.size()*(carta.getWidth())), 318);
			carta.setTouchable(null);
			melee.add(carta);
			pontosMelee+=carta.atack;
			cartasEnemie.remove(carta);
			jogada++;
			for (int i=0; i<2;i++)
			{
				int index = 0 + (int)(Math.random() * (deckEnemie.size()-1)); 
				cartasEnemie.add(deckEnemie.get(index));
				deckEnemie.remove(index);
				cartasEnemie.get(cartasEnemie.size()-1).setVisible(false);
			}
			
		}
		if (carta.efeito=="Scorcher")
		{
			carta.setSize(90,99);
			carta.setPosition((float) (364+melee.size()*(carta.getWidth())), 423);
			carta.setTouchable(null);
			meleeEnemie.add(carta);
			pontosMelee2+=carta.atack;
			cartasEnemie.remove(carta);
			jogada++;
			scorcherAi();
			
		}
		if(carta.efeito=="Reviver")
		{
			carta.setSize(90,99);
			carta.setPosition((float) (364+melee.size()*(carta.getWidth())), 423);
			carta.setTouchable(null);
			meleeEnemie.add(carta);
			pontosMelee2+=carta.atack;
			cartasEnemie.remove(carta);
			jogada++;
			int index = 0 + (int)(Math.random() * (graveyardEnemie.size()-1));
			if(graveyardEnemie.size()>0)
			{
			cartasEnemie.add(graveyardEnemie.get(index));
			graveyardEnemie.remove(index);
			}

			
			
		}
		if(carta.efeito=="Booster")
		{
			System.out.println("BOOSTER");
			pontosMelee2=0;
			carta.setSize(90,99);
			carta.setPosition((float) (364+melee.size()*(carta.getWidth())), 423);
			carta.setTouchable(null);
			meleeEnemie.add(carta);
			pontosMelee2+=carta.atack;
			cartasEnemie.remove(carta);
			jogada++;
			for(int i=0;i <meleeEnemie.size()-1;i++)
			{
				meleeEnemie.get(i).atack++;
				pontosMelee2+=meleeEnemie.get(i).atack;
			}
		}
	}
	/**
	 * Realiza uma jogada "Ranged" de uma carta com efeito
	 * @param carta carta jogada
	 */
	void FazerJogadaEfeitoRangedAi(MyCard carta)
	{
		if (carta.efeito=="Spy")
		{
			carta.setSize(90,99);
			carta.setPosition((float) (364+melee.size()*(carta.getWidth())), 219);
			carta.setTouchable(null);
			ranged.add(carta);
			pontosRanged+=carta.atack;
			cartasEnemie.remove(carta);
			jogada++;
			if(deckEnemie.size()>0)
			{
			for (int i=0; i<2;i++)
			{
				int index = 0 + (int)(Math.random() * (deckEnemie.size()-1)); 
				cartasEnemie.add(deckEnemie.get(index));
				deckEnemie.remove(index);
				cartasEnemie.get(cartasEnemie.size()-1).setVisible(false);
			}
			}
			
		}
		if (carta.efeito=="Scorcher")
		{
			carta.setSize(90,99);
			carta.setPosition((float) (364+melee.size()*(carta.getWidth())), 522);
			carta.setTouchable(null);
			rangedEnemie.add(carta);
			pontosRanged2+=carta.atack;
			cartasEnemie.remove(carta);
			jogada++;
			scorcherAi();
			
		}
		if(carta.efeito=="Reviver")
		{
			carta.setSize(90,99);
			carta.setPosition((float) (364+melee.size()*(carta.getWidth())), 522);
			carta.setTouchable(null);
			rangedEnemie.add(carta);
			pontosRanged2+=carta.atack;
			cartasEnemie.remove(carta);
			jogada++;
			int index = 0 + (int)(Math.random() * (graveyardEnemie.size()-1));
			if(graveyardEnemie.size()>0)
			{
			cartasEnemie.add(graveyardEnemie.get(index));
			graveyardEnemie.remove(index);
			}

			
			
		}
		if(carta.efeito=="Booster")
		{
			System.out.println("BOOSTER");
			pontosRanged2=0;
			carta.setSize(90,99);
			carta.setPosition((float) (364+melee.size()*(carta.getWidth())), 522);
			carta.setTouchable(null);
			rangedEnemie.add(carta);
			pontosRanged2+=carta.atack;
			cartasEnemie.remove(carta);
			jogada++;
			for(int i=0;i <rangedEnemie.size()-1;i++)
			{
				rangedEnemie.get(i).atack++;
				pontosRanged2+=rangedEnemie.get(i).atack;
			}
		}
	}
	/**
	 * Realiza uma jogada "Siege" de uma carta com efeito
	 * @param carta carta jogada
	 */	
	void FazerJogadaEfeitoSiegeAi(MyCard carta)
	{
		if (carta.efeito=="Spy")
		{
			carta.setSize(90,99);
			carta.setPosition((float) (364+melee.size()*(carta.getWidth())), 120);
			carta.setTouchable(null);
			siege.add(carta);
			pontosSiege+=carta.atack;
			cartasEnemie.remove(carta);
			jogada++;
			if(deckEnemie.size()>0)
			{
			for (int i=0; i<2;i++)
			{
				int index = 0 + (int)(Math.random() * (deckEnemie.size()-1)); 
				cartasEnemie.add(deckEnemie.get(index));
				deckEnemie.remove(index);
				cartasEnemie.get(cartasEnemie.size()-1).setVisible(false);
			}
			}
			
		}
		if (carta.efeito=="Scorcher")
		{
			carta.setSize(90,99);
			carta.setPosition((float) (364+melee.size()*(carta.getWidth())), 621);
			carta.setTouchable(null);
			siegeEnemie.add(carta);
			pontosSiege2+=carta.atack;
			cartasEnemie.remove(carta);
			jogada++;
			scorcherAi();
			
		}
		if(carta.efeito=="Reviver")
		{
			carta.setSize(90,99);
			carta.setPosition((float) (364+melee.size()*(carta.getWidth())), 621);
			carta.setTouchable(null);
			siegeEnemie.add(carta);
			pontosSiege2+=carta.atack;
			cartasEnemie.remove(carta);
			jogada++;
			int index = 0 + (int)(Math.random() * (graveyardEnemie.size()-1));
			if(graveyardEnemie.size()>0)
			{
			cartasEnemie.add(graveyardEnemie.get(index));
			graveyardEnemie.remove(index);
			}

			
			
		}
		if(carta.efeito=="Booster")
		{
			System.out.println("BOOSTER");
			pontosSiege2=0;
			carta.setSize(90,99);
			carta.setPosition((float) (364+melee.size()*(carta.getWidth())), 621);
			carta.setTouchable(null);
			siegeEnemie.add(carta);
			pontosSiege2+=carta.atack;
			cartasEnemie.remove(carta);
			jogada++;
			for(int i=0;i <siegeEnemie.size()-1;i++)
			{
				siegeEnemie.get(i).atack++;
				pontosSiege2+=siegeEnemie.get(i).atack;
			}
		}
	}
	/**
	 * Desenha os efeitos de cada estado de jogo
	 */
	void DesenhaStates()
	{
		game.batch.begin();
		for(int i=0;i<states.size();i++)
		{
			System.out.println("heyyyy "+states.get(i));
			if(states.get(i)==1)
			{	
				/*blizzard.setPosition(364, 318);
				blizzard.draw(game.batch);
				blizzard.setPosition(364, 423);
				blizzard.draw(game.batch);*/
				
				game.batch.draw(blizzardAnimation.getKeyFrame(timepassed,true),364,318,916,99);
				game.batch.draw(blizzardAnimation.getKeyFrame(timepassed,true),364,423,916,99);
				
			
		
			}
			else if(states.get(i)==2)
			{
				/*
				blizzard.setPosition(364, 219);
				blizzard.draw(game.batch);
				blizzard.setPosition(364, 522);
				blizzard.draw(game.batch);*/

				game.batch.draw(blizzardAnimation.getKeyFrame(timepassed,true),364,219,916,99);
				game.batch.draw(blizzardAnimation.getKeyFrame(timepassed,true),364,522,916,99);
				
		
			}
			else if(states.get(i)==3)
			{
				/*
				blizzard.setPosition(364, 120);
				blizzard.draw(game.batch);
				blizzard.setPosition(364, 621);
				blizzard.draw(game.batch);*/
				
				game.batch.draw(blizzardAnimation.getKeyFrame(timepassed,true),364,120,916,99);
				game.batch.draw(blizzardAnimation.getKeyFrame(timepassed,true),364,621,916,99);
			}
			else if(states.get(i)==7)
			{	
				btnMelee.setSize(500, 100);
				btnRanged.setSize(500, 100);
				btnSiege.setSize(500, 100);
				btnMelee.setVisible(true);
				btnRanged.setVisible(true);
				btnSiege.setVisible(true);
				stage.addActor(btnSiege);
				stage.addActor(btnMelee);
				stage.addActor(btnRanged);
				states.remove(i);
				i--;
			}
			else if(states.get(i)==8)
			{
				/*
				empower.setPosition(263, 320);
				empower.draw(game.batch);*/
				
				game.batch.draw(empowerAnimation.getKeyFrame(timepassed,true),263,320,90,99);
				
			}
			else if(states.get(i)==9)
			{
		
				/*empower.setPosition(263, 224);
				empower.draw(game.batch);*/
				game.batch.draw(empowerAnimation.getKeyFrame(timepassed,true),263,224,90,99);
			
			}
			else if(states.get(i)==10)
			{
				
				/*empower.setPosition(263, 125);
				empower.draw(game.batch);*/
				game.batch.draw(empowerAnimation.getKeyFrame(timepassed,true),263,125,90,99);
				
			}
			else if(states.get(i)==11)
			{
				
				/*empower.setPosition(263, 425);
				empower.draw(game.batch);*/
				game.batch.draw(empowerAnimation.getKeyFrame(timepassed,true),263,425,90,99);
			
			}
			else if(states.get(i)==12)
			{
			
				/*empower.setPosition(263, 524);
				empower.draw(game.batch);*/
				game.batch.draw(empowerAnimation.getKeyFrame(timepassed,true),263,524,90,99);

			}
			else if(states.get(i)==13)
			{
				/*empower.setPosition(263, 623);
				empower.draw(game.batch);*/
				game.batch.draw(empowerAnimation.getKeyFrame(timepassed,true),263,623,90,99);
			}
			else if(states.get(i)==15)
			{
				timepassedsunny+=Gdx.graphics.getDeltaTime();
				game.batch.draw(sunnyAnimation.getKeyFrame(timepassedsunny,true),0,0,1280,720);
				if(sunnyAnimation.isAnimationFinished(timepassedsunny))
				{
					states.clear();
				}
			}
			else if(states.get(i)==16)
			{
				
			}
		}
		game.batch.end();
	}
}


		
