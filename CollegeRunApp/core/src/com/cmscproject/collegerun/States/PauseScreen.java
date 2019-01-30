package com.cmscproject.collegerun.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cmscproject.collegerun.Sprites.MusicManager;
import com.cmscproject.collegerun.Sprites.Person;

/**
 * @author Harrison Cook
 * PauseScreen class is used when the player pauses the game
 * */
public class PauseScreen extends States {

    private final Texture background;
    private final Stage stage;
    private final Texture ground;
    private final Person person;
    private final Texture pause;
    private MusicManager musicManager;

    public PauseScreen(final GameStateManager gameStateManager, Person person, final MusicManager musicManager){
        super(gameStateManager);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.person = person;
        background = new Texture("background.png");
        ground = new Texture("groundT.png");
        stage = new Stage();
        this.musicManager = musicManager;
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        pause = new Texture("PauseMenu.png");
        ImageTextButton play = new ImageTextButton("", skin);
        ImageButton home = new ImageButton(skin);
        play.setWidth(Gdx.graphics.getWidth()/3);
        play.setHeight(Gdx.graphics.getHeight()/4);
        play.setPosition(Gdx.graphics.getWidth()/2- play.getWidth()/2,Gdx.graphics.getHeight()/2- play.getHeight()/6);
        home.setWidth(Gdx.graphics.getWidth()/3);
        home.setHeight(Gdx.graphics.getHeight()/4);
        home.setPosition(Gdx.graphics.getWidth()/2- home.getWidth()/2,Gdx.graphics.getHeight()/2- home.getHeight());
        play.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("Resume_button.png")));
        stage.addActor(play);
        home.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("Return_button.png")));
        stage.addActor(home);
        play.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(gameStateManager.states.size()>1) {
                    dispose();
                    gameStateManager.pop();
                }
                return false;
            }
        });
        home.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                dispose();
                musicManager.changeSong();
                gameStateManager.setState(new GamePickScreenState(gameStateManager,musicManager));
                return false;
            }
        });
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            dispose();
            gameStateManager.pop();
        }
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
        stage.getBatch().draw(person.getTexture(0), person.getPosition().x,person.getPosition().y,person.getWidth(),person.getHeight());
        stage.getBatch().draw(pause, Gdx.graphics.getWidth()/2-(Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/8)/2, Gdx.graphics.getHeight()/2-(Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/8)/2, (Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/8), (Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/8));
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
