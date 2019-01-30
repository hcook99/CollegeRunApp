package com.cmscproject.collegerun.States;

import com.badlogic.gdx.Gdx;
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

public class MainMenuState extends States {

    private final Texture background;
    private final Stage stage;

    public MainMenuState(final GameStateManager gameStateManager,final MusicManager musicManager){
        super(gameStateManager);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background = new Texture("Menu_Title.png");
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        ImageButton start = new ImageButton(skin);
        ImageTextButton options = new ImageTextButton("",skin);
        start.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("Start_button.png")));
        options.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("Option_button.png")));
        start.setWidth(Gdx.graphics.getWidth()/4);
        start.setHeight(Gdx.graphics.getWidth()/4);
        start.setPosition(Gdx.graphics.getWidth()/2-start.getWidth(),0);

        options.setWidth(Gdx.graphics.getWidth()/4);
        options.setHeight(Gdx.graphics.getWidth()/4);
        options.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/17);

        stage.addActor(start);
        stage.addActor(options);
        start.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                dispose();
                gameStateManager.setState(new GamePickScreenState(gameStateManager,musicManager));
                return false;
            }
        });
        options.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                dispose();
                gameStateManager.setState(new SettingsMenu(gameStateManager,musicManager));
                return false;
            }
        });
    }

    @Override
    protected void handleInput() {

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
        stage.getBatch().draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        stage.getBatch().end();
        stage.draw();
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
    }
}
