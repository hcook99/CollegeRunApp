package com.cmscproject.collegerun.Test;

import com.cmscproject.collegerun.Sprites.PowerUp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


/**
 * 	PowerUpTest
 * Class PowerUpTest contains test cases for the PowerUp class
 * @author Ethan M. Hopkins
 * @version CMSC 355-01 10/11/2018
 */

@SuppressWarnings("ResultOfMethodCallIgnored")
public class TestPowerUp {

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }



    /**
     * Test testRandom implements the method body of the private method random in PowerUp and
     * passes if it produces a random integer between 1 and 100
     * Pass: If result equals a value equal to or between 1 and 100
     */
    @Test
    public void testRandom() {
        int result;
        int range = (100) + 1;
        result = (int)(Math.random() * range);

        if(result >= 1 && result <= 100)
        {
            assertTrue(true);
        }
        else
        {
            fail();
        }
    }

    /**
     * Test testGetPosition instantiates a mock PowerUp object to confirm the relationship between
     * PowerUp.getPosition() and result and confirms that it returns type Vector2
     * Pass: If getPosition() is verified
     */
    @Test
    public void testGetPosition() {
        PowerUp testObject = Mockito.mock(PowerUp.class);
        verify(testObject).getPosition();
        verifyNoMoreInteractions(testObject);
    }
}