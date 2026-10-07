package javatechnoshop.proizvodi;
/**
 *
 * @author Igor
 */
public class Telefon extends Proizvod {
    private String model;
    private String konfiguracija;

    public Telefon() {
    }

    public Telefon(String model, String konfiguracija, String sifra, double cijena, 
            String naziv, Proizvodjac proizvodjac) {
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
        return "Telefon: " + "model=" + model + ", konfiguracija=" + konfiguracija + ' ' + super.toString();
    }
    
    
}
