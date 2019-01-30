package com.cmscproject.collegerun.Test;

import com.cmscproject.collegerun.Sprites.LargeCoffee;
import com.cmscproject.collegerun.Sprites.MediumCoffee;
import com.cmscproject.collegerun.Sprites.SmallCoffee;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Scenario CoffeeVariations is a test to test variations in coffee amount
 * @author Harrison Cook
 * */
public class TestCoffeeVariations {
    private LargeCoffee largeCoffee;
    private MediumCoffee mediumCoffee;
    private SmallCoffee smallCoffee;

    @Before
    public void setUp(){
        largeCoffee = new LargeCoffee(1,1,true);
        mediumCoffee = new MediumCoffee(1,1,true);
        smallCoffee = new SmallCoffee(1,1,true);
    }

    @Test
    public void testLargeCoffee(){
        assertEquals(1.0, largeCoffee.getRestoreValue(),0.1);
    }

    @Test
    public void testMediumCoffee(){
        assertEquals(0.3, mediumCoffee.getRestoreValue(),0.1);
    }

    @Test
    public void testSmallCoffee(){
        assertEquals(0.15, smallCoffee.getRestoreValue(),0.1);
    }
}
