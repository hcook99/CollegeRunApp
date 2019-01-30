package com.cmscproject.collegerun.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * 	Platform
 * Class platform is just used to create platforms
 * @author Harrison Cook
 *
 */
public class Platform {

    private final Vector2 position;
    private Texture platformT;


    public Platform(float x, float y){
        position = new Vector2(x,y);
        platformT = new Texture("platformfloat.png");

    }

    //Suppresed Lint Because unused variable is for making test
    public Platform(float x, float y, @SuppressWarnings("unused") boolean test){
        position = new Vector2(x,y);
    }

    public Texture getPlatformT(){
        return platformT;
    }

    public Vector2 getPosition(){
        return position;
    }

    public void moveForward(float dist){
        position.add(-dist, 0);
    }


}
