package gwenta.pckg;


/**
 * Imports
 */

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Repas on 01/06/2016.
 */
public abstract class State {

    protected OrthographicCamera camera;
    //TODO if unnecessary remove
    protected Vector3 mouse;
    GameStateManager manager;


    /**
     * State Class Constructor
     * @param manager
     */
    protected State(GameStateManager manager){
        this.manager = manager;
        this.camera = new OrthographicCamera();
        this.mouse = new Vector3();
    }

    /**
     * Function Update for State - Will be used by all State Classes
     * @param dt
     */
    protected abstract void update(float dt);

    /**
     * Function Render for State - Will be used by all State Classes
     * @param sb
     */
    protected abstract void render(SpriteBatch sb);

    /**
     * Function that handles INput for State
     */
    protected abstract void handleInput();

}
