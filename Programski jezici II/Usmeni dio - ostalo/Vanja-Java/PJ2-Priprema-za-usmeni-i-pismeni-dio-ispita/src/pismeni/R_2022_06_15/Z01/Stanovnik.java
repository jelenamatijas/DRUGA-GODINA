package pismeni.R_2022_06_15.Z01;

public abstract class Stanovnik extends Thread {

    public String ime;
    public String prezime;
    public String jmb;

    public Stanovnik(String ime, String prezime, String jmb) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmb = jmb;

    }

    public Stanovnik(String ime, String prezime, String jmb, int plata) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmb = jmb;
    }
}