package collectgame;

import java.util.Random;

/**
 *
 * @author Igor
 */
public class Coin {

    private int value;

    public Coin() {
        Random rand = new Random();
        value = rand.nextInt(100);

    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Novcic " + value;
    }

}
