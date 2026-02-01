package net.etfbl.pj2.lab11z2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.etfbl.pj2.lab11z2.proizvodi.*;

/**
 *
 * @author Igor
 */
public class Cjenovnik {

    private HashMap<String, Proizvod> proizvodi = new HashMap<String, Proizvod>();

    public Cjenovnik() {
        init();
    }

    public void prikazCjenovnika() {
        System.out.println("Svi proizvodi:\n");
        Iterator p = proizvodi.values().iterator();
        while (p.hasNext()) {
            System.out.println(p.next());
            System.out.println("---------------------------------------------");
        }
    }

    public ArrayList sviProizvodi() {
        return new ArrayList(proizvodi.values());
    }

    public boolean pretragaPoProizvodu(Proizvod p) {
        return proizvodi.containsValue(p);
    }

    public Proizvod pretragaPoSifri(String sifra) {
        return proizvodi.get(sifra);
    }

    public void dodaj(Proizvod p) {
        proizvodi.put(p.getSifra(), p);
    }

    private void init() {
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
        Proizvod[] pr = new Proizvod[]{m1, r1, r2, r3, t1, s1};
        for (Proizvod p : pr) {
            proizvodi.put(p.getSifra(), p);
        }
    }

    public static void main(String[] args) {
        Cjenovnik c = new Cjenovnik();
        c.prikazCjenovnika();

        System.out.println("Pretraga:\n");
        System.out.println(c.pretragaPoSifri("r100"));
        //sifra koja ne postoji
        System.out.println(c.pretragaPoSifri("r1201"));

        c.dodaj(new Softver("JDK", "s192", 0.0, "Java Development Kit", new Proizvodjac("Oracle", null, null)));
        System.out.println();
        c.prikazCjenovnika();

        //postojeci proizvod
        System.out.println(c.pretragaPoProizvodu(
                new Softver("JDK", "s192", 0.0, "Java Development Kit", new Proizvodjac("Oracle", null, null)))
        );
        
        //razlicit opis
         System.out.println(c.pretragaPoProizvodu(
                new Softver("JDK123", "s192", 0.0, "Java Development Kit", new Proizvodjac("Oracle", null, null)))
        );
         //pogledati equals metodu Proizvod klase
         //razlicita sifra 
          System.out.println(c.pretragaPoProizvodu(
                new Softver("JDK", "s1922", 0.0, "Java Development Kit", new Proizvodjac("Oracle", null, null)))
        );
         
        
    }

}
