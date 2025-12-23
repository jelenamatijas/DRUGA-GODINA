package net.etfbl.pj2.lab11z2.proizvodi;

import java.io.Serializable;

/**
 *
 * @author Igor
 */
public class Proizvod implements Serializable{
    private String sifra;
    private double cijena;
    private String naziv;
    private Proizvodjac proizvodjac;

    public Proizvod() {
    }

    public Proizvod(String sifra, double cijena, String naziv, Proizvodjac proizvodjac) {
        this.sifra = sifra;
        this.cijena = cijena;
        this.naziv = naziv;
        this.proizvodjac = proizvodjac;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Proizvodjac getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(Proizvodjac proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    @Override
    public String toString() {
        return "sifra=" + sifra + ", cijena=" + cijena
                + ", naziv=" + naziv + ", proizvodjac=" + proizvodjac;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proizvod other = (Proizvod) obj;
        if (!this.sifra.equals(other.sifra)) {
            return false;
        }
        return true;
    }
    
    
}
