package com.cmscproject.collegerun.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * 	Distance Manager
 * Class distancemanager is used to keep track of distance and size of drawing
 * @author Harrison Cook
 *
 */
public class DistanceManager {

    private final float maxDist;
    private float dist;
    private Texture emptyProgress;
    private Texture progressBar;

    public DistanceManager(float maxDist){
        this.maxDist = maxDist;
        dist = 1;
        emptyProgress = new Texture("EmptyProgrssBar.png");
        progressBar = new Texture("progressbarbar.png");
    }

    public DistanceManager(float maxDist, @SuppressWarnings("unused") boolean test) {
        this.maxDist = maxDist;
        dist = 1;
    }

    public void update(){
        dist++;
    }


    public float getDist(){
        return dist;
    }

    public boolean maxDistNotReached(){
        return maxDist>dist;
    }

    public boolean gameOver(){
        return maxDist<=dist;
    }

    public Texture getEmptyProgress(){
        return emptyProgress;
    }
    public Texture getProgressBar(){
        return progressBar;
    }

    public float drawSize(float buffer){
        return dist/(maxDist+204)*(Gdx.graphics.getWidth()/2-Gdx.graphics.getWidth()/15-2*buffer);
    }


    public float drawSize(float buffer,int artificialScreenWidth){
        return dist/(maxDist+204)*(artificialScreenWidth-artificialScreenWidth/15-2*buffer);
    }
}
