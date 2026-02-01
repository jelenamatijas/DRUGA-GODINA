package collectgame;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Igor
 */
public class TrafficLight extends Thread {

    private String light = RED;
    public int id;
    //boje
    public final static String RED = "crveno";
    public final static String YELLOW = "zuto";
    public final static String GREEN = "zeleno";

    public TrafficLight(int id) {
        this.id = id;
        //id % 2 omogucava da semafori ne budu u istim pocetnim stanjima
        light = (id % 2 == 0) ? RED : GREEN;
    }

    public String getLight() {
        return light;
    }

    @Override
    public void run() {
        boolean toRed = id % 2 ==0;
        while (Simulation.NUM_OF_PLAYERS > 0) {
            System.out.println("Trenutno svjetlo na semaforu " + id + " je " + light);
            if (RED.equals(light) || GREEN.equals(light)) {
                try {
                    Thread.sleep(1800);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(700);
                } catch (InterruptedException ex) {
                     ex.printStackTrace();
                }
            }

            switch (light) {
                case RED:
                    light = YELLOW;
                    toRed = false;
                    break;
                case YELLOW:
                    light = toRed ? RED : GREEN;
                    break;
                case GREEN:
                    light = YELLOW;
                    toRed = true;
                    break;
            }
            if (GREEN.equals(light)) {
                for (Player p : Simulation.PLAYERS) {
                    synchronized (p) {
                        p.notify();
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Semafor " + id + " ima upaljeno " + light + " svjetlo.";
    }

}
