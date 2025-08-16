package mapa;

import java.util.PriorityQueue;
import java.util.Random;
import osoba.*;
import elementi.*;

public class Mapa {
    public static Element MAPA[] = new Element[100];
    static Random rand = new Random();

    public static void postaviJednuVrstuPrepreke(String prepreka){
        if(prepreka.equals("Vatra")){
            int brojPrepreka = 0;
            while(brojPrepreka < 10){
                int x = rand.nextInt(100);
                if(MAPA[x] == null){
                    MAPA[x] = new Vatra(rand.nextInt(100));
                    brojPrepreka++;
                }
            }
        }else if(prepreka.equals("Voda")){
            int brojPrepreka = 0;
            while(brojPrepreka < 10){
                int x = rand.nextInt(100);
                if(MAPA[x] == null){
                    MAPA[x] = new Voda(rand.nextInt(100), rand.nextBoolean());
                    brojPrepreka++;
                }
            }
        }else if(prepreka.equals("Stijena")){
            int brojPrepreka = 0;
            while(brojPrepreka < 10){
                int x = rand.nextInt(100);
                if(MAPA[x] == null){
                    MAPA[x] = new Stijena(rand.nextInt(100), rand.nextInt(100));
                    brojPrepreka++;
                }
            }
        }
    }

    public static void main(String[] args) {
        postaviJednuVrstuPrepreke("Vatra");
        postaviJednuVrstuPrepreke("Voda");
        postaviJednuVrstuPrepreke("Stijena");

        PriorityQueue<Osoba> osobe = new PriorityQueue<>();
        Pilot p = new Pilot("Pilot", rand.nextInt(100));
        Planinar pl = new Planinar("Planinar", rand.nextInt(100));
        Vatrogasac v = new Vatrogasac("Vatrogasac", rand.nextInt(100));
        osobe.add(p);
        osobe.add(pl);
        osobe.add(v);

       try {
         while(!osobe.isEmpty()){
            Osoba o = osobe.poll();
            System.out.println(o + " krece sa prelazenjem prepreka.");
            o.start();
            Thread.sleep(1000);
        }
       } catch (InterruptedException e) {
            e.printStackTrace();
       }
    }
}
