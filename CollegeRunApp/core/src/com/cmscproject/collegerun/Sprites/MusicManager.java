package com.cmscproject.collegerun.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;

public class MusicManager {

    private Music runnerSong;
    private Music menuSong;
    private Music currentPlay;
    private Preferences preferences;

    public MusicManager(){
        menuSong = Gdx.audio.newMusic(Gdx.files.internal("Menu_Theme_Sleep.wav"));
        runnerSong = Gdx.audio.newMusic(Gdx.files.internal("Running_Loop.wav"));
        preferences = Gdx.app.getPreferences("collegeRun");

        currentPlay = menuSong;
    }

    public void play(){
        if(preferences.getBoolean("music")){
            currentPlay.setLooping(true);
            if(!currentPlay.isPlaying()) {
                currentPlay.play();
            }
        }
        else{
            currentPlay.stop();
        }
    }
    public void pause(){
        if(preferences.getBoolean("music")){
            currentPlay.pause();
        }
    }
    public void stop(){
        if(preferences.getBoolean("music")){
            currentPlay.stop();
        }
    }

    public void changeSong(){
        if(currentPlay.equals(menuSong)){
            stop();
            currentPlay = runnerSong;
            play();
        }
        else{
            stop();
            currentPlay = menuSong;
            play();
        }
    }
}
