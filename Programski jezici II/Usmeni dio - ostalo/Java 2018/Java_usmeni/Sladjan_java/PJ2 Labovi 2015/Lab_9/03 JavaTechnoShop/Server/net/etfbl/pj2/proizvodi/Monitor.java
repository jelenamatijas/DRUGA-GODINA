package net.etfbl.pj2.proizvodi;

import java.io.Serializable;

/**
 *
 * @author Igor
 */
public class Monitor extends Proizvod implements Serializable{
    
    private String model;
    private String konfiguracija;

    public Monitor() {
    }

    public Monitor(String model, String konfiguracija, String sifra,
            double cijena, String naziv, Proizvodjac proizvodjac) {
        super(sifra, cijena, naziv, proizvodjac);
        this.model = model;
        this.konfiguracija = konfiguracija;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getKonfiguracija() {
        return konfiguracija;
    }

    public void setKonfiguracija(String konfiguracija) {
        this.konfiguracija = konfiguracija;
    }

    @Override
    public String toString() {
        return "Monitor:" + "model=" + model + ", konfiguracija=" + konfiguracija + ' ' + super.toString();
    }
    
    
}
