package dice;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Nico on 3/27/2016.
 * tests for Die testing...
 * - to ensure correct number is returned for each dice roll method
 */
public class DieTest {
    @Test
    public void testRoll() throws Exception {
        for(Die t : Die.values()) {
            for(int i = 1; i < 5; ++i) {
                int ret = t.roll(i);
                boolean test = (ret >= i) && (ret <= t.getSides() * i);
                assertTrue(test);
            }
        }
    }

    @Test
    public void testToString() throws Exception {
        for(Die t : Die.values()) {
            assertEquals(t.toString(), "d" + t.getSides());
        }
    }
}