package com.cmscproject.collegerun.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * 	GameStateManager
 * Class gamestatemanager
 * @author Harrison Cook
 */

public class GameStateManager {
    public final Stack<States> states;


    /**
     * Constructor initializes the gamestatemanager with stack of states
     */
    public GameStateManager(){
        states = new Stack<States>();
    }
    /**
     * add state to stack
     * @param state state to display
     */
    public void add(States state){
        states.push(state);
    }
    /**
     * delete state to stack
     */
    public void pop(){
        states.pop();
    }
    /**
     * removes current state and adds new one
     * @param state state to display
     */
    public void setState(States state){
        states.pop();
        states.push(state);
    }

    /**
     * peek a current state
     * @param dt time change
     */
    public void update(float dt){
        states.peek().update(dt);
    }


    /**
     * render states
     * @param spriteBatch image to display
     */
    public void render(SpriteBatch spriteBatch){
        states.peek().render(spriteBatch);
    }


}
