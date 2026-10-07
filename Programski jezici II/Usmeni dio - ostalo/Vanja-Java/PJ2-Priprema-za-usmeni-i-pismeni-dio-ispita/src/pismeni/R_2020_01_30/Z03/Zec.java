package pismeni.R_2020_01_30.Z03;

public class Zec {

    public int rodjenje;
    public String ime;
    public int tezina;
    public OmiljenaHrana hrana;

    public Zec(int r, String i, int t, OmiljenaHrana h) {
        ime = i;
        tezina = t;
        hrana = h;
        rodjenje = r;
    }

    public String toString() {
        return ime + " " + rodjenje + " " + hrana + " " + tezina;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Zec) {
            Zec z = (Zec) o;
            return z.ime.equals(this.ime) && z.rodjenje == this.rodjenje;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 7 * hash + ime.hashCode();
        hash = 7 * hash + Integer.hashCode(rodjenje);
        return hash;
    }
}