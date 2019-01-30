package com.cmscproject.collegerun.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Queue;
import java.util.Random;

/**
 * 	ObstacleManager
 * Class obstacleManager keeps track of obstacles
 * @author Harrison Cook
 *
 */

public class ObstacleManager {
    private final Queue<Obstacle> obstacles;
    private int difficulty;
    private final float personScroll;
    private final int y;
    private final float width;
    private final float height;


    /**
     * Method ObstacleManager constructor initializes class
     * @param difficulty controls the chance a obstacle will spawn
     * @param personScroll speed at which a player runs
     * */
    public ObstacleManager(int difficulty, float personScroll, int y, float width, float height){
        this.difficulty = difficulty;
        this.personScroll = personScroll;
        this.y = y;
        this.width = width;
        this.height = height;
        obstacles = new Queue<Obstacle>();
    }

    /**
     * Method getObstacles returns obstacles in queue
     * @return Queue<Obstacle> queue of obstacles
     * */
    public Queue<Obstacle> getObstacles() {
        return obstacles;
    }

    /**
     * Method to update
     * */
    public void update(boolean distNotReached){
        moveEveryObstacle();
        if (distNotReached) {
            if (determineIfObstaclePlacement()) {
                obstacles.addLast(new Obstacle(Gdx.graphics.getWidth() + 200, y, width, height));
            }
        }
        checkIfObstacleIsGone();
    }

    public void changeDifficulty(int difficulty){
        this.difficulty =difficulty;
    }

    /**
     * method determineIfObstaclePlacement is used to determine if an obstacle can be placed
     * @return boolean if true it places object if it is false it doesn't
     * */
    private boolean determineIfObstaclePlacement(){
        int rand = new Random().nextInt(70);
        int minDis = (Gdx.graphics.getWidth()/difficulty)+Gdx.graphics.getWidth()/(4*difficulty);
        if(rand>=difficulty){
            return false;
        }
        if(obstacles.size>0) {
            return !(Gdx.graphics.getWidth() + 100 - obstacles.last().getPositionOb().x < minDis);
        }
        return true;

    }


    /**
     * method moveEveryObstacle is used to move all the obstacles forward
     * */
    private void moveEveryObstacle(){
        if(obstacles.size>0) {
            for (int i = 0;i<obstacles.size;i++) {
                obstacles.get(i).moveForward(personScroll);
            }
        }
    }

    /**
     * method checkIfObstacleIsGone is used to remove a obstacle if it exits the screen
     * */
    private void checkIfObstacleIsGone(){
        if(obstacles.size>0) {
            if (obstacles.first().getPositionOb().x < -500) {
                obstacles.removeFirst();
            }
        }
    }
}
