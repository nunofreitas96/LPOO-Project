package gwenta.pckg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.IOException;
import java.util.ArrayList;

import gwenta.Gwent;
import gwenta.logic.ACard;

/**
 * Created by Repas on 01/06/2016.
 */
public class MainMenu implements Screen {
    final Gwent game;

    Skin skin;
    Button newBtn;
    Button deckBtn;
    Button exitBtn;
    Button deck1Btn;
    Button deck2Btn;
    Button deck3Btn;
    Button deck4Btn;
    Button deck5Btn;
    Stage stage;
    TextButton newGameButton;// Use the initialized skin
    TextButton DeckBuilderButton;
    Texture newText;
    Sprite newSprite;
    Texture exitText;
    Sprite exitSprite;
    Texture deckText;
    Sprite deckSprite;
    Texture fundoText;
    Sprite fundoSprite;
    Texture deck1Text;
    Sprite deck1Sprite;
    Texture deck2Text;
    Sprite deck2Sprite;
    Texture deck3Text;
    Sprite deck3Sprite;
    Texture deck4Text;
    Sprite deck4Sprite;
    Texture deck5Text;
    Sprite deck5Sprite;
    Texture deck1CText;
    Sprite deck1CSprite;
    Texture deck2CText;
    Sprite deck2CSprite;
    Texture deck3CText;
    Sprite deck3CSprite;
    Texture deck4CText;
    Sprite deck4CSprite;
    Texture deck5CText;
    Sprite deck5CSprite;
    Saver saver;
    OrthographicCamera camera;
	Viewport viewport;
    public MainMenu(final Gwent gam){
    	camera = new OrthographicCamera();
		viewport = new StretchViewport(1280, 720, camera);
        game = gam;
        saver = new Saver(game);
        this.stage = new Stage();
        stage.setViewport(viewport);
        Gdx.input.setInputProcessor(stage);// Make the stage consume events
        newText= new Texture("newGameBtn.png");
        newSprite= new Sprite(newText);
        exitText= new Texture("exitMenuBtn.png");
        exitSprite= new Sprite(exitText);
        deckText= new Texture("deckBuilderBtn.png");
        deckSprite= new Sprite(deckText);
        fundoText=new Texture("mainMenu.png");
        fundoSprite=new Sprite(fundoText);
        
        //BUTOES DECK
        deck1Text= new Texture("deck1Btn.png");
        deck1Sprite= new Sprite(deck1Text);
        deck2Text= new Texture("deck2Btn.png");
        deck2Sprite= new Sprite(deck2Text);
        deck3Text= new Texture("deck3Btn.png");
        deck3Sprite= new Sprite(deck3Text);
        deck4Text= new Texture("deck4Btn.png");
        deck4Sprite= new Sprite(deck4Text);
        deck5Text= new Texture("deck5Btn.png");
        deck5Sprite= new Sprite(deck5Text);
        deck1CText= new Texture("deck1BtnChecked.png");
        deck1CSprite= new Sprite(deck1CText);
        deck2CText= new Texture("deck2BtnChecked.png");
        deck2CSprite= new Sprite(deck2CText);
        deck3CText= new Texture("deck3BtnChecked.png");
        deck3CSprite= new Sprite(deck3CText);
        deck4CText= new Texture("deck4BtnChecked.png");
        deck4CSprite= new Sprite(deck4CText);
        deck5CText= new Texture("deck5BtnChecked.png");
        deck5CSprite= new Sprite(deck5CText);
        
        
        createBasicSkin();
        this.newGameButton = new TextButton("New game", skin);
        newGameButton.addListener( new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new gwentAndroid(game));
            }
        });
        //stage.addActor(newGameButton);

        this.DeckBuilderButton = new TextButton("Deck Builder", skin);
        DeckBuilderButton.addListener( new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                dispose();

                ArrayList<ArrayList<ACard>> Decks;


                try {
                    Decks = saver.readDecks();


                } catch (IOException e) {
                    e.printStackTrace();
                    Decks = new ArrayList<ArrayList<ACard>>();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    Decks = new ArrayList<ArrayList<ACard>>();

                }
                    System.out.println("found it!");

                if(Decks.isEmpty()){
                    Decks = new ArrayList<ArrayList<ACard>>();
                    for(int i =0; i < 5; i++){
                        ArrayList<ACard> exDeck = new ArrayList<ACard>();
                        Decks.add(exDeck);
                    }
                    System.out.println("Didnt find" + Gdx.files.getLocalStoragePath());
                    try {
                        saver.saveDecks(Decks);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                game.setScreen(new DeckBuilder(game,Decks));
            }
        });
        
        newBtn = new Button(new SpriteDrawable(newSprite),
	            new SpriteDrawable(newSprite));
	    newBtn.addListener(new ClickListener() {
	        public void clicked(InputEvent event, float x, float y) {

                ArrayList<ArrayList<ACard>> Decks;


                try {
                    Decks = saver.readDecks();


                } catch (IOException e) {
                    e.printStackTrace();
                    Decks = new ArrayList<ArrayList<ACard>>();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    Decks = new ArrayList<ArrayList<ACard>>();

                }
                System.out.println("found it!");

                if(Decks.isEmpty()){
                    Decks = new ArrayList<ArrayList<ACard>>();
                    for(int i =0; i < 5; i++){
                        ArrayList<ACard> exDeck = new ArrayList<ACard>();
                        Decks.add(exDeck);
                    }
                    System.out.println("Didnt find" + Gdx.files.getLocalStoragePath());
                    try {
                        saver.saveDecks(Decks);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(Decks.get(game.deckIndex).size() >= 10){
	        	 game.setScreen(new gwentAndroid(game));}
	        }
	    });
	    newBtn.setSize(200, 75);
        newBtn.setPosition(350,420);
        stage.addActor(newBtn);
        
        
        exitBtn = new Button(new SpriteDrawable(exitSprite),
	            new SpriteDrawable(exitSprite));
	    exitBtn.addListener(new ClickListener() {
	        public void clicked(InputEvent event, float x, float y) {
	        	System.exit(0);
	        }
	    });
	    exitBtn.setSize(200, 75);
        exitBtn.setPosition(350,20);
        stage.addActor(exitBtn);
        
        deckBtn = new Button(new SpriteDrawable(deckSprite),
	            new SpriteDrawable(deckSprite));
	    deckBtn.addListener(new ClickListener() {
	        public void clicked(InputEvent event, float x, float y) {
	        	  ArrayList<ArrayList<ACard>> Decks;


	                try {
	                    Decks = saver.readDecks();


	                } catch (IOException e) {
	                    e.printStackTrace();
	                    Decks = new ArrayList<ArrayList<ACard>>();

	                } catch (ClassNotFoundException e) {
	                    e.printStackTrace();
	                    Decks = new ArrayList<ArrayList<ACard>>();

	                }
	                    System.out.println("found it!");

	                if(Decks.isEmpty()){
	                    Decks = new ArrayList<ArrayList<ACard>>();
	                    for(int i =0; i < 5; i++){
	                        ArrayList<ACard> exDeck = new ArrayList<ACard>();
	                        Decks.add(exDeck);
	                    }
	                    System.out.println("Didnt find" + Gdx.files.getLocalStoragePath());
	                    try {
	                        saver.saveDecks(Decks);
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	                }

	                game.setScreen(new DeckBuilder(game,Decks));
	        }
	    });
	    
	    deckBtn.setSize(200, 75);
        deckBtn.setPosition(350,220);
        stage.addActor(deckBtn); 
        
        //DECK BUTOES
        
        deck1Btn = new Button(new SpriteDrawable(deck1Sprite),
	            new SpriteDrawable(deck1CSprite));
        deck1Btn.getStyle().checked=deck1Btn.getStyle().down;
	    deck1Btn.addListener(new ClickListener() {
	        public void clicked(InputEvent event, float x, float y) {
	        	((Button) event.getTarget()).setChecked(true);
	        	deck2Btn.setChecked(false);
	        	deck3Btn.setChecked(false);
	        	deck4Btn.setChecked(false);
	        	deck5Btn.setChecked(false);
	        	game.deckIndex=0;
	        }
	    });
	    deck1Btn.setSize(200, 75);
        deck1Btn.setPosition(800,420);
        stage.addActor(deck1Btn);
        
        deck2Btn = new Button(new SpriteDrawable(deck2Sprite),
	            new SpriteDrawable(deck2CSprite));
        deck2Btn.getStyle().checked=deck2Btn.getStyle().down;
	    deck2Btn.addListener(new ClickListener() {
	        public void clicked(InputEvent event, float x, float y) {
	        	((Button) event.getTarget()).setChecked(true);
	        	deck1Btn.setChecked(false);
	        	deck3Btn.setChecked(false);
	        	deck4Btn.setChecked(false);
	        	deck5Btn.setChecked(false);
	        	game.deckIndex=1;
	        }
	    });
	    deck2Btn.setSize(200, 75);
        deck2Btn.setPosition(800,320);
        stage.addActor(deck2Btn);
        
        deck3Btn = new Button(new SpriteDrawable(deck3Sprite),
	            new SpriteDrawable(deck3CSprite));
        deck3Btn.getStyle().checked=deck3Btn.getStyle().down;
	    deck3Btn.addListener(new ClickListener() {
	        public void clicked(InputEvent event, float x, float y) {
	        	((Button) event.getTarget()).setChecked(true);
	        	deck1Btn.setChecked(false);
	        	deck2Btn.setChecked(false);
	        	deck4Btn.setChecked(false);
	        	deck5Btn.setChecked(false);
	        	game.deckIndex=2;
	        }
	    });
	    deck3Btn.setSize(200, 75);
        deck3Btn.setPosition(800,220);
        stage.addActor(deck3Btn);
        
        deck4Btn = new Button(new SpriteDrawable(deck4Sprite),
	            new SpriteDrawable(deck4CSprite));
        deck4Btn.getStyle().checked=deck4Btn.getStyle().down;
	    deck4Btn.addListener(new ClickListener() {
	        public void clicked(InputEvent event, float x, float y) {
	        	((Button) event.getTarget()).setChecked(true);
	        	deck1Btn.setChecked(false);
	        	deck2Btn.setChecked(false);
	        	deck3Btn.setChecked(false);
	        	deck5Btn.setChecked(false);
	        	game.deckIndex=3;
	        }
	    });
	    deck4Btn.setSize(200, 75);
        deck4Btn.setPosition(800,120);
        stage.addActor(deck4Btn);
        
        deck5Btn = new Button(new SpriteDrawable(deck5Sprite),
	            new SpriteDrawable(deck5CSprite));
        deck5Btn.getStyle().checked=deck5Btn.getStyle().down;
	    deck5Btn.addListener(new ClickListener() {
	        public void clicked(InputEvent event, float x, float y) {
	        	((Button) event.getTarget()).setChecked(true);
	        	deck1Btn.setChecked(false);
	        	deck2Btn.setChecked(false);
	        	deck3Btn.setChecked(false);
	        	deck4Btn.setChecked(false);
	        	game.deckIndex=4;
	        }
	    });
	    deck5Btn.setSize(200, 75);
        deck5Btn.setPosition(800,20);
        stage.addActor(deck5Btn);
        
        if(game.deckIndex==0)
        {
        	deck1Btn.setChecked(true);
        }
        if(game.deckIndex==1)
        {
        	deck2Btn.setChecked(true);
        }
        if(game.deckIndex==2)
        {
        	deck3Btn.setChecked(true);
        }
        if(game.deckIndex==3)
        {
        	deck4Btn.setChecked(true);
        }
        if(game.deckIndex==4)
        {
        	deck5Btn.setChecked(true);
        }
        
        
        
        fundoSprite.setSize(1280, 720);
        


    }

    //TODO Make actual SKin

    /**
     * Skin for Main Menu
     */
    private void createBasicSkin(){
        //Create a font
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        skin.add("background",new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

    }

    public void handleInput(){

    }

    public void update(float dt){

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        fundoSprite.draw(game.batch);
        game.batch.end();
        stage.act();
        stage.draw();
     
    }

    @Override
    public void resize(int width, int height) {
    	viewport.update(width, height,true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
