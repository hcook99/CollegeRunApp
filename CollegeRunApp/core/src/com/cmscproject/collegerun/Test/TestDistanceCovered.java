package com.cmscproject.collegerun.Test;

import com.cmscproject.collegerun.Sprites.DistanceManager;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * The class TestDistanceCovered is associated with the the issue called Distance Covered
 * @author Harrison Cook
 * */
public class TestDistanceCovered {
    private DistanceManager distanceManager;

    @Before
    public void setUp(){
        distanceManager = new DistanceManager(1000, true);
    }

    /**
     * Test associated with Scenario 1. The idea of this test is to assure
     * that as the player runs his distance should increase.
     * */
    @Test
    public void testDistanceIncrease(){
        for(int i = 0; i<200;i++){
            distanceManager.update();
        }
        assertTrue(distanceManager.getDist()>1);
    }


    /**
     * Test associated with Scenario 2. The idea of this test is to assure
     * that if the player has reached the end of the level (aka maxDist) then the game will end.
     * */
    @Test
    public void testgameOver(){
        for(int i =0;i<1001;i++){
            distanceManager.update();
        }
        assertTrue(!distanceManager.maxDistNotReached());
    }

    /**
     * Test associated with Scenario 3. The idea of this test is to assure
     * that as a player runs the size of the distance till the end should
     * increase for the ui progress bar.
     * */
    @Test
    public void testDrawIncrease(){
        float initialBuffer = distanceManager.drawSize(20,2000);
        for(int i = 0;i<300;i++){
            distanceManager.update();
        }
        assertTrue(distanceManager.drawSize(20,2000)>initialBuffer);
    }
}
