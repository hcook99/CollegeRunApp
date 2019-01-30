package com.cmscproject.collegerun.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Queue;

import java.util.Random;

/*
*   PowerupManager
* Class PowerupManager contains a queue of powerup objects and the methods used to edit this queue,
* which is used by GameState for the purposes of spawning powerups, displaying them in-game
* @author Ethan M. Hopkins
* @version CMSC 355-01 11/10/2018 Iteration 2
*/
public class PowerupManager {
    private final Queue<PowerUp> powerups;
    private final float personScroll;
    private int difficulty;
    private final int height;

    /**
     * Method PowerupManager is the constructor for class PowerupManager, which contains a queue
     * of PowerUps to be used by GameState to draw PowerUp textures
     * @param personScroll Value passed from GameState
     */
    public PowerupManager(int difficulty, float personScroll, int height)
    {
        powerups = new Queue<PowerUp>();
        this.difficulty = difficulty;
        this.height = height;
        this.personScroll = personScroll;

    }

    /**
     * Method getPowerups is the accessor method for the powerups queue
     * @return powerups
     */
    public Queue<PowerUp> getPowerups()
    {
        return powerups;
    }

    /**
     * Method update is called to update the positioning of powerup sprites and to add more to the
     * queue if the conditions outlined in determinePowerUpPlacement are satisfied
     */
    public void update(boolean isNotReached){
        moveEveryPowerUp();
        if(isNotReached) {
            if (determinePowerUpPlacement()) {
                addRandomCoffee();
            }
        }
        checkIfPowerUpIsGone();
    }

    public void changeDifficulty(int difficulty){
        this.difficulty = difficulty;
    }

    /*
    * Method determinePowerUpPlacement returns a value from 1 to 3 to determine which powerup should
    * have it's randomization logic evaluated for spawning, 1 is SmallCoffee, 2 is MediumCoffee,
    * 3 is LargeCoffee
    * @return An int value 1 to 3
    */

    private void addRandomCoffee(int tempHeight){
        int coffeeNum = getCoffeeNum();
        if (coffeeNum == 3) {
            powerups.addLast(new SmallCoffee(Gdx.graphics.getWidth() + 700, tempHeight));
        } else if (coffeeNum == 2) {
            powerups.addLast(new MediumCoffee(Gdx.graphics.getWidth() + 700, tempHeight));
        } else if (coffeeNum == 1) {
            powerups.addLast(new LargeCoffee(Gdx.graphics.getWidth() + 700, tempHeight));
        }
    }
    private void addRandomCoffee(){
        int coffeeNum = getCoffeeNum();
        if (coffeeNum == 3) {
            powerups.addLast(new SmallCoffee(Gdx.graphics.getWidth() + 200, height));
        } else if (coffeeNum == 2) {
            powerups.addLast(new MediumCoffee(Gdx.graphics.getWidth() + 200, height));
        } else if (coffeeNum == 1) {
            powerups.addLast(new LargeCoffee(Gdx.graphics.getWidth() + 200, height));
        }
    }

    public void addCoffee(int tempHeight){
        addRandomCoffee(tempHeight);
    }

    private boolean determinePowerUpPlacement(){
        int rand = new Random().nextInt(difficulty*100);
        int minDis = Gdx.graphics.getWidth()/2;
        if(rand>=1){
            return false;
        }
        if(powerups.size > 0)
        {
            return !(Gdx.graphics.getWidth() + 100 - powerups.last().getPosition().x < minDis);
        }
        return true;
    }



    private int getCoffeeNum(){
        int rand = new Random().nextInt(10)+1;
        if(difficulty == 1){
            if(rand<=5){
                return 1;
            }
            else if(rand<=8){
                return 2;
            }
            else {
                return 3;
            }
        }
        else if(difficulty == 2){
            if(rand<=3){
                return 1;
            }
            else if(rand<=8){
                return 2;
            }
            else {
                return 3;
            }
        }
        else if(difficulty == 3){
            if(rand<=2){
                return 1;
            }
            else if(rand<=8){
                return 2;
            }
            else {
                return 3;
            }
        }
        else{
            if(rand<=2){
                return 1;
            }
            else if(rand<=5){
                return 2;
            }
            else {
                return 3;
            }
        }
    }

    public void removePowerUp(int index){
        powerups.removeIndex(index);
    }

    /*
    * Method moveEveryPowerUp scrolls powerup sprites to the left
    */
    private void moveEveryPowerUp(){
        if(powerups.size > 0) {
            for (int i = 0;i<powerups.size;i++) {
                powerups.get(i).reposition(personScroll);
            }
        }
    }

    /*
    * Method checkIfPowerUpIsGone removes powerups from the queue when they are offscreen
    */
    private void checkIfPowerUpIsGone(){
        if(powerups.size > 0) {
            if (powerups.first().getPosition().x < -200) {
                powerups.removeFirst();
            }
        }
    }

    /**
     * TEST HELPERS
     * */

    //Suppresed Lint Because unused variable is for making test
    public void addCoffee(int tempHeight, @SuppressWarnings("unused") boolean test){
        addRandomCoffee(tempHeight, true);
    }

    //Suppresed Lint Because unused variable is for making test
    @SuppressWarnings("SameParameterValue")
    private void addRandomCoffee(int tempHeight, @SuppressWarnings("unused") boolean test){
        int coffeeNum = getCoffeeNum();
        if (coffeeNum == 3) {
            powerups.addLast(new SmallCoffee(1000 + 700, tempHeight,true));
        } else if (coffeeNum == 2) {
            powerups.addLast(new MediumCoffee(1000 + 700, tempHeight,true));
        } else if (coffeeNum == 1) {
            powerups.addLast(new LargeCoffee(1000 + 700, tempHeight, true));
        }
    }
}
