package com.cmscproject.collegerun.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


/**
 * 	Person
 * Class person handles the person on screen
 * @author Harrison Cook
 *
 */

public class Person {
    private final float GRAVITY;

    private AnimateRunner animateRunner;
    private final Vector2 position;
    private final Vector2 velocity;
    private final float jumpVelocity;
    private Rectangle bounds;
    private int yMin;
    private TextureRegion jump;
    private final int standardY;
    private final float width;
    private final float height;


    /**
     * Constructor initializes person
     * @param x int in the x coordinate
     * @param y int in the y coordinate
     * @param cycleTime time to cycle to next image
     */
    public Person(int x, int y, float width, float height, float cycleTime){
        position = new Vector2(x,y);
        GRAVITY = -Gdx.graphics.getHeight()/9.6f;
        jumpVelocity = Gdx.graphics.getHeight()*2.3f;
        standardY = y;
        yMin=standardY;
        this.width = width;
        this.height = height;
        velocity= new Vector2(0,0);
        Texture jumpText = new Texture("jump.png");
        jump = new TextureRegion(jumpText);
        animateRunner = new AnimateRunner(cycleTime);
    }
    /**
     * Constructor initializes person for test
     * @param x int in the x coordinate
     * @param y int in the y coordinate
     */
    public Person(int x, int y){
        position = new Vector2(x,y);
        yMin = y;
        GRAVITY = -150;
        jumpVelocity = 3200;
        velocity= new Vector2(0,0);
        width = 100;
        height =100;
        standardY = 90;
        bounds=new Rectangle(position.x,position.y,100,100);
    }

    public int getStandardY(){
        return standardY;
    }

    public void setyMin(int yMin){
        this.yMin=yMin;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight() {
        return height;
    }

    /**
     * Method jump adds to velocity in the y so the user goes up
     */
    public void jump(){
        if(position.y==yMin) {
            velocity.y = jumpVelocity;
        }
    }

    public int getyMin(){
        return yMin;
    }
    /**
     * Method update is used to add gravity to the person
     * @param dt is a measure of time
     */
    public void update(float dt){
        velocity.add(0,GRAVITY);
        velocity.scl(dt);
        if(position.y<=yMin&& !Gdx.input.justTouched()) {
            velocity.y=0;
        }
        if(position.y<yMin){
            position.y=yMin;
        }

        position.add(0,velocity.y);


        velocity.scl(1/dt);

        bounds = new Rectangle(position.x,position.y,width-width/4,height-height/5);
    }


    /**
     * Method used for testing the jump method
     */
    public void addToPositionFromJumpTest(){
        position.add(0,velocity.y);
    }

    public TextureRegion getTexture(float timeAdd) {
        TextureRegion person;
        if(position.y>yMin){
            person = jump;
        }
        else {
            person = animateRunner.getCurrentFrame(timeAdd);
        }
        return person;

    }

    /**
     *  Method to get the current position of the person
     * @return Vector coordinate position
     * */
    public Vector2 getPosition() {
        return position;
    }
    /**
     *  Method to dispose of image
     * */
    public void dispose(){
        animateRunner.dispose();
    }
    /**
     *  Method will be used in future
     * */
    public Rectangle getBounds(){
        return bounds;
    }
}
