package javatechnoshop;

import java.util.Date;
import javatechnoshop.proizvodi.Proizvod;
import java.io.Serializable;
/**
 *
 * @author Igor
 */
public class Racun implements Serializable {

    public Proizvod[] proizvodi = new Proizvod[0];
    public Date datumKupovine = new Date();
    public double cijena;

    public Racun() {

    }

    public void dodajStavku(Proizvod p) {
        if (p != null) {
            Proizvod[] temp = new Proizvod[proizvodi.length + 1];
            System.arraycopy(proizvodi, 0, temp, 0, proizvodi.length);
            temp[proizvodi.length] = p;
            proizvodi = temp;
        }
    }

    public void ukloniStavku(Proizvod p) {
        if (p != null) {
            Proizvod[] temp = new Proizvod[proizvodi.length - 1];
            int i = 0;
            for (Proizvod pr : proizvodi) {
                if (!pr.equals(p)) {
                    temp[i] = pr;
                    i++;

                }

            }
            proizvodi = temp;
        }
    }

    public void pregledStavki() {
        System.out.println("Naruceni proizvodi:");
        System.out.println("==================================================");
        for (Proizvod p : proizvodi) {
            System.out.println(p);
            System.out.println("_____________________________________________");

        }
    }

    public void kupovina() {
        System.out.println("Racun za kupovinu " + datumKupovine);
        System.out.println("==================================================");
        for (Proizvod p : proizvodi) {
            cijena += p.getCijena();
            System.out.println(p.getSifra() + " - " + p.getNaziv() + " - " + p.getCijena());
        }
        System.out.println("_____________________________________________");
        System.out.println("Ukupno za placanje " + cijena);
    }

}
