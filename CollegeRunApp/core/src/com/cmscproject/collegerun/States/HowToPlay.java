package com.cmscproject.collegerun.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.cmscproject.collegerun.Sprites.MusicManager;

/**
 * @author Aiden Myers
 * How To Play displays information on how to play the game
 * */
public class HowToPlay extends States {
    private final Texture background;
    private final Sprite backGroundSprite;
    private final Stage stage;
    private MusicManager musicManager;
    private Texture backButton;

    public HowToPlay(final GameStateManager gameStateManager, MusicManager musicManager){
        super(gameStateManager);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background = new Texture("Menu_NoTitle.png");
        this.musicManager = musicManager;
        backGroundSprite = new Sprite(background,0,0,background.getWidth(),background.getHeight());
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        backButton = new Texture("Back_button.png");

        Label rules= new Label(   "In SINGLE PLAYER, in order to get to the end of the semester safely, you have to: \n" +
                            "   1. Collect the Coffee: \n" +
                            "       Large Coffee completely fills your energy \n" +
                            "       Medium Coffee gives you 20 energy \n" +
                            "       Small Coffee gives you 10 energy \n" +
                            "   2. Tap to jump over obstacles such as: \n" +
                            "       Steps, broken bicycles, tests, trashcans, and stoplights \n" +
                            "In INFINITE RUN: \n" +
                            "   Run as long as you can", skin);
        rules.setFontScale(3.5f);
        rules.getStyle().fontColor = Color.WHITE;
        rules.setPosition(Gdx.graphics.getWidth()/16,Gdx.graphics.getHeight()/2);
        stage.addActor(rules);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            dispose();
            gameStateManager.setState(new GamePickScreenState(gameStateManager,musicManager));
        }
        if(Gdx.input.justTouched()){
            if(Gdx.input.getX()<=Gdx.graphics.getWidth()/10&&Gdx.input.getY()<=Gdx.graphics.getHeight()/10){
                gameStateManager.setState(new GamePickScreenState(gameStateManager,musicManager));
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        musicManager.play();
        camera.update();
    }

    @Override
    protected void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(backGroundSprite,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        stage.getBatch().draw(backButton,0,Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/10,Gdx.graphics.getWidth()/10,Gdx.graphics.getHeight()/10);
        stage.getBatch().end();
        stage.draw();
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        stage.dispose();
    }
}
