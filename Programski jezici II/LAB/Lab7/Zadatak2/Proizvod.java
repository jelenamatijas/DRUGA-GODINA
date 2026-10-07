import java.io.Serializable;

public class Proizvod implements Serializable{
    String sifra;
    double cijena;
    String naziv;
    Proizvodjac proizvodjac;

    Proizvod(){
        super();
    }

    Proizvod(String sifra, double cijena, String naziv, Proizvodjac proizvodjac){
        this.sifra = sifra;
        this.cijena = cijena;
        this.naziv = naziv;
        this.proizvodjac = proizvodjac;
    }

    Proizvod(Proizvod p){
        new Proizvod(p.sifra, p.cijena, p.naziv, p.proizvodjac);
    }

    public String getSifra(){
        return sifra;
    }

    public double getCijena(){
        return cijena;
    }

    public String getNaziv(){
        return naziv;
    }

    public Proizvodjac getProizvodjac(){
        return proizvodjac;
    }

    @Override
    public String toString(){
        return "Sifra: " + sifra + " Naziv: " + naziv + " Cijena: " + cijena + " Proizvodjac: " + proizvodjac.getNaziv();
    }

    @Override
    public boolean equals(Object p){
        return (this.sifra == ((Proizvod)p).sifra) ? true : false;
    }   
    
}
