package net.etfbl.pj2.lab11z2.proizvodi;

import java.io.Serializable;

/**
 *
 * @author Igor
 */
public class Softver extends Proizvod implements Serializable{
    private String opis;

    public Softver() {
    }

    public Softver(String opis, String sifra, double cijena, String naziv, Proizvodjac proizvodjac) {
        super(sifra, cijena, naziv, proizvodjac);
        this.opis = opis;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "Softver: " + "opis=" + opis + ' ' + super.toString();
    }
    
    
}
