package com.cmscproject.collegerun.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * 	powerup
 * Abstract class powerup contains the fields necessary for all powerups to be implemented, the
 * purpose of adding this layer of abstraction is for the possible implementation of powerups with
 * different effects, so that they can be subclassed from powerup and given the unique fields they
 * require while being uniformly derivative of powerup.
 * @author Ethan M. Hopkins
 * @version CMSC 355-01 10/5/2018
 *
 */

public abstract class PowerUp {
    private Texture texture; // Variable texture holds the texture of the powerup
    private final Vector2 position; // Variable position is the vector value for the position of the powerup
    private Rectangle bound;
    /**
     * Method PowerUp is the constructor for class PowerUp, called by subclasses of class PowerUp.
     * PowerUp updates the values of spawnChance, texture, and position while instantiating and
     * assigning variable powerupWidth ad creating an instantiation of rectangle object bound.
     * @param x Value will be assigned to the x value in position
     */
    public PowerUp(float x,float y,Texture texture)
    {
        this.position = new Vector2(x,y);
        this.texture = texture;
        bound = new Rectangle(position.x, position.y, texture.getWidth()-texture.getWidth()/5,
                texture.getHeight()-texture.getHeight()/5);
    }

    PowerUp(float x, float y)
    {
        this.position = new Vector2(x, y);
    }


    /**
     * Method getPosition is the accessor for the position variable
     * @return The vector for the position of the powerup
     */
    public Vector2 getPosition()
    {
        return position;
    }

    /**
     * Method reposition can be called to change the position of powerup along the x axis
     * @param x The x value in vector2 position
     */
    public void reposition(float x)
    {
        position.add(-x,0);
        bound.setX(position.x);
    }

    public Rectangle getBounds(){
        return bound;
    }

    public abstract float getRestoreValue();
    /**
     *  Method to get the current image of the obstacle
     * @return Texture image of obstacle
     * */
    public Texture getTexture(){
        return texture;
    }

}
