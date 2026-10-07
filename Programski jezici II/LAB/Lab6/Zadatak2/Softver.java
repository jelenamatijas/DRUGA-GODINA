public class Softver extends Proizvod{
    String opis;

    Softver(){
        super();
    }

    Softver(String sifra, double cijena, String naziv, Proizvodjac proizvodjac, String opis){
        super(sifra, cijena, naziv, proizvodjac);
        this.opis = opis;
    }

    public String getOpis(){
        return opis;
    }

    @Override
    public String toString(){
        return super.toString() + " Opis: " + opis;
    }
}
