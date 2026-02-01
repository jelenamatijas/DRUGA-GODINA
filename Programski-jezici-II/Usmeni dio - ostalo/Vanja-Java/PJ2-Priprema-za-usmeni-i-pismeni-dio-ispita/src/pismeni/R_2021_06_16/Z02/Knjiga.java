package pismeni.R_2021_06_16.Z02;


public class Knjiga {

    public String naslov;
    public String pisac;
    public int godinaIzdavanja;
    public Zanr zanr;

    public Knjiga(String naslov, String pisac, int godinaIzdavanja, Zanr zanr) {
        this.naslov = naslov;
        this.pisac = pisac;
        this.godinaIzdavanja = godinaIzdavanja;
        this.zanr = zanr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
       Knjiga knjiga = (Knjiga) o;
        return this.naslov.equals(knjiga.naslov) && (this.godinaIzdavanja == knjiga.godinaIzdavanja);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 7 * hash + naslov.hashCode();
        hash = 7 * hash + Integer.hashCode(godinaIzdavanja);
        return hash;
    }

    @Override
    public String toString() {
        return naslov + " " + pisac + " " + godinaIzdavanja + " " + zanr.toString();
    }
}