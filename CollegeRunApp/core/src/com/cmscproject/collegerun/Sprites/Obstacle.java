package com.cmscproject.collegerun.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * 	AnimateRunner
 * Class Obstacle is the class that handles all the obstacles to place on screen
 * @author Harrison Cook
 */


public class Obstacle {
    private Texture obstacle;
    private Vector2 postionOb;
    private final Rectangle bound;
    private float width;
    private float height;

    /**
     *  Constructor for obstacle creates the image for obstacles
     * @param x is an int corresponding to the x coordinate
     * */
    public Obstacle(int x, int y, float width, float height){
        int rand = (int) ((Math.random()*3)+1);
        if(rand==1){
            obstacle = new Texture("obstaclepot1.png");
        }
        else if(rand==2){
            obstacle = new Texture("obstaclepot2.png");
        }
        else{
            obstacle = new Texture("obstacletrash.png");
        }
        this.width = width;
        this.height = height;
        postionOb = new Vector2(x,y);
        bound = new Rectangle(postionOb.x, postionOb.y, width-width/5, height-height/4);
    }

    /**
     *  Constructor for obstacle creates the image for obstacles
     * @param x is an int corresponding to the x coordinate
     * */
    public Obstacle(int x){
        postionOb = new Vector2(x,30);
        bound = new Rectangle(postionOb.x,postionOb.y,100,100);
    }


    /**
     *  Method to get the current position of the obstacle
     * @return Vector coordinate position
     * */
    public Vector2 getPositionOb(){
        return postionOb;
    }

    /**
     *  Method to get the current image of the obstacle
     * @return Texture image of obstacle
     * */
    public Texture getOb(){
        return obstacle;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    /**
     *  Method to move the trashcan
     * @param x int coordinate for x plane
     * @param y int coordinate for y plane
     * */
    public void reposition(int x, int y){
        postionOb = new Vector2(x,y);
    }

    public void moveForward(float dist){
        postionOb.add(-dist, 0);
        bound.setX(postionOb.x);
    }


    public Rectangle getBound() {
        return bound;
    }


}
