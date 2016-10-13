package gwenta.pckg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import gwenta.Gwent;
import gwenta.logic.ACard;

/**
 * Created by Repas on 03/06/2016.
 */
public class DeckList implements Screen {
    final Gwent game;
    Texture CardImage;
    Sprite Carder;
    ArrayList<ACard> Deck;
    Texture returnText;
    Sprite returner;
    Button returnBtn;
    Stage stage;
    Texture BackText;
    Sprite Back;
    OrthographicCamera camera;
  	Viewport viewport;
    final ArrayList<ArrayList<ACard>> Decks;
    public DeckList(final Gwent game, ArrayList<ACard> Deck, final ArrayList<ArrayList<ACard>> Decks) {
        this.game = game;
        this.Deck = Deck;
        this.Decks = Decks;
        camera = new OrthographicCamera();
		viewport = new StretchViewport(1280, 720, camera);
        stage = new Stage();
        stage.setViewport(viewport);
        Gdx.input.setInputProcessor(stage);
        returnText = new Texture("returnBtn2.png");
        returner = new Sprite(returnText);
        returner.setSize((returner.getWidth()/2),(returner.getHeight()/2));
        BackText = new Texture("DeckBack.png");
        Back = new Sprite(BackText);
        Back.setSize(1280, 720);

        returnBtn = new Button(new SpriteDrawable(returner),
                new SpriteDrawable(returner));
        returnBtn.getStyle().checked = returnBtn.getStyle().down;
        returnBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                ((Button) event.getTarget()).setChecked(true);
                System.out.println("out");
                stage.dispose();
                game.setScreen(new DeckBuilder(game,Decks));

            }
        });
        returnBtn.setPosition(400 , 300);
        stage.addActor(returnBtn);



    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        int number;
        if(Deck.size() ==1){
            number = 880;
        }else {
            number = 880 / (Deck.size() / 2);
        }
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        Back.draw(game.batch);
        for(int i =0; i< Deck.size(); i++){
            CardImage = new Texture("" + Deck.get(i).getId() + ".png");
            Carder = new Sprite(CardImage);
            Carder.setSize(Carder.getWidth()/3, Carder.getHeight()/3);
            if(i < Deck.size()/2){
                Carder.setPosition(number*(i+1),400);

            }
            else{
                Carder.setPosition(number*((i-Deck.size()/2)+1),100);

            }


            //System.out.println("printing" + i + "  " + (number*((i-Deck.size()/2)+1)));
            Carder.draw(game.batch);



        }
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

    }
}
