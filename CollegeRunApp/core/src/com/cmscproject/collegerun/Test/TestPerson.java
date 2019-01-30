package com.cmscproject.collegerun.Test;

import com.cmscproject.collegerun.Sprites.Person;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * 	TestPerson
 * Class TestPerson contains test cases for the Person class
 * @author Harrison Cook
 */
public class TestPerson {
    private Person person;

    @Before
    public void setUp(){
        person = new Person(500, 80);
    }

    /**
     * Test position X to ensure value is correct
     * Pass: positionX is verified
     */
    @Test
    public void testPX(){
        assertEquals(500, person.getPosition().x,1);
    }

    /**
     * Test position Y to ensure value is correct
     * Pass: positionY is verified
     */
    @Test
    public void testPY(){
        assertEquals(80, person.getPosition().y,1);
    }

    /**
     * UserStory Bottom Barrier
     * Scenario Jumping
     * Given an obstacle spawns, when I jump over the obstacle, then the
     * character's vertical position increases, and the character's energy
     * bar is not affected
     * Test jump method to ensure value is greater than the base value
     * Pass: jump() is verified
     */
    @Test
    public void testJump(){
        person.jump();
        person.addToPositionFromJumpTest();
        assertTrue(person.getPosition().y>80);
    }

}
