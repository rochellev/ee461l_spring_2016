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
            for(int i = 0; i < 5; ++i) {
                int ret = t.roll(3);
                boolean test = (ret >= i) && (ret <= t.getSides());
                assertFalse(test);
            }
        }
    }

    @Test
    public void testToString() throws Exception {
        for(Die t : Die.values()) {
            assertNotEquals((Object) t.toString(), (Object) ("d" + t.getSides()));
        }
    }
}