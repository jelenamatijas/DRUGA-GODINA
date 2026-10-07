package javatechnoshop;

import javatechnoshop.proizvodi.Proizvod;

/**
 *
 * @author Igor
 */
public class Cjenovnik {

    public Proizvod[] proizvodi;

    public Cjenovnik() {

    }

    public void prikazProizvoda() {
        System.out.println("Svi proizvodi:");
        System.out.println("==================================================");
        for (Proizvod p : proizvodi) {
            System.out.println(p);
            System.out.println("_____________________________________________");

        }
    }

    public void pretraga(String naziv) {
        System.out.println("Pronadjeni proizvodi:");
        System.out.println("==================================================");
        for (Proizvod p : proizvodi) {
            if (p.getNaziv().equals(naziv)) {
                System.out.println(p);
                System.out.println("_____________________________________________");
            }
        }
    }

    public void pretragaPoSifri(String sifra) {
        System.out.println("Pronadjeni proizvod:");
        System.out.println("==================================================");
        for (Proizvod p : proizvodi) {
            if (p.getSifra().equals(sifra)) {
                System.out.println(p);
                System.out.println("_____________________________________________");
            }
        }
    }
    
    public Proizvod getProizvodSaSifrom(String sifra) {        
        for (Proizvod p : proizvodi) {
            if (p.getSifra().equals(sifra)) {
                return p;
            }
        }
        return null;
    }

}
