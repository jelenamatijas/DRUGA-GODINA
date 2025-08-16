package src.fabrika.proizvod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectInputValidation;
import java.util.Random;

public class Fabrika {
    public static final String DIR = "./proizvodi/";
    public static final int BROJ_PROIZVODNIH_TRAKA = 5;
    public static Object lock = new Object();

    public static void brisanjeProzivoda(){

        try{
            FileOutputStream fos = new FileOutputStream(ProizvodnaTraka.PROIZVODI);
            fos.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        File files[] = new File(DIR).listFiles();
        for (File file : files) {
            file.delete();
        }

    }

    public static void main(String []args){
        new File(DIR).mkdir();
        brisanjeProzivoda();

        ProizvodnaTraka proizvodneTrake[] = new ProizvodnaTraka[BROJ_PROIZVODNIH_TRAKA];
        for(int i=0; i< BROJ_PROIZVODNIH_TRAKA; i++){
            proizvodneTrake[i] = new ProizvodnaTraka(i, new Random().nextInt(2000));
            proizvodneTrake[i].start();
        }

        try{
            for (ProizvodnaTraka proizvodnaTraka : proizvodneTrake) {
                proizvodnaTraka.join();
            }
        }catch(InterruptedException e){
            e.printStackTrace();;
        } 

        int brojNeispravnihProizvoda[] = new int[BROJ_PROIZVODNIH_TRAKA];
        int brojIspravnihProizvoda[] = new int[BROJ_PROIZVODNIH_TRAKA];

        File files[] = new File(DIR).listFiles();
        for (File file : files) {
            ObjectInputStream obj = null;
            try{
                obj = new ObjectInputStream(new FileInputStream(file));
                Proizvod p = (Proizvod)obj.readObject();
                if(p.getGreska()){
                    brojNeispravnihProizvoda[Integer.parseInt(p.getSerijskiBroj().charAt(0)+ "")]++;
                }else{
                    brojIspravnihProizvoda[Integer.parseInt(p.getSerijskiBroj().charAt(0) + "")]++;
                }
                
            }catch(IOException | ClassNotFoundException e){
                e.printStackTrace();
            }finally{
                try{
                    obj.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }

        for(int i=0; i<BROJ_PROIZVODNIH_TRAKA; i++){
            System.out.println("Traka " + i + " je napravila " + brojIspravnihProizvoda[i] + " ispravnih proizvoda i "
                + brojNeispravnihProizvoda[i] + " neispravnih proizvoda. Uspjesnost trake je " + 100*brojIspravnihProizvoda[i]/10 + "%.");
        }
    }
}
