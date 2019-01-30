package com.cmscproject.collegerun.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

class HighScoreManager {
    private boolean isHigher;

    public HighScoreManager(float dist){
        isHigher = false;
        Preferences preferences = Gdx.app.getPreferences("collegeRun");
        if(dist> preferences.getFloat("highScore")){
            preferences.putFloat("highScore", dist);
            preferences.flush();
            isHigher = true;
        }
    }

    public boolean isHigher() {
        return isHigher;
    }
}
