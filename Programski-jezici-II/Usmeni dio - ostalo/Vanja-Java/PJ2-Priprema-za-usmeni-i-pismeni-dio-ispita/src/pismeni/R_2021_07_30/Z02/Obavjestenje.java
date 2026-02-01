package pismeni.R_2021_07_30.Z02;

public class Obavjestenje extends Podatak {

    String naslov;
    String opis;
    static int count;

    public Obavjestenje() {
        naslov = "NASLOV " + count;
        opis = "OPIS " + (++count);
    }

    public void akcija() {
        System.out.println(naslov);
    }

    public String toString() {
        return "OBAVJ: " + naslov + " " + opis;
    }
}