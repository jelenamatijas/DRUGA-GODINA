package fabrika;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.Random;

/**
 *
 * @author Igor
 */
public class Fabrika {

   public static final int BROJ_PROIZVODNIH_TRAKA = 5;
   public static final String DIR_PROIZVODI = "./proizvodi/";

    public static void brisanjeProizvoda() {
      try{
        //brisanje sadrzaja fajla
        FileOutputStream fos = new FileOutputStream(ProizvodnaTraka.PROIZVODI);
        fos.close();
      }catch(Exception ex){
        System.out.println(ex);
      }
      
      //brisanje prethodno napravljenih proizvoda
        File[] files = new File(DIR_PROIZVODI).listFiles();
        for (File f : files) {
          f.delete();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //kreiranje foldera za proizvode
        new File(DIR_PROIZVODI).mkdir();
        brisanjeProizvoda();
        
        //kreiranje proizvodnih traka
        ProizvodnaTraka[] trake = new ProizvodnaTraka[BROJ_PROIZVODNIH_TRAKA];
        for (int i = 0; i < BROJ_PROIZVODNIH_TRAKA; i++) {
            ProizvodnaTraka t = new ProizvodnaTraka(i, new Random().nextInt(2000));
            trake[i] = t;
            t.start();
        }

        //cekanje da sve trake zavrse sa radom
        for (ProizvodnaTraka p : trake) {
            try {
                p.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Sve trake su zavrsile sa radom.");

        //analiza proizvodnje
        int[] ispravniProizvodiPoTrakama = new int[BROJ_PROIZVODNIH_TRAKA];
        int[] neispravniProizvodiPoTrakama = new int[BROJ_PROIZVODNIH_TRAKA];

        File[] files = new File(DIR_PROIZVODI).listFiles();
        for (File f : files) {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(f));
                Proizvod p = (Proizvod) ois.readObject();
                if (p.imaGresku) {
                    neispravniProizvodiPoTrakama[Integer.parseInt(p.serijskiBroj.charAt(0) + "")]++;
                } else {

                    ispravniProizvodiPoTrakama[Integer.parseInt(p.serijskiBroj.charAt(0) + "")]++;
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    ois.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        //prikaz vrijednosti analize
        for (int i = 0; i < BROJ_PROIZVODNIH_TRAKA; i++) {
            System.out.println("Traka " + i + " je napravila " + ispravniProizvodiPoTrakama[i]
                    + " ispravnih i " + neispravniProizvodiPoTrakama[i] + " neispravnih proizvoda. Uspjesnost trake je "
                    + 100 * ispravniProizvodiPoTrakama[i] / 10 + "%");
        }
    }

}
