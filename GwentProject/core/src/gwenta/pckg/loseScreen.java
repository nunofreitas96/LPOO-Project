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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import gwenta.Gwent;
/**
 * 
 * @author Paulo Babo
 *
 */
public class loseScreen implements Screen { 
	 
	    final Gwent game;

	    Skin skin;
	    Texture rematchBtnText;
	    Sprite rematchBtnSprite;
	    Texture exitBtnText;
	    Sprite exitBtnSprite;
	    Texture returnBtnText;
	    Sprite returnBtnSprite;
	    Texture loseScreenText;
	    Sprite loseScreen;
	    Stage stage;
	    Button rematchBtn;
	    Button returnBtn;
	    Button exitBtn;// Use the initialized skin
	    OrthographicCamera camera;
		Viewport viewport;

	    public loseScreen(final Gwent gam){
	        game = gam;
	        camera = new OrthographicCamera();
			viewport = new StretchViewport(1280, 720, camera);
	        this.stage = new Stage();
	        stage.setViewport(viewport);
	        rematchBtnText=new Texture("rematchBtn.png");
		    rematchBtnSprite=new Sprite(rematchBtnText);
		    exitBtnText=new Texture("exitBtn.png");
		    exitBtnSprite=new Sprite(exitBtnText);
		    returnBtnText=new Texture("returnBtn.png");
		    returnBtnSprite=new Sprite(returnBtnText);
		    loseScreenText= new Texture("loseScreen.png");
		    loseScreen= new Sprite(loseScreenText);
		    returnBtnSprite.setSize(300, 150);
		    exitBtnSprite.setSize(300, 150);
		    rematchBtnSprite.setSize(300, 150);
		    loseScreen.setSize(1280, 720);
	        Gdx.input.setInputProcessor(stage);// Make the stage consume events

	        returnBtn = new Button(new SpriteDrawable(returnBtnSprite),
		            new SpriteDrawable(returnBtnSprite));
		    returnBtn.addListener(new ClickListener() {
		        public void clicked(InputEvent event, float x, float y) {
		        	game.setScreen(new MainMenu(game));
		        }
		    });
		    returnBtn.setPosition(480, 420);
		    stage.addActor(returnBtn);
		    
	        exitBtn = new Button(new SpriteDrawable(exitBtnSprite),
		            new SpriteDrawable(exitBtnSprite));
		    exitBtn.addListener(new ClickListener() {
		        public void clicked(InputEvent event, float x, float y) {
		        	System.exit(0);
		        }
		    });
		    exitBtn.setPosition(480, 220);
		    stage.addActor(exitBtn);
		    
	        rematchBtn = new Button(new SpriteDrawable(rematchBtnSprite),
		            new SpriteDrawable(rematchBtnSprite));
		    rematchBtn.addListener(new ClickListener() {
		        public void clicked(InputEvent event, float x, float y) {
		        	game.setScreen(new gwentAndroid(game));
		        }
		    });
		    rematchBtn.setPosition(480,20);
		    stage.addActor(rematchBtn);


	    }

	    //TODO Make actual SKin

	    /**
	     * Skin for Main Menu
	     */

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
	        loseScreen.draw(game.batch);
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
