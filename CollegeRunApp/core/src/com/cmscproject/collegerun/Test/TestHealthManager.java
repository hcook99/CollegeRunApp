package com.cmscproject.collegerun.Test;

import com.cmscproject.collegerun.Sprites.HealthManager;
import com.cmscproject.collegerun.Sprites.MediumCoffee;
import com.cmscproject.collegerun.Sprites.SmallCoffee;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class associated with HealthManager Issue
 * @author Aidan Myers
 */
public class TestHealthManager {
    private HealthManager healthManager;

    @Before
    public void setUp(){
        healthManager = new HealthManager();
    }

    @Test
    public void testHealthManager(){
        assertEquals(1.0, healthManager.getEnergy(), 0.1);
    }

    /**
     * Test associated with Scenario 1 while loop is used to simulate as if the game was running
     * so while the player does not hit coffee energy should lower which is achieved by lowering energy
     * */
    @Test
    public void testLowerEnergy(){
        for(int i =0;i<200; i++){
            healthManager.lowerEnergy();
        }
        assertEquals(1-(0.0009f*200), healthManager.getEnergy(), 0.1);
    }



    /**
     * Test associated with Scenario 3 character running into a coffee if the two collide the game should add the amount of
     * energy from that given coffee to the players energy
     * */
    @Test
    public void testAddEnergy(){
        for(int i =0;i<200; i++){
            healthManager.lowerEnergy();
        }
        healthManager.addEnergy(new SmallCoffee(100,100,true).getRestoreValue());
        assertEquals(1.0-(0.0009f*200)+0.15f, healthManager.getEnergy(), 0.1);
    }

    /**
     * Test associated Scenario 3 character hits a coffee that causes the
     * health to go above the maximum allowed so the value is set to 1
     * */
    @Test
    public void testOverloadCoffee(){
        for(int i =0;i<200; i++){
            healthManager.lowerEnergy();
        }
        healthManager.addEnergy(new MediumCoffee(100,100, true).getRestoreValue());
        assertEquals(1.0, healthManager.getEnergy(),0.1);
    }
}