package dice;/*
 * This is a simple coin flipping application
 * Use in any role playing video game or table top game
 * Can be used in any existing java project
 * Programmed specifically in jre7
 * Code written by Jester155
 * For Shana
 */

import java.util.Random;

public enum Die {
    d2(2), d3(3), d4(4), d5(5), d6(6),
    d8(8), d10(10), d12(12), d15(15),
    d20(20), d30(30), d100(100);

    private int _sides;

    Die(int s) {
        _sides = s;
    }

    public int roll() {
        Random r = new Random();
        return r.nextInt(_sides + 1); //add 1 to make _sides the max possible value
    }

    public int roll(int amount) {
        int ret = 0;
        for(int i = 0; i < amount; ++i) {
            ret += this.roll();
        }
        return ret;
    }

    @Override
    public String toString() {
        return "d" + Integer.toString(_sides);
    }
}
