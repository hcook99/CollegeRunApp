package com.cmscproject.collegerun.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cmscproject.collegerun.Sprites.MusicManager;

/**
 * @author Harrison Cook
 * SinglePlayerMenu class shows the 8 semesters
 * */
public class SinglePlayerMenu extends States{

    private final Texture background;
    private final Sprite backGroundSprite;
    private final Stage stage;
    private final ImageTextButton level2;
    private final ImageTextButton level3;
    private final ImageTextButton level4;
    private final ImageTextButton level5;
    private final ImageTextButton level6;
    private final ImageTextButton level7;
    private final ImageTextButton level8;
    private MusicManager musicManager;
    private Texture backButton;


    public SinglePlayerMenu(final GameStateManager gameStateManager, final MusicManager musicManager){
        super(gameStateManager);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background = new Texture("Menu_NoTitle.png");
        backGroundSprite = new Sprite(background, 0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.musicManager = musicManager;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        skin.getFont("font-big").getData().setScale(2,2);
        backButton = new Texture("Back_button.png");

        ImageTextButton level1 = new ImageTextButton("1", skin);
        level2 = new ImageTextButton("2",skin);
        level3 = new ImageTextButton("3", skin);
        level4 = new ImageTextButton("4",skin);
        level5 = new ImageTextButton("5", skin);
        level6 = new ImageTextButton("6",skin);
        level7 = new ImageTextButton("7", skin);
        level8 = new ImageTextButton("8",skin);
        level1.getStyle().fontColor = Color.BLACK;
        level1.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("buttonMain.png")));
        float heightAndWidthOfButton = Gdx.graphics.getHeight()/3.5f;
        float yAbove = (Gdx.graphics.getHeight()/2)+heightAndWidthOfButton/12;
        float yBelow = (Gdx.graphics.getHeight()/2)-heightAndWidthOfButton-heightAndWidthOfButton/12;
        level2.getStyle().disabled = new TextureRegionDrawable(new TextureRegion(new Texture("buttonLock.png")));
        checkIfAvailable();

        level1.setWidth(heightAndWidthOfButton);
        level1.setHeight(heightAndWidthOfButton);
        level1.setPosition((Gdx.graphics.getWidth()/2)-(level1.getWidth()*2)-(heightAndWidthOfButton/4), yAbove);
        level2.setWidth(heightAndWidthOfButton);
        level2.setHeight(heightAndWidthOfButton);
        level2.setPosition((Gdx.graphics.getWidth()/2)-(level2.getWidth())-heightAndWidthOfButton/12, yAbove);
        level3.setWidth(heightAndWidthOfButton);
        level3.setHeight(heightAndWidthOfButton);
        level3.setPosition((Gdx.graphics.getWidth()/2)+heightAndWidthOfButton/12, yAbove);
        level4.setWidth(heightAndWidthOfButton);
        level4.setHeight(heightAndWidthOfButton);
        level4.setPosition((Gdx.graphics.getWidth()/2)+(level4.getWidth())+heightAndWidthOfButton/4, yAbove);
        level5.setWidth(heightAndWidthOfButton);
        level5.setHeight(heightAndWidthOfButton);
        level5.setPosition((Gdx.graphics.getWidth()/2)-(level5.getWidth()*2)-heightAndWidthOfButton/4, yBelow);
        level6.setWidth(heightAndWidthOfButton);
        level6.setHeight(heightAndWidthOfButton);
        level6.setPosition((Gdx.graphics.getWidth()/2)-(level6.getWidth())-heightAndWidthOfButton/12, yBelow);
        level7.setWidth(heightAndWidthOfButton);
        level7.setHeight(heightAndWidthOfButton);
        level7.setPosition((Gdx.graphics.getWidth()/2)+heightAndWidthOfButton/12, yBelow);
        level8.setWidth(heightAndWidthOfButton);
        level8.setHeight(heightAndWidthOfButton);
        level8.setPosition((Gdx.graphics.getWidth()/2)+(level8.getWidth())+heightAndWidthOfButton/4, yBelow);
        stage.addActor(level1);
        stage.addActor(level2);
        stage.addActor(level3);
        stage.addActor(level4);
        stage.addActor(level5);
        stage.addActor(level6);
        stage.addActor(level7);
        stage.addActor(level8);
        level1.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                dispose();
                gameStateManager.setState(new GameState(gameStateManager,1,musicManager));
                return false;
            }
        });
        level2.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                dispose();
                gameStateManager.setState(new GameState(gameStateManager,2,musicManager));
                return false;
            }
        });
        level3.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                dispose();
                gameStateManager.setState(new GameState(gameStateManager,3,musicManager));
                return false;
            }
        });
        level4.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                dispose();
                gameStateManager.setState(new GameState(gameStateManager,4,musicManager));
                return false;
            }
        });
        level5.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                dispose();
                gameStateManager.setState(new GameState(gameStateManager,5,musicManager));
                return false;
            }
        });
        level6.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                dispose();
                gameStateManager.setState(new GameState(gameStateManager,6,musicManager));
                return false;
            }
        });
        level7.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                dispose();
                gameStateManager.setState(new GameState(gameStateManager,7,musicManager));
                return false;
            }
        });
        level8.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                dispose();
                gameStateManager.setState(new GameState(gameStateManager,8,musicManager));
                return false;
            }
        });
    }

    private void checkIfAvailable(){
        Preferences preferences = Gdx.app.getPreferences("collegeRun");

        if(!preferences.getBoolean("level2")) {
            level2.setDisabled(true);
            level2.setTouchable(Touchable.disabled);
            level2.setText("");
        }
        if(!preferences.getBoolean("level3")) {
            level3.setDisabled(true);
            level3.setTouchable(Touchable.disabled);
            level3.setText("");
        }
        if(!preferences.getBoolean("level4")) {
            level4.setDisabled(true);
            level4.setTouchable(Touchable.disabled);
            level4.setText("");
        }
        if(!preferences.getBoolean("level5")) {
            level5.setDisabled(true);
            level5.setTouchable(Touchable.disabled);
            level5.setText("");
        }
        if(!preferences.getBoolean("level6")) {
            level6.setDisabled(true);
            level6.setTouchable(Touchable.disabled);
            level6.setText("");
        }
        if(!preferences.getBoolean("level7")) {
            level7.setDisabled(true);
            level7.setTouchable(Touchable.disabled);
            level7.setText("");
        }
        if(!preferences.getBoolean("level8")) {
            level8.setDisabled(true);
            level8.setTouchable(Touchable.disabled);
            level8.setText("");
        }


    }
    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
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
