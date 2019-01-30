package com.cmscproject.collegerun.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Queue;

import java.util.Random;

/**
 * PlatformManager
 * Class platform manager is in charge of controlling when to place platforms
 * @author Harrison Cook
 *
 */
public class PlatformManager {

    private final Queue<Platform> platformQueue;
    private final float height;
    private final float scroll;

    public PlatformManager(float height,float scroll){
        this.scroll = scroll;
        this.height = height;
        platformQueue = new Queue<Platform>();
    }

    public Queue<Platform> getPlatformQueue(){
        return platformQueue;
    }

    public void update(boolean isNotReached,Person person,PowerupManager powerupManager){
        placePerson(person);
        movePlatformForward();
        if(isNotReached){
            if (determinePlatformPlacement()){
                platformQueue.addLast(new Platform(Gdx.graphics.getWidth()+200, height));
                if(placeCoffeeOnPlatform()){
                    powerupManager.addCoffee((int)height);
                }
            }
        }
        checkIfOutOfPlay();
    }



    private void placePerson(Person person){
        if(platformQueue.size>0) {
            if (platformQueue.first().getPosition().x < person.getPosition().x+person.getWidth()/1.5f && platformQueue.first().getPosition().x +Gdx.graphics.getWidth()/3 > person.getPosition().x && platformQueue.first().getPosition().y < person.getPosition().y) {
                person.setyMin((int) platformQueue.first().getPosition().y+Gdx.graphics.getHeight()/15);
            }
            else{
                person.setyMin(person.getStandardY());
            }
        }
    }

    private boolean determinePlatformPlacement(){
        int rand = new Random().nextInt(300);
        if(platformQueue.size==0){
            return rand <= 1;
        }
        return false;
    }

    private boolean placeCoffeeOnPlatform(){
        int rand = new Random().nextInt(4);
        return rand <= 1;
    }


    private void movePlatformForward() {
        if (platformQueue.size > 0) {
            platformQueue.last().moveForward(scroll);
        }
    }

    private void checkIfOutOfPlay(){
        if(platformQueue.size>0){
            if(platformQueue.first().getPosition().x<-(platformQueue.first().getPlatformT().getWidth()*4)){

                platformQueue.removeFirst();
            }
        }
    }

    /**
     * TESTS HELPERS
     * */

    public void update(Person person,PowerupManager powerupManager){
        placePerson(person, true);
        movePlatformForward();
        if (determinePlatformPlacement()){
            platformQueue.addLast(new Platform(1000+200, height, true));
            if(placeCoffeeOnPlatform()){
                powerupManager.addCoffee((int)height, true);
            }
        }
        checkIfOutOfPlay(true);
    }

    //Suppresed Lint Because unused variable is for making test
    @SuppressWarnings("SameParameterValue")
    private void checkIfOutOfPlay(@SuppressWarnings("unused") boolean test){
        if(platformQueue.size>0){
            if(platformQueue.first().getPosition().x<-(100)){

                platformQueue.removeFirst();
            }
        }
    }

    //Suppresed Lint Because unused variable is for making test
    @SuppressWarnings("SameParameterValue")
    private void placePerson(Person person, @SuppressWarnings("unused") boolean test){
        if(platformQueue.size>0) {
            if (platformQueue.first().getPosition().x < person.getPosition().x+person.getWidth()/1.5f && platformQueue.first().getPosition().x +1000/3 > person.getPosition().x && platformQueue.first().getPosition().y < person.getPosition().y) {
                person.setyMin((int) platformQueue.first().getPosition().y);
            }
            else{
                person.setyMin(person.getStandardY());
            }
        }
    }
}
