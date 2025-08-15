public class Telefon extends Proizvod{
    String konfiguracija, model;

    Telefon(){
        super();
    }

    Telefon(String sifra, double cijena, String naziv, Proizvodjac proizvodjac, String konfiguracija, String model){
        super(sifra, cijena, naziv, proizvodjac);
        this.konfiguracija = konfiguracija;
        this.model = model;
    }

    public String getKonfiguracija(){
        return konfiguracija;
    }

    public String getModel(){
        return model;
    }

    @Override
    public String toString(){
        return super.toString() + " Konfiguracija: " + konfiguracija + " Model: " + model;
    }
}
