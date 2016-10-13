package gwenta.pckg;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.IOException;
import java.util.ArrayList;

import gwenta.Gwent;
import gwenta.logic.ACard;
import gwenta.logic.Card;
import gwenta.logic.UnitCard;

/**
 * Created by Repas on 03/06/2016.
 */
public class DeckBuilder implements Screen{

    Sprite CardButton;
    Texture CardText;
    Texture CardClickedText;
    Sprite CardClicked;
    Texture PageButtonText;
    Sprite PageButton;
    Texture PageButtonTextChecked;
    Sprite PageButtonChecked;
    Texture PageButtonText2;
    Sprite PageButton2;
    Texture PageButtonTextChecked2;
    Sprite PageButtonChecked2;
    Texture DeckBTNTexture;
    Sprite DeckBTN;
    Texture Deck1T;
    Texture Deck2T;
    Texture Deck3T;
    Texture Deck4T;
    Texture Deck5T;
    Sprite Deck1S;
    Sprite Deck2S;
    Sprite Deck3S;
    Sprite Deck4S;
    Sprite Deck5S;
    Texture returnText;
    Sprite returner;
    Texture eraseText;
    Sprite eraser;
    Texture eraseTextChecked;
    Sprite eraserChecked;
    Texture BackText;
    Sprite Back;
    ArrayList<ACard> Deck;
    final ArrayList<ArrayList<ACard>> Decks;
    final Gwent game;
    Saver saver;
    Stage stage;
    Button card1;
    Button card2;
    Button card3;
    Button card4;
    Button card5;
    Button card6;
    Button card7;
    Button card8;
    Button pager;
    Button pager2;
    Button deck;
    Button Deck1;
    Button Deck2;
    Button Deck3;
    Button Deck4;
    Button Deck5;
    Button returnBtn;
    Button eraserBtn;
    BitmapFont atacktxt;
    int page;
    int deckCounter;
    OrthographicCamera camera;
	Viewport viewport;
    public DeckBuilder(final Gwent game, final ArrayList<ArrayList<ACard>> Deckes) {
        this.game = game;
        this.saver = new Saver(game);
        camera = new OrthographicCamera();
		viewport = new StretchViewport(1280, 720, camera);
        Decks = Deckes;
        deckCounter =0;
        CardText = new Texture("carta.png");
        CardButton = new Sprite(CardText);
        CardButton.setSize(CardButton.getWidth()/3, CardButton.getHeight()/3);
        CardClickedText = new Texture("cartachecked.png");
        CardClicked = new Sprite(CardClickedText);
        PageButtonText = new Texture("arrow2.png");
        PageButtonTextChecked = new Texture("arrow1.png");
        PageButton = new Sprite(PageButtonText);
        PageButtonChecked = new Sprite(PageButtonTextChecked);
        PageButtonText2 = new Texture("arrow3.png");
        PageButtonTextChecked2 = new Texture("arrow4.png");
        PageButton2 = new Sprite(PageButtonText2);
        PageButtonChecked2 = new Sprite(PageButtonTextChecked2);
        DeckBTNTexture = new Texture("DeckBtn.png");
        DeckBTN = new Sprite(DeckBTNTexture);
        Deck1T = new Texture("Deck1.png");
        Deck1S = new Sprite(Deck1T);
        Deck2T = new Texture("Deck2.png");
        Deck2S = new Sprite(Deck2T);
        Deck3T = new Texture("Deck3.png");
        Deck3S = new Sprite(Deck3T);
        Deck4T = new Texture("Deck4.png");
        Deck4S = new Sprite(Deck4T);
        Deck5T = new Texture("Deck5.png");
        Deck5S = new Sprite(Deck5T);
        BackText = new Texture("DeckBack.png");
        Back = new Sprite(BackText);
        Back.setSize(1280, 720);

        returnText = new Texture("returnBtn2.png");
        returner = new Sprite(returnText);
        returner.setSize((returner.getWidth()/2),(returner.getHeight()/2));
        eraseText = new Texture("erase.png");
        eraser = new Sprite(eraseText);
        eraseTextChecked = new Texture("erasechecked.png");
        eraserChecked = new Sprite(eraseTextChecked);

        page = 0;
        Deck =  Decks.get(0);
        stage = new Stage(); //TODO
        stage.setViewport(viewport);


        //card buttons
        AddCardButtons();



        pager = new Button(new SpriteDrawable(PageButton),
                new SpriteDrawable(PageButtonChecked));
        pager.getStyle().checked = pager.getStyle().down;
        pager.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                ((Button) event.getTarget()).setChecked(true);
                removeCards();
                page++;
                pager.setChecked(false);
                AddCardButtons();

            }
        });
        pager.setPosition(1200 , 350);
        stage.addActor(pager);


        pager2 = new Button(new SpriteDrawable(PageButton2),
                new SpriteDrawable(PageButtonChecked2));
        pager2.getStyle().checked = pager2.getStyle().down;
        pager2.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                ((Button) event.getTarget()).setChecked(true);
                removeCards();
                page--;
                pager2.setChecked(false);
                AddCardButtons();

            }
        });
            pager2.setPosition(50 , 350);
            stage.addActor(pager2);

        deck = new Button(new SpriteDrawable(DeckBTN),
                new SpriteDrawable(DeckBTN));
        deck.getStyle().checked = deck.getStyle().down;
        deck.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                ((Button) event.getTarget()).setChecked(true);

                if (!Deck.isEmpty()){
                    System.out.println(Deck.size());
                    stage.dispose();
                    game.setScreen(new DeckList(game,Deck,Decks));

                    try {
                        saver.saveDecks(Decks);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }}



            }
        });
        deck.setPosition(75 , 100);
        stage.addActor(deck);

        Deck1 = new Button(new SpriteDrawable(Deck1S), new SpriteDrawable(Deck1S));
        Deck1.getStyle().checked = Deck1.getStyle().down;
        Deck1.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                ((Button) event.getTarget()).setChecked(true);

                Deck = Decks.get(1);
                stage.addActor(Deck2);
                Deck1.remove();
                deckCounter++;

            }
        });
        Deck1.setPosition(1085,500);
        stage.addActor(Deck1);

        Deck2 = new Button(new SpriteDrawable(Deck2S), new SpriteDrawable(Deck2S));
        Deck2.getStyle().checked = Deck2.getStyle().down;
        Deck2.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                ((Button) event.getTarget()).setChecked(true);

                Deck = Decks.get(2);
                stage.addActor(Deck3);
                Deck2.remove();
                deckCounter++;

            }
        });
        Deck2.setPosition(1085,500);

        Deck3 = new Button(new SpriteDrawable(Deck3S), new SpriteDrawable(Deck3S));
        Deck3.getStyle().checked = Deck3.getStyle().down;
        Deck3.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                ((Button) event.getTarget()).setChecked(true);

                Deck = Decks.get(3);
                stage.addActor(Deck4);
                Deck3.remove();
                deckCounter++;

            }
        });
        Deck3.setPosition(1085,500);

        Deck4 = new Button(new SpriteDrawable(Deck4S), new SpriteDrawable(Deck4S));
        Deck4.getStyle().checked = Deck4.getStyle().down;
        Deck4.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                ((Button) event.getTarget()).setChecked(true);

                Deck = Decks.get(4);
                stage.addActor(Deck5);
                Deck4.remove();
                deckCounter++;

            }
        });
        Deck4.setPosition(1085,500);

        Deck5 = new Button(new SpriteDrawable(Deck5S), new SpriteDrawable(Deck5S));
        Deck5.getStyle().checked = Deck5.getStyle().down;
        Deck5.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                ((Button) event.getTarget()).setChecked(true);

                Deck = Decks.get(0);
                stage.addActor(Deck1);
                Deck5.remove();
                deckCounter =0;

            }
        });
        Deck5.setPosition(1085,500);

        returnBtn = new Button(new SpriteDrawable(returner), new SpriteDrawable(returner));
        returnBtn.getStyle().checked = returnBtn.getStyle().down;
        returnBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                ((Button) event.getTarget()).setChecked(true);

                game.setScreen(new MainMenu(game));

            }
        });
        returnBtn.setPosition(50,500);
        //returnBtn.scaleBy((float)0.5);
        stage.addActor(returnBtn);

        eraserBtn = new Button(new SpriteDrawable(eraser),
                new SpriteDrawable(eraserChecked));
        eraserBtn.getStyle().checked = eraserBtn.getStyle().down;
        eraserBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                ((Button) event.getTarget()).setChecked(true);

                Deck.clear();
                try {
                    saver.saveDecks(Decks);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                eraserBtn.setChecked(false);

            }
        });
        eraserBtn.setPosition(1100 , 600);
        stage.addActor(eraserBtn);

        Gdx.input.setInputProcessor(stage);


    }


    private void AddCardButtons(){
        String card = "card";
        CardText = new Texture( page * 8 + 8 + ".png");
        CardButton = new Sprite(CardText);
        CardButton.setSize( (float) (CardButton.getWidth()/2.7), CardButton.getHeight()/3);
        card1 = new Button(new SpriteDrawable(CardButton),
                new SpriteDrawable(CardClicked));
        card1.getStyle().checked = card1.getStyle().down;
        card1.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                ((Button) event.getTarget()).setChecked(true);

                System.out.println("deckbuilder test" + (page * 8 + 8));
                ACard carta = new UnitCard(page * 8 + 8);
                Deck.add(carta);
                Decks.set(deckCounter, Deck);
                card1.setChecked(false);
                try {
                    saver.saveDecks(Decks);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        card1.setPosition(300 , 100);
        stage.addActor(card1);
        if(page != 3) {
        CardText = new Texture(page * 8 + 9+ ".png");
        CardButton = new Sprite(CardText);
        CardButton.setSize( (float) (CardButton.getWidth()/2.7), CardButton.getHeight()/3);
        card2 = new Button(new SpriteDrawable(CardButton),
                new SpriteDrawable(CardClicked));
        card2.getStyle().checked = card2.getStyle().down;
        card2.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                ((Button) event.getTarget()).setChecked(true);

                System.out.println("deckbuilder test" + (page * 8 + 9));
                ACard carta = new UnitCard(page * 8 + 9);
                Deck.add(carta);
                Decks.set(deckCounter, Deck);
                card2.setChecked(false);
                try {
                    saver.saveDecks(Decks);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        card2.setPosition(500 , 100);
        stage.addActor(card2);

        CardText = new Texture(page * 8 + 10+ ".png");
        CardButton = new Sprite(CardText);
            CardButton.setSize( (float) (CardButton.getWidth()/2.7), CardButton.getHeight()/3);
        card3 = new Button(new SpriteDrawable(CardButton),
                new SpriteDrawable(CardClicked));
        card3.getStyle().checked = card3.getStyle().down;
        card3.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                ((Button) event.getTarget()).setChecked(true);

                System.out.println("deckbuilder test" +(page * 8 + 10));
                ACard carta = new UnitCard(page * 8 + 10);
                Deck.add(carta);
                Decks.set(deckCounter, Deck);
                card3.setChecked(false);
                try {
                    saver.saveDecks(Decks);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

            card3.setPosition(700, 100);
            stage.addActor(card3);


            CardText = new Texture(page * 8 + 11 + ".png");
            CardButton = new Sprite(CardText);
            CardButton.setSize( (float) (CardButton.getWidth()/2.7), CardButton.getHeight()/3);
            card4 = new Button(new SpriteDrawable(CardButton),
                    new SpriteDrawable(CardClicked));
            card4.getStyle().checked = card4.getStyle().down;
            card4.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {

                    ((Button) event.getTarget()).setChecked(true);

                    System.out.println("deckbuilder test" + (page * 8 + 11));
                    ACard carta = new UnitCard(page * 8 + 11);
                    Deck.add(carta);
                    Decks.set(deckCounter, Deck);
                    card4.setChecked(false);
                    try {
                        saver.saveDecks(Decks);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            card4.setPosition(900, 100);
            stage.addActor(card4);


            CardText = new Texture(page * 8 + 12 + ".png");
            CardButton = new Sprite(CardText);
            CardButton.setSize( (float) (CardButton.getWidth()/2.7), CardButton.getHeight()/3);
            card5 = new Button(new SpriteDrawable(CardButton),
                    new SpriteDrawable(CardClicked));
            card5.getStyle().checked = card5.getStyle().down;
            card5.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {

                    ((Button) event.getTarget()).setChecked(true);

                    System.out.println("deckbuilder test " + (page * 8 + 12));
                    ACard carta = new UnitCard(page * 8 + 12);
                    Deck.add(carta);
                    Decks.set(deckCounter, Deck);
                    card5.setChecked(false);
                    try {
                        saver.saveDecks(Decks);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            card5.setPosition(300, 400);
            stage.addActor(card5);

            CardText = new Texture(page * 8 + 13 + ".png");
            CardButton = new Sprite(CardText);
            CardButton.setSize( (float) (CardButton.getWidth()/2.7), CardButton.getHeight()/3);
            card6 = new Button(new SpriteDrawable(CardButton),
                    new SpriteDrawable(CardClicked));
            card6.getStyle().checked = card6.getStyle().down;
            card6.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {

                    ((Button) event.getTarget()).setChecked(true);

                    System.out.println("deckbuilder test" + (page * 8 + 13));
                    ACard carta = new UnitCard(page * 8 + 13);
                    Deck.add(carta);
                    Decks.set(deckCounter, Deck);
                    card6.setChecked(false);
                    try {
                        saver.saveDecks(Decks);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            });
            card6.setPosition(500, 400);
            stage.addActor(card6);

            CardText = new Texture(page * 8 + 14 + ".png");
            CardButton = new Sprite(CardText);
            CardButton.setSize( (float) (CardButton.getWidth()/2.7), CardButton.getHeight()/3);
            card7 = new Button(new SpriteDrawable(CardButton),
                    new SpriteDrawable(CardClicked));
            card7.getStyle().checked = card7.getStyle().down;
            card7.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {

                    ((Button) event.getTarget()).setChecked(true);

                    System.out.println("deckbuilder test" + (page * 8 + 14));
                    ACard carta = new UnitCard(page * 8 + 14);
                    Deck.add(carta);
                    Decks.set(deckCounter, Deck);
                    card7.setChecked(false);
                    try {
                        saver.saveDecks(Decks);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            card7.setPosition(700, 400);
            stage.addActor(card7);

            CardText = new Texture(page * 8 + 15 + ".png");
            CardButton = new Sprite(CardText);
            CardButton.setSize( (float) (CardButton.getWidth()/2.7), CardButton.getHeight()/3);
            card8 = new Button(new SpriteDrawable(CardButton),
                    new SpriteDrawable(CardClicked));
            card8.getStyle().checked = card8.getStyle().down;
            card8.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {

                    ((Button) event.getTarget()).setChecked(true);

                    System.out.println("deckbuilder test 1" + (page * 8 + 15));
                    ACard carta = new UnitCard(page * 8 + 15);
                    Deck.add(carta);
                    Decks.set(deckCounter, Deck);
                    card8.setChecked(false);
                    try {
                        saver.saveDecks(Decks);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            card8.setPosition(900, 400);
            stage.addActor(card8);
        }
        else{

            CardText = new Texture("7.png");
            CardButton = new Sprite(CardText);
            CardButton.setSize( (float) (CardButton.getWidth()/2.7), CardButton.getHeight()/3);
            card2 = new Button(new SpriteDrawable(CardButton),
                    new SpriteDrawable(CardClicked));
            card2.getStyle().checked = card2.getStyle().down;
            card2.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {

                    ((Button) event.getTarget()).setChecked(true);

                    System.out.println("deckbuilder test" + (7));
                    ACard carta = new UnitCard(7);
                    Deck.add(carta);
                    Decks.set(deckCounter, Deck);
                    card2.setChecked(false);
                    try {
                        saver.saveDecks(Decks);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            card2.setPosition(500 , 100);
            stage.addActor(card2);

            CardText = new Texture( "1.png");
            CardButton = new Sprite(CardText);
            CardButton.setSize( (float) (CardButton.getWidth()/2.7), CardButton.getHeight()/3);
            card3 = new Button(new SpriteDrawable(CardButton),
                    new SpriteDrawable(CardClicked));
            card3.getStyle().checked = card3.getStyle().down;
            card3.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {

                    ((Button) event.getTarget()).setChecked(true);

                    System.out.println("deckbuilder test" +(1));
                    ACard carta = new UnitCard(1);
                    Deck.add(carta);
                    Decks.set(deckCounter, Deck);
                    card3.setChecked(false);
                    try {
                        saver.saveDecks(Decks);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });

            card3.setPosition(700, 100);
            stage.addActor(card3);


            CardText = new Texture("2.png");
            CardButton = new Sprite(CardText);
            CardButton.setSize( (float) (CardButton.getWidth()/2.7), CardButton.getHeight()/3);
            card4 = new Button(new SpriteDrawable(CardButton),
                    new SpriteDrawable(CardClicked));
            card4.getStyle().checked = card4.getStyle().down;
            card4.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {

                    ((Button) event.getTarget()).setChecked(true);

                    System.out.println("deckbuilder test" + (2));
                    ACard carta = new UnitCard(2);
                    Deck.add(carta);
                    Decks.set(deckCounter, Deck);
                    card4.setChecked(false);
                    try {
                        saver.saveDecks(Decks);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            card4.setPosition(900, 100);
            stage.addActor(card4);


            CardText = new Texture("3.png");
            CardButton = new Sprite(CardText);
            CardButton.setSize( (float) (CardButton.getWidth()/2.7), CardButton.getHeight()/3);
            card5 = new Button(new SpriteDrawable(CardButton),
                    new SpriteDrawable(CardClicked));
            card5.getStyle().checked = card5.getStyle().down;
            card5.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {

                    ((Button) event.getTarget()).setChecked(true);

                    System.out.println("deckbuilder test " + (3));
                    ACard carta = new UnitCard(3);
                    Deck.add(carta);
                    Decks.set(deckCounter, Deck);
                    card5.setChecked(false);
                    try {
                        saver.saveDecks(Decks);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            card5.setPosition(300, 400);
            stage.addActor(card5);

            CardText = new Texture("4.png");
            CardButton = new Sprite(CardText);
            CardButton.setSize( (float) (CardButton.getWidth()/2.7), CardButton.getHeight()/3);
            card6 = new Button(new SpriteDrawable(CardButton),
                    new SpriteDrawable(CardClicked));
            card6.getStyle().checked = card6.getStyle().down;
            card6.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {

                    ((Button) event.getTarget()).setChecked(true);

                    System.out.println("deckbuilder test" + (4));
                    ACard carta = new UnitCard(4);
                    Deck.add(carta);
                    Decks.set(deckCounter, Deck);
                    card6.setChecked(false);
                    try {
                        saver.saveDecks(Decks);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            });
            card6.setPosition(500, 400);
            stage.addActor(card6);

            CardText = new Texture("5.png");
            CardButton = new Sprite(CardText);
            CardButton.setSize( (float) (CardButton.getWidth()/2.7), CardButton.getHeight()/3);
            card7 = new Button(new SpriteDrawable(CardButton),
                    new SpriteDrawable(CardClicked));
            card7.getStyle().checked = card7.getStyle().down;
            card7.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {

                    ((Button) event.getTarget()).setChecked(true);

                    System.out.println("deckbuilder test" + (5));
                    ACard carta = new UnitCard(5);
                    Deck.add(carta);
                    Decks.set(deckCounter, Deck);
                    card7.setChecked(false);
                    try {
                        saver.saveDecks(Decks);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            card7.setPosition(700, 400);
            stage.addActor(card7);

            CardText = new Texture("6.png");
            CardButton = new Sprite(CardText);
            CardButton.setSize( (float) (CardButton.getWidth()/2.7), CardButton.getHeight()/3);
            card8 = new Button(new SpriteDrawable(CardButton),
                    new SpriteDrawable(CardClicked));
            card8.getStyle().checked = card8.getStyle().down;
            card8.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {

                    ((Button) event.getTarget()).setChecked(true);

                    System.out.println("deckbuilder test 1" + 6);
                    ACard carta = new UnitCard(6);
                    Deck.add(carta);
                    Decks.set(deckCounter, Deck);
                    card8.setChecked(false);
                    try {
                        saver.saveDecks(Decks);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            card8.setPosition(900, 400);
            stage.addActor(card8);
        }
    }

    private void removeCards(){
        card1.remove();
        card2.remove();
        card3.remove();
        card4.remove();
        card5.remove();
        card6.remove();
        card7.remove();
        card8.remove();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /*
        if(page ==3){
            card3.remove();
            card4.remove();
            card5.remove();
            card6.remove();
            card7.remove();
            card8.remove();
        }
        else{
            stage.addActor(card3);
            stage.addActor(card4);
            stage.addActor(card5);
            stage.addActor(card6);
            stage.addActor(card7);
            stage.addActor(card8);
        }*/

        if(page >= 3){
            pager.remove();
        }
        else{
            pager.setPosition(1100 , 350);
            stage.addActor(pager);
        }


        if(page > 0){
            pager2.setPosition(50 , 350);
            stage.addActor(pager2);
        }
        else{
            pager2.remove();
        }
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        Back.draw(game.batch);


        game.batch.end();
        stage.act();
        stage.draw();
        game.batch.begin();
        atacktxt = new BitmapFont();
        ACard carta = new UnitCard(page * 8 + 8);
        atacktxt.getData().setScale((float)1.5,(float)1.5);
        atacktxt.draw(game.batch,"" + carta.findPower(carta.getId()), 405 , 170);
        carta = new UnitCard(page * 8 + 9);
        atacktxt.draw(game.batch,"" + carta.findPower(carta.getId()), 605 , 170);
        carta = new UnitCard(page * 8 + 10);
        atacktxt.draw(game.batch,"" + carta.findPower(carta.getId()), 805 , 170);
        carta = new UnitCard(page * 8 + 11);
        atacktxt.draw(game.batch,"" + carta.findPower(carta.getId()), 1005 , 170);
         carta = new UnitCard(page * 8 + 12);
        atacktxt.draw(game.batch,"" + carta.findPower(carta.getId()), 405 , 470);
        carta = new UnitCard(page * 8 + 13);
        atacktxt.draw(game.batch,"" + carta.findPower(carta.getId()), 605 , 470);
        carta = new UnitCard(page * 8 + 14);
        atacktxt.draw(game.batch,"" + carta.findPower(carta.getId()), 805 , 470);
        carta = new UnitCard(page * 8 + 15);
        atacktxt.draw(game.batch,"" + carta.findPower(carta.getId()), 1005 , 470);
        game.batch.end();



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
