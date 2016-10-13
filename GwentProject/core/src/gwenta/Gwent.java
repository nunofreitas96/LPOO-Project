package gwenta;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import gwenta.pckg.MainMenu;

/**
 * Created by Repas on 01/06/2016.
 */
public class Gwent extends Game implements ApplicationListener{
    public static final int WIDTH = 720;
    public static final int LENGTH = 1280;
    public SpriteBatch batch;
    public BitmapFont font;
    public int deckIndex=0;
    Texture img;
    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        img = new Texture("badlogic.jpg");
        this.setScreen(new MainMenu(this));
    }

    @Override
    public void render(){
       super.render();

    }

}
