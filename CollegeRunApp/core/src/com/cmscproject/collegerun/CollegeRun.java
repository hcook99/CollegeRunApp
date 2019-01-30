package com.cmscproject.collegerun;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cmscproject.collegerun.Sprites.MusicManager;
import com.cmscproject.collegerun.States.GameStateManager;
import com.cmscproject.collegerun.States.MainMenuState;


/**
 * 	CollegeRun
 * Class collegerun is the main class for the game
 * @author Harrison Cook
 */

public class CollegeRun extends ApplicationAdapter {
    private SpriteBatch batch;
    private GameStateManager gameStateManager;
    /**
     * Method to create the game
     */
	@Override
	public void create () {
		batch = new SpriteBatch();
		gameStateManager = new GameStateManager();
		MusicManager musicManager = new MusicManager();
		Preferences preferences = Gdx.app.getPreferences("collegeRun");
		if(!preferences.contains("level1")){
			preferences.putBoolean("level1",true);
			preferences.putBoolean("level2",false);
			preferences.putBoolean("level3",false);
			preferences.putBoolean("level4",false);
			preferences.putBoolean("level5",false);
			preferences.putBoolean("level6",false);
			preferences.putBoolean("level7",false);
			preferences.putBoolean("level8",false);
			preferences.flush();
		}
		if(!preferences.contains("music")){
			preferences.putBoolean("music", true);
			preferences.flush();
		}
		if(!preferences.contains("highScore")){
			preferences.putFloat("highScore", 0);
			preferences.flush();
		}
		musicManager.play();
        gameStateManager.add(new MainMenuState(gameStateManager,musicManager));
		Gdx.gl.glClearColor(1, 0, 0, 1);
	}

    /**
     * Method to render the game
     */
	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameStateManager.update(Gdx.graphics.getDeltaTime());
        gameStateManager.render(batch);
    }

    /**
     * Method to dispose of the images
     */
	@Override
	public void dispose(){
		batch.dispose();
	}
}
