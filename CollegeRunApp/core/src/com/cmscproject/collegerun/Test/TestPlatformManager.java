package com.cmscproject.collegerun.Test;

import com.cmscproject.collegerun.Sprites.Person;
import com.cmscproject.collegerun.Sprites.PlatformManager;
import com.cmscproject.collegerun.Sprites.PowerupManager;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Test associated with Issue Platforms
 * @author Harrison Cook
 * */
public class TestPlatformManager {
    private PlatformManager platformManager;


    /**
     * Initialize PlatformManager
     * */
    @Before
    public void setUp(){
        platformManager = new PlatformManager(200, 25);
    }


    /**
     * Test associated with Scenario 1. The idea of this test is to assure
     * that a platform will randomly spawn at some point on the screen.
     * */
    @Test
    public void testScroll(){
        for(int i = 0;i<2000;i++){
            platformManager.update(new Person(10,10), new PowerupManager(1,1,2));
            if(platformManager.getPlatformQueue().size>0) {
                break;
            }
        }
        assertTrue(platformManager.getPlatformQueue().size>0);
    }


    /**
     * Test associated with Scenario 2 written to assure that the player can land on a platform.
     * This is achieved by making the player jump and once the player comes back down onto a
     * platform there minimum y position is set to the height of the platform.
     * */
    @Test
    public void testJumpOnPlatform(){
        Person person = new Person(100,90);
        int firstJump = 0;
        for(int  i = 0; i<10000;i++){
            platformManager.update(person, new PowerupManager(1,1,1));
            if(platformManager.getPlatformQueue().size>0){
                if (platformManager.getPlatformQueue().first().getPosition().x<person.getPosition().x){
                    person.jump();
                    person.addToPositionFromJumpTest();
                    firstJump++;
                    if(firstJump>1){
                        break;
                    }
                }
            }
        }
        assertEquals(person.getyMin(), platformManager.getPlatformQueue().first().getPosition().y, 0.0);
    }


    /**
     * Test associated with Scenario 3 Assures that coffee will spawn on a platform randomly.
     * */
    @Test
    public void testCoffeeOnPlatform(){
        PowerupManager powerupManager = new PowerupManager(1,25,100);
        for(int i = 0;i<10000;i++){
            platformManager.update(new Person(100,100),powerupManager);
            if(platformManager.getPlatformQueue().size>0){
                if(powerupManager.getPowerups().size>0){
                    break;
                }
            }
        }
        assertTrue(powerupManager.getPowerups().size>0);
    }

}
