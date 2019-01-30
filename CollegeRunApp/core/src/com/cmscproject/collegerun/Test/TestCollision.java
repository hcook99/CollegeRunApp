package com.cmscproject.collegerun.Test;

import com.cmscproject.collegerun.Sprites.Obstacle;
import com.cmscproject.collegerun.Sprites.Person;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * TestCollision is associated with LevelEnd scenario because it makes sure that a collision can occur and if it occurs
 * then this covers the problem
 * @author Harrison Cook
 * */
public class TestCollision {

    private Obstacle obstacle;
    private Person person;

    @Before
    public void setUp(){
        obstacle = new Obstacle(100);
        person = new Person(100,30);
    }

    @Test
    public void testCollision(){
        assertTrue(obstacle.getBound().overlaps(person.getBounds()));
    }
}
