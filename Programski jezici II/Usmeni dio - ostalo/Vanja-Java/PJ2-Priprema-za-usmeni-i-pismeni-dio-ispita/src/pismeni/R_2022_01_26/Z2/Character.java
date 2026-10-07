package pismeni.R_2022_01_26.Z2;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Character extends Thread {

    String s;
    Integer count = 0;
    static Map<String, Integer> mapa = new ConcurrentHashMap<>();

    public Character(String s) {
        this.s = s;
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        try {
            synchronized (mapa) {
                if (mapa.containsKey(s))
                    mapa.put(s, mapa.get(s) + 1);
                else
                    mapa.put(s, 1);

                System.out.println(s + " : " + mapa.get(s));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}