package collectgame;

import java.util.Random;

/**
 *
 * @author Igor
 */
public class Simulation {

    public final static int NUM_OF_COINS = 5;
    public final static int NUM_OF_TRAFFIC_LIGHT = 2;
    public final static int FIELD_NUMBERS = 20;
    public static Object[] MAP = new Object[FIELD_NUMBERS];
    public static int NUM_OF_PLAYERS = 2;
    public static Player[] PLAYERS = new Player[NUM_OF_PLAYERS];   

    public static void printMap() {
        System.out.println("__________________MAPA_____________________");
        for (int i = 0; i < MAP.length; i++) {
            System.out.println("MAP[" + i + "] " + MAP[i]);
        }
        System.out.println("___________________________________________");
    }

    public static void main(String[] args) {

        
        Random rand = new Random();

        //postavljanje 5 novcica
        int i = NUM_OF_COINS;
        while (i > 0) {
            int val = rand.nextInt(FIELD_NUMBERS);
            if (MAP[val] == null) {
                MAP[val] = new Coin();
                i--;

            }
        }

        //postavljanje 2 semafora
        i = NUM_OF_TRAFFIC_LIGHT;
        while (i > 0) {
            int val = rand.nextInt(FIELD_NUMBERS);
            if (MAP[val] == null) {
                TrafficLight tl = new TrafficLight(i);
                tl.start();
                MAP[val] = tl;
                i--;

            }
        }

        printMap();
        
        for(i=0;i<NUM_OF_PLAYERS;i++){
          //i%2==0 omogucava da igraci idu u suprotnim smjerovima
            PLAYERS[i]=new Player("Test "+i, (i%2==0));
            PLAYERS[i].start();
        }

    }

}
