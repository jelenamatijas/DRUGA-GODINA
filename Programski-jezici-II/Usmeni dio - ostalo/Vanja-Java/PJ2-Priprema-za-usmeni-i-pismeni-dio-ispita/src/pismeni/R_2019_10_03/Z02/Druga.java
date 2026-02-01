package pismeni.R_2019_10_03.Z02;

import java.util.Random;

public class Druga extends Thread {

    public void run() {
        char slovo = (char) (new Random().nextInt(26) + 'a');
        new Treca(String.valueOf(slovo)).run();
    }
}