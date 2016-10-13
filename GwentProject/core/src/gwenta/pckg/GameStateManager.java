package gwenta.pckg;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Repas on 01/06/2016.
 */
public class GameStateManager {

   private Stack<State> states;

    public GameStateManager(){
        this.states = new Stack<State>();
    }


    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop();
    }
}
