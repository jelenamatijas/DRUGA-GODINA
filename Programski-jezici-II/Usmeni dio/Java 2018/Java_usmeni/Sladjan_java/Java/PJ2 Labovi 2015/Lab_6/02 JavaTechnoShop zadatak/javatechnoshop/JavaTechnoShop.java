package javatechnoshop;

import java.util.Scanner;
import javatechnoshop.proizvodi.Monitor;
import javatechnoshop.proizvodi.Proizvod;
import javatechnoshop.proizvodi.Proizvodjac;
import javatechnoshop.proizvodi.Racunar;
import javatechnoshop.proizvodi.Softver;
import javatechnoshop.proizvodi.Telefon;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Igor
 */
public class JavaTechnoShop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("JavaTechnoShop");

        //formiranje proizvoda
        Proizvodjac samsung = new Proizvodjac("Samsung", "Juzna Koreja", "info@samsung.com");
        Proizvodjac lenovo = new Proizvodjac("Lenovo", "Kina", "info@lenovo.com");
        Proizvodjac lg = new Proizvodjac("LG", "Juzna Koreja", "info@lg.com");
        Proizvodjac microsoft = new Proizvodjac("Microsoft", "USA", "info@microsoft.com");

        Monitor m1 = new Monitor("123", "    Screen size (inches)"
                + "    Panel Type 34"
                + "    Aspect Ratio IPS"
                + "    Resolution 21:9", "m100", 300.0, "Monitor LG 123", lg);

        Racunar r1 = new Racunar("Intel i3, RAM 4 GB, HDD 300 GB, Windows 7", "r100", 1025.5, "Laptop ThinkPad", lenovo);
        Racunar r2 = new Racunar("Intel i5, RAM 8 GB, HDD 500 GB, Windows 7", "r101", 1325.5, "Laptop ThinkPad", lenovo);
        Racunar r3 = new Racunar("Intel i7, RAM 16 GB, HDD 1 TB, Windows 7", "r102", 1625.5, "Laptop ThinkPad", lenovo);

        Telefon t1 = new Telefon("Galaxy S4", "RAM 2 GB, Super AMOLED capacitive touchscreen, 16M colors ", "t100",
                800, "Samsung Galaxy S4", samsung);

        Softver s1 = new Softver("Operativni sistem Windows 7", "s100", 100, "Windows 7", microsoft);

        Cjenovnik cjenovnik = new Cjenovnik();
        cjenovnik.proizvodi = new Proizvod[]{m1, r1, r2, r3, t1, s1};
        // cjenovnik.prikazProizvoda();

        Racun racun = new Racun();
        //Kupovina
        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        int opcija = 0;
        do {
            System.out.println("Opcije:");
            System.out.println("1 - prikaz cjenovnika");
            System.out.println("2 - pretraga proizvoda po nazivu");
            System.out.println("3 - pretraga proizvoda po sifri");
            System.out.println("4 - kupovina proizvoda");
            System.out.println("5 - pregled svih kupljenih proizvoda");
            System.out.println("6 - uklanjanje proizvoda sa racuna");
            System.out.println("7 - zavrsetak kupovine");
            System.out.println("8 - prikaz svih racuna i ukupan iznos");
            opcija = scan2.nextInt();
            switch (opcija) {
                case 1:
                    //prikaz cjenovnika
                    cjenovnik.prikazProizvoda();
                    break;
                case 2:
                    System.out.println("Unesite naziv proizvoda:");
                    String p = scan.nextLine();
                    cjenovnik.pretraga(p);
                    break;
                case 3:
                    System.out.println("Unesite sifru proizvoda:");
                    p = scan.nextLine();
                    cjenovnik.pretragaPoSifri(p);
                    break;
                case 4:
                    System.out.println("Unesite sifru proizvoda koji kupujete:");
                    p = scan.nextLine();
                    racun.dodajStavku(cjenovnik.getProizvodSaSifrom(p));
                    break;
                case 5:
                    racun.pregledStavki();
                    break;
                case 6:
                    System.out.println("Unesite sifru proizvoda koji uklanjate sa racuna:");
                    p = scan.nextLine();
                    racun.ukloniStavku(cjenovnik.getProizvodSaSifrom(p));
                    break;
              case 8:
                FileInputStream citac;               
                File [] files = new File("./racuni/").listFiles();
                double iznos = 0.0;
              for(File f: files){
                try {
                  citac = new FileInputStream(f);                  
                  ObjectInputStream citanjeObjekta = new ObjectInputStream(citac);
                  Racun r = (Racun) citanjeObjekta.readObject();
                  System.out.println("Racun: " + r.datumKupovine + " Iznos: " + r.cijena);
                  iznos += r.cijena;
                  citanjeObjekta.close();
                  citac.close();
                } catch (FileNotFoundException e) {
                  System.out.println("Fajl nije pronadjen!");
                } catch (Exception e) {
                  e.printStackTrace();
                  System.out.println("Exception!");
                }
              }
              System.out.println("Ukupan prihod: " + iznos);
              break;

            }
        } while (opcija != 7);
        if(racun.proizvodi.length > 0){
        racun.kupovina();
        
        try {
          SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yy_hh_mm_ss");
          String fileName =sdf.format(new Date());
          FileOutputStream pisac = new FileOutputStream("./racuni/"+fileName+".etf");
          ObjectOutputStream upisObjekta = new ObjectOutputStream(pisac);
          upisObjekta.writeObject(racun);
          upisObjekta.close();
          pisac.close();
        } catch (FileNotFoundException e) {
          System.out.println("Fajl nije pronadjen!");
        } catch (IOException e) {
          e.printStackTrace();
          System.out.println("IOException!");
        }
        }
        
    }

}
