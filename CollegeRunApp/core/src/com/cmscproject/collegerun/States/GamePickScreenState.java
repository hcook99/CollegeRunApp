package com.cmscproject.collegerun.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cmscproject.collegerun.Sprites.MusicManager;


/**
 * @author Harrison Cook
 * Main Menu Class Displays the main menu to navigate
 * */
public class GamePickScreenState extends States{
    private final Texture background;
    private final Sprite backGroundSprite;
    private final Stage stage;
    private final Texture howToPlay;
    private MusicManager musicManager;
    private Texture backButton;

    public GamePickScreenState(final GameStateManager gameStateManager, final MusicManager musicManager){
        super(gameStateManager);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background = new Texture("Menu_Title.png");
        this.musicManager = musicManager;
        backGroundSprite = new Sprite(background,0,0,background.getWidth(),background.getHeight());
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        howToPlay = new Texture("help.png");
        backButton = new Texture("Back_button.png");
        ImageButton singlePlayerButton = new ImageButton(skin);
        singlePlayerButton.getStyle().up=new TextureRegionDrawable(new TextureRegion(new Texture("GetDegree_button.png")));
        singlePlayerButton.setWidth(Gdx.graphics.getWidth()/5);
        singlePlayerButton.setHeight(Gdx.graphics.getWidth()/5);
        singlePlayerButton.setPosition((Gdx.graphics.getWidth()/2)-(singlePlayerButton.getWidth()),Gdx.graphics.getHeight()/4-singlePlayerButton.getHeight()/2);
        ImageTextButton infiniteRunButton = new ImageTextButton("",skin);
        infiniteRunButton.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("InfiniteRun_button.png")));
        infiniteRunButton.setWidth(Gdx.graphics.getWidth()/5);
        infiniteRunButton.setHeight(Gdx.graphics.getWidth()/5);
        infiniteRunButton.setPosition((Gdx.graphics.getWidth()/2), Gdx.graphics.getHeight()/4-infiniteRunButton.getHeight()/2);
        stage.addActor(singlePlayerButton);
        stage.addActor(infiniteRunButton);
        singlePlayerButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                dispose();
                gameStateManager.setState(new SinglePlayerMenu(gameStateManager,musicManager));
                return false;
            }
        });
        infiniteRunButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                dispose();
                gameStateManager.setState(new GameState(gameStateManager, 0,musicManager));
                return false;
            }
        });
    }


    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            gameStateManager.setState(new MainMenuState(gameStateManager,musicManager));
        }
        if(Gdx.input.justTouched()){
            if(Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/15<Gdx.input.getX()&&Gdx.input.getY()>Gdx.graphics.getHeight()-Gdx.graphics.getWidth()/15){
                dispose();
                gameStateManager.setState(new HowToPlay(gameStateManager,musicManager));
            }
            if(Gdx.input.getX()<=Gdx.graphics.getWidth()/10&&Gdx.input.getY()<=Gdx.graphics.getHeight()/10){
                gameStateManager.setState(new MainMenuState(gameStateManager,musicManager));
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
        stage.getBatch().draw(howToPlay,Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/15,0,Gdx.graphics.getWidth()/15,Gdx.graphics.getWidth()/15);
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
