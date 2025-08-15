public class Racunar extends Proizvod{
    String konfiguracija;
    
    Racunar(){
        super();
    }

    Racunar(String sifra, double cijena, String naziv, Proizvodjac proizvodjac, String konfiguracija){
        super(sifra, cijena, naziv, proizvodjac);
        this.konfiguracija = konfiguracija;
    }

    public String getKonfiguracija(){
        return konfiguracija;
    }

    @Override
    public String toString(){
        return super.toString() + " Konfiguracija: " + konfiguracija;
    }
}

