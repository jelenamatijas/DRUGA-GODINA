package collectgame;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Igor
 */
public class Player extends Thread {

    private String playerName;
    private int position;
    private boolean forward;
    private int value;
    private int endPosition;
    private int numOfFields = 0;
    private long duration;

    public Player(String playerName, boolean forward) {
        this.playerName = playerName;
        if (forward) {
            position = 0;
            endPosition = Simulation.FIELD_NUMBERS-1;
        } else {
            position = Simulation.FIELD_NUMBERS-1;
            endPosition = 0;
        }
        this.forward = forward;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }   

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Igrac " + playerName + " je na poziciji " + position;
    }

    @Override
    public void run() {      
        duration = (new Date()).getTime(); //vrijeme kada je pocelo kretanje po mapi
        while (numOfFields != Simulation.FIELD_NUMBERS) {
            numOfFields++;
            try {
                Thread.sleep(500);
                System.out.println(this);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
           

            //provjera mape
            synchronized (this) {
                if (Simulation.MAP[position] instanceof Coin) {
                    int val = ((Coin) Simulation.MAP[position]).getValue();
                    value += val;
                    Simulation.MAP[position] = null;
                    System.out.println(">> Igrac " + playerName + " je pokupio " + val + " novcic.");
                }

                if (Simulation.MAP[position] instanceof TrafficLight) {
                    TrafficLight tl = (TrafficLight) Simulation.MAP[position];
                    System.out.println(">> Igrac " + playerName + " je na semaforu." + tl.id
                            + " Trenutno svjetlo je " + tl.getLight());
                    try {
                        if (!TrafficLight.GREEN.equals(tl.getLight())) {
                            wait();
                        }

                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println("Igrac " + playerName + " nastavlja dalje.");
                }
            }
             if (forward) {
                position += 1;
            } else {
                position -= 1;
            }
        }
        System.out.println(">>>> " + playerName + " je zavrsio simulaciju sa " + value + " novcica!"
                + " Simulacija je trajala " + ((new Date()).getTime() - duration) + " ms.");
        Simulation.NUM_OF_PLAYERS--;

    }

}
