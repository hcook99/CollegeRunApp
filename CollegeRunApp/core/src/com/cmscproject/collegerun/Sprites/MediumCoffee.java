package com.cmscproject.collegerun.Sprites;

import com.badlogic.gdx.graphics.Texture;

/**
 * 	mediumCoffee
 * Class mediumCoffee contains the constructor for mediumCoffee and a method that returns the value
 * of meter gained when the player picks up this powerup.
 * @author Ethan M. Hopkins
 * @version CMSC 355-01 10/5/2018
 *
 */

public class MediumCoffee extends PowerUp
{
    // Instantiate a Texture object coffeeMedium for the texture coffeeMedium.png

    /**
     * Method MediumCoffee is the constructor for class MediumCoffee, which calls the constructor
     * of its parent class, PowerUp, passing the value 5 for int spawn and the parameter x
     * @param x The x coordinate for the position of MediumCoffee
     */
    public MediumCoffee(float x, float y)
    {
        super(x,y,new Texture("coffeeMedium.png"));
    }


    //Suppresed Lint Because unused variable is for making test
    public MediumCoffee(float x, float y, @SuppressWarnings("unused") boolean test){
        super(x,y);
    }

    /**
     * Method getTexture is the accessor for the Texture coffeeMedium
     * @return Texture object coffeeMedium
     */
    @SuppressWarnings("EmptyMethod")
    public Texture getTexture()
    {
        return super.getTexture();
    }

    /**
     * Method getRestoreValue returns a double value between 0 and 1, used to determine how much
     * of the player's meter is restored by the powerup when an empty meter is 0 and a full meter
     * is 1
     * @return A restore value of 0.30 (30%)
     */
    public float getRestoreValue() {
        return 0.30f;
    }

}
