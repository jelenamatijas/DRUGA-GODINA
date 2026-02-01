package pismeni.R_2022_06_15.Z01;

public class Radnik extends Stanovnik {

    public int plata;

    public Radnik(String ime, String prezime, String jmb, int plata) {
        super(ime, prezime, jmb);
        this.plata = plata;
    }

    @Override
    public String toString(){
        return ime + " " + prezime + " JMB: " + jmb + " PLATA: " + plata;
    }
}