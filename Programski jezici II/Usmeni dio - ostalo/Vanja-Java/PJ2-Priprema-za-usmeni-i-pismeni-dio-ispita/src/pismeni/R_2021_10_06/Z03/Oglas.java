package pismeni.R_2021_10_06.Z03;

public class Oglas {
    public String naziv;
    public String opisPosla;
    public String datum;
    public int trajanje;
    public int plata;
    public int iskustvo;
    public String grad;
    public Kategorija kategorija;

    public Oglas(String n, String o, String d, int t, int p, int i, String g, Kategorija k) {
        naziv = n;
        opisPosla = o;
        datum = d;
        trajanje = t;
        plata = p;
        iskustvo = i;
        grad = g;
        kategorija = k;
    }

    public String toString() {
        return naziv + " " + opisPosla + " " + datum + " " + trajanje + "  " + iskustvo + " " + grad + " " + kategorija + " " + plata;
    }
}