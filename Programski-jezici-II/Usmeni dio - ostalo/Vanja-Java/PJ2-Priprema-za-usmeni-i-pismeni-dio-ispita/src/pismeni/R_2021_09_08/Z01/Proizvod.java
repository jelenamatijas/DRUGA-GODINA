package pismeni.R_2021_09_08.Z01;

public class Proizvod implements Element {
    String sifra;
    String naziv;
    int kolicina;
    double cijena;

    public Proizvod(String sifra, String naziv, int kolicina, double cijena) {
        this.sifra = sifra;
        this.naziv = naziv;
        this.kolicina = kolicina;
        this.cijena = cijena;
    }
}