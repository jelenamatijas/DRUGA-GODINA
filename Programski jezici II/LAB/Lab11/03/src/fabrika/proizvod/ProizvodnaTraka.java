package src.fabrika.proizvod;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;

public class ProizvodnaTraka extends Thread{
    public static String PROIZVODI = "proizvodi.txt";
    private int id;
    private int brzinaRada;

    public ProizvodnaTraka(int id, int brzinaRada){
        this.brzinaRada = brzinaRada;
        this.id = id;
    }

    @Override
    public void run(){
        Random rand =  new Random();
        try{
            System.out.println("Proivodna traka " + id + " je zapocela sa radom.");
            for(int i=0; i<10; i++){
                try{
                    System.out.println("Napravljen " + (i+1) + ". proizvod na traci " + id);
                    String serijskiBroj = String.valueOf(id )+ String.valueOf((new Date().getTime()));
                    boolean greska = rand.nextInt(100) < 8;
                    Proizvod p = new Proizvod("Proizvod", serijskiBroj, greska);
                    ObjectOutputStream pf = new ObjectOutputStream(new FileOutputStream(Fabrika.DIR + "proizvod" + id + serijskiBroj));
                    pf.writeObject(p);
                    pf.close();

                    synchronized(Fabrika.lock){
                        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(PROIZVODI, true)));
                        pw.write("Traka broj" + id + " je napravila proizvod " + p.getSerijskiBroj() + "\n");
                        pw.close();
                    }

                    sleep(brzinaRada);

                    if(rand.nextInt(100) < 10){
                        System.out.println("Zastoj na traci " + id);
                        sleep(5000);
                    }

                    if(rand.nextInt(100) < 5){
                        System.out.println("Desio se kvar na traci " + id);
                        throw new Exception("Prekid rada " + id + ". trake!");
                    }

                }catch(IOException e){
                    e.printStackTrace();
                }
            }

            System.out.println("Traka " + id + " je zavrsila sa radom.");
        }catch(Exception e){
            e.printStackTrace();
        }

       
    }
}
