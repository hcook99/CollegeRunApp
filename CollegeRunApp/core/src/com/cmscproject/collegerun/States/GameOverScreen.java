package com.cmscproject.collegerun.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cmscproject.collegerun.Sprites.MusicManager;

/**
 * @author Harrison Cook
 * GameOverScreen class is used to display that the player lost and shows next option to retry or close
 * */
public class GameOverScreen extends States {

    private final Texture background;
    private final Stage stage;
    private final Texture ground;
    private MusicManager musicManager;

    public GameOverScreen(final GameStateManager gameStateManager, String type, final int levelNum, float distF, final MusicManager musicManager){
        super(gameStateManager);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background = new Texture("background.png");
        ground = new Texture("groundT.png");
        this.musicManager = musicManager;
        stage = new Stage();
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        HighScoreManager highScoreManager = new HighScoreManager(distF);
        if(type.equals("YOU WIN!")){
            setLevelToOpen(levelNum);
        }
        if(highScoreManager.isHigher()&&levelNum == 0){
            type = "NEW HIGHSCORE";
            int dist = (int)distF;
            Label highScore = new Label(String.valueOf(dist), skin);
            highScore.setFontScale(7.0f);
            highScore.getStyle().fontColor = Color.BLACK;
            highScore.setPosition(Gdx.graphics.getWidth()/2-highScore.getPrefWidth()/2,Gdx.graphics.getHeight()-2*highScore.getPrefHeight());
            stage.addActor(highScore);
        }
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        Label gameOver = new Label(type, skin);
        gameOver.setFontScale(5.0f);
        gameOver.getStyle().fontColor = Color.BLACK;
        gameOver.setPosition(Gdx.graphics.getWidth()/2- gameOver.getPrefWidth()/2,Gdx.graphics.getHeight()-gameOver.getPrefHeight());
        ImageTextButton play = new ImageTextButton("", skin);
        ImageButton home = new ImageButton(skin);
        play.setWidth(Gdx.graphics.getHeight()/6);
        play.setHeight(Gdx.graphics.getHeight()/6);
        play.setPosition(Gdx.graphics.getWidth()/2-Gdx.graphics.getWidth()/4-play.getWidth(),Gdx.graphics.getHeight()/4);
        home.setWidth(Gdx.graphics.getHeight()/6);
        home.setHeight(Gdx.graphics.getHeight()/6);
        home.setPosition(Gdx.graphics.getWidth()/2+Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/4);
        stage.addActor(gameOver);
        play.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("retryIcon.png")));
        stage.addActor(play);
        home.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("homeIcon.png")));
        stage.addActor(home);
        play.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                dispose();
                gameStateManager.setState(new GameState(gameStateManager,levelNum,musicManager));
                return false;
            }
        });
        home.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                dispose();
                gameStateManager.setState(new GamePickScreenState(gameStateManager,musicManager));
                return false;
            }
        });
        skin.getFont("font-big").getData().setScale(2,2);
    }


    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            dispose();
            gameStateManager.setState(new GamePickScreenState(gameStateManager,musicManager));
        }
    }

    private void setLevelToOpen(int level){
        Preferences preferences = Gdx.app.getPreferences("collegeRun");
        level++;
        String keyValue = "level"+level;
        preferences.putBoolean(keyValue, true);
        preferences.flush();
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    protected void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        stage.getBatch().draw(ground,0,0,Gdx.graphics.getWidth(),150);
        stage.getBatch().end();
        stage.draw();
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        ground.dispose();
        stage.dispose();
    }
}
