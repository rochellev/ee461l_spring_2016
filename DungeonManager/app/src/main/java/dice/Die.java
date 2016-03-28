package dice;
/**
* Created by Nico Cortes on 3/24/2016.
* simple dice roller to...
* - roll a single die
* - roll multiple of the same die
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

    public int getSides() {
        return _sides;
    }

    public int roll() {
        Random r = new Random();
        return r.nextInt(_sides) + 1; //add 1 to make range from 1 to _sides
    }

    public int roll(int amount) { //if amount < 0 will just return 0
        int ret = 0;
        for(int i = 0; i < amount; ++i) { // need to do to maintain probability
            ret += this.roll();
        }
        return ret;
    }

    @Override
    public String toString() {
        return "d" + Integer.toString(_sides);
    }
}
