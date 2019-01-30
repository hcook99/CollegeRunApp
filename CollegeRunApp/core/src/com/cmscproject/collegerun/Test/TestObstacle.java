package com.cmscproject.collegerun.Test;

import com.cmscproject.collegerun.Sprites.Obstacle;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 	TestObstacle
 * Class TestObstacle contains test cases for the Obstacle class
 * Test is also associated with Barrier
 * @author Harrison Cook
 */
public class TestObstacle {
    private Obstacle obstacle;

    @Before
    public void setUp(){
        obstacle = new Obstacle(100);
    }

    /**
     * Test position X to ensure value is correct
     * Pass: positionX is verified
     */
    @Test
    public void testPX(){
        assertEquals(100, obstacle.getPositionOb().x,1);
    }
    /**
     * Test position Y to ensure value is correct
     * Pass: positionY is verified
     */
    @Test
    public void testPY(){
        assertEquals(80, obstacle.getPositionOb().y,1);
    }

    /**
     * Test reposition of obstacle to ensure value is correct
     * Pass: reposition is verified
     */
    @Test
    public void testReposition(){
        obstacle.reposition(99,80);
        assertEquals(99,obstacle.getPositionOb().x,1);
    }

    /**
     * Test if moving the obstacle closer to the bck of the screen works
     * */
    @Test
    public void testMoveForward(){
        obstacle.moveForward(200);
        assertEquals(-100, obstacle.getPositionOb().x,1);
    }

}
