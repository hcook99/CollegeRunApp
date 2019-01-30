package com.cmscproject.collegerun.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
/**
 * 	AnimateRunner
 * Class animateRunner contains the constructor cycling an image to appear as a person is running
 * @author Harrison Cook
 *
 */

class AnimateRunner {

    private final Animation<TextureRegion> animation;
    private final Texture runnerPane;
    private float timeCount;

    /**
     * Constructor initializes image for cycling
     * @param cycleTime Represents speed to cycle through image
     */
    public AnimateRunner(float cycleTime){
        timeCount = 0;
        runnerPane = new Texture("runner.png");
        int FRAME_COL=7;
        int FRAME_ROW=1;
        TextureRegion[][] tmp = TextureRegion.split(runnerPane, (runnerPane.getWidth())/FRAME_COL,runnerPane.getHeight()/FRAME_ROW);
        TextureRegion[] runnerFrames = new TextureRegion[FRAME_COL*FRAME_ROW];
        int indexToAdd=0;
        for(int i =0;i<FRAME_ROW;i++){
            for(int j=0;j<FRAME_COL;j++){
                runnerFrames[indexToAdd++] = tmp[i][j];
            }
        }
        animation = new Animation<TextureRegion>(cycleTime, runnerFrames);
    }

    /**
     * Method getCurrentFrame returns the current frame
     * @param timeAdd Represents current time
     * @return TextureRegion the image
     */
    public TextureRegion getCurrentFrame(float timeAdd){
        timeCount += timeAdd;
        return animation.getKeyFrame(timeCount,true);
    }

    /**
     * Method dispose disposes of image references
     */
    public void dispose(){
        runnerPane.dispose();
    }
}
