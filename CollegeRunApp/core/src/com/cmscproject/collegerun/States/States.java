package com.cmscproject.collegerun.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * 	States
 * Abstract class to create states
 * @author Harrison Cook
 */

abstract class States {
    final OrthographicCamera camera;
    final GameStateManager gameStateManager;

    States(GameStateManager gsm){
        gameStateManager = gsm;
        camera = new OrthographicCamera();
    }

    @SuppressWarnings("unused")
    protected abstract void handleInput();
    public abstract void update(float dt);
    protected abstract void render(SpriteBatch spriteBatch);
    @SuppressWarnings("unused")
    public abstract void dispose();
}
