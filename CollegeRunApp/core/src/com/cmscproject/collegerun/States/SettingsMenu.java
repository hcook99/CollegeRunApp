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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cmscproject.collegerun.Sprites.MusicManager;


/**
 * @author Harrison Cook
 * SettingsScreen class gives the player the otion to mute
 * */
public class SettingsMenu extends States {

    private final Texture background;
    private final Stage stage;
    private final Preferences preferences = Gdx.app.getPreferences("collegeRun");
    private final ImageTextButton music;
    private MusicManager musicManager;
    private Texture backButton;

    public SettingsMenu(final GameStateManager gameStateManager, final MusicManager musicManager){
        super(gameStateManager);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.musicManager = musicManager;
        background = new Texture("Menu_Title.png");
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        backButton = new Texture("Back_button.png");
        music = new ImageTextButton("Music", skin);
        ImageButton credits = new ImageButton(skin);
        float height = Gdx.graphics.getWidth()/4;
        float width = Gdx.graphics.getWidth()/4;
        music.getStyle().disabled = new TextureRegionDrawable(new TextureRegion(new Texture("Sound_buttonMUTED.png")));
        music.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("Sound_button.png")));
        music.getStyle().down = new TextureRegionDrawable(new TextureRegion(new Texture("Sound_button.png")));


        checkSettingsInit();
        music.setHeight(height);
        music.setWidth(width);
        music.setPosition(Gdx.graphics.getWidth()/2-width/2,Gdx.graphics.getHeight()/2-height);
        music.clearChildren();
        music.add(music.getLabel());
        music.add(music.getImage());
        stage.addActor(music);
        // CREDIT BUTTON
        credits.getStyle().up=new TextureRegionDrawable(new TextureRegion(new Texture("Credits_button.png")));
        credits.getStyle().down = new TextureRegionDrawable(new TextureRegion(new Texture("Credits_button.png")));
        credits.setWidth(Gdx.graphics.getWidth()/9);
        credits.setHeight((Gdx.graphics.getWidth()/9));
        credits.setPosition(Gdx.graphics.getWidth()-credits.getWidth(),0);
        stage.addActor(credits);
        credits.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                dispose();
                gameStateManager.setState(new CreditsScreen(gameStateManager,musicManager));
                return false;
            }
        });

    }

    private void checkSettingsInit(){

        if (!preferences.getBoolean("music")){
            music.setDisabled(true);
        }
        else{
            music.setDisabled(false);
        }

    }

    @Override
    protected void handleInput() {
        music.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(music.isPressed()) {
                    boolean musicOn = preferences.getBoolean("music");
                    musicOn = !musicOn;
                    preferences.putBoolean("music", musicOn);
                    preferences.flush();
                }
                return false;
            }
        });

        if(Gdx.input.justTouched()){
            if(Gdx.input.getX()<=Gdx.graphics.getWidth()/10&&Gdx.input.getY()<=Gdx.graphics.getHeight()/10){
                gameStateManager.setState(new MainMenuState(gameStateManager,musicManager));
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            gameStateManager.setState(new MainMenuState(gameStateManager, musicManager));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        checkSettingsInit();
        musicManager.play();
        camera.update();
    }

    @Override
    protected void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
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
