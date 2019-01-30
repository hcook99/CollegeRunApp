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
 * @author Tom Nguyen
 * Class that displays credit screen
 * */
public class CreditsScreen extends States{
    private final Texture background;
    private final Stage stage;
    private final Sprite backGroundSprite;
    private MusicManager musicManager;
    private Texture backButton;

    public CreditsScreen(final GameStateManager gameStateManager, MusicManager musicManager) {
        super(gameStateManager);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background = new Texture("Menu_NoTitle.png");
        backGroundSprite = new Sprite(background,0,0,background.getWidth(),background.getHeight());
        this.musicManager = musicManager;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        Label credit = new Label("CREDITS", skin);
        backButton = new Texture("Back_button.png");
        credit.setFontScale(6.0f);
        credit.getStyle().fontColor=Color.WHITE;
        credit.setPosition(Gdx.graphics.getWidth()/2-credit.getPrefWidth()/2, Gdx.graphics.getHeight()-credit.getPrefHeight());
        Label programmers = new Label("Development Team:\nHarrison Cook\nEthan Hopkins\nAidan Myers\nTom Nguyen", skin);
        programmers.setFontScale(5.0f);
        programmers.setPosition(Gdx.graphics.getWidth()/4-programmers.getPrefWidth()/2,Gdx.graphics.getHeight()/2);
        Label artist = new Label("Artist Team:\nSamantha Newman\nErin Joo\nPatrick Pankratz\nMonica Rao\nJacie Dannhardt", skin);
        artist.setFontScale(5.0f);
        artist.setPosition(Gdx.graphics.getWidth()/2+Gdx.graphics.getWidth()/8,Gdx.graphics.getHeight()/2);
        stage.addActor(credit);
        stage.addActor(artist);
        stage.addActor(programmers);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            dispose();
            gameStateManager.setState(new SettingsMenu(gameStateManager,musicManager));
        }
        if(Gdx.input.justTouched()){
            if(Gdx.input.getX()<=Gdx.graphics.getWidth()/10&&Gdx.input.getY()<=Gdx.graphics.getHeight()/10){
                gameStateManager.setState(new SettingsMenu(gameStateManager,musicManager));
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
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
