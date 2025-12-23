package model;

import java.util.ArrayList;

public class Faktura implements ParserInterface<Faktura> {
    private String sifra;
    private String nazivKupca;
    private double ukupanIznos;
    private ArrayList<FakturaStavka> stavke = new ArrayList<>();

    public Faktura() {

    }

    public Faktura(String sifra, String nazivKupca, double ukupanIznos) {
        this.sifra = sifra;
        this.nazivKupca = nazivKupca;
        this.ukupanIznos = ukupanIznos;
    }

    @Override
    public Faktura parse(String s) {
        String[] parts = s.split(",");
        return new Faktura(parts[1], parts[2], Double.parseDouble(parts[3]));
    }

    /**
     * @return the sifra
     */
    public String getSifra() {
        return sifra;
    }

    /**
     * @return the nazivKupca
     */
    public String getNazivKupca() {
        return nazivKupca;
    }

    /**
     * @return the ukupanIznos
     */
    public double getUkupanIznos() {
        return ukupanIznos;
    }

    /**
     * @return the stavke
     */
    public ArrayList<FakturaStavka> getStavke() {
        return stavke;
    }

    /**
     * @param sifra the sifra to set
     */
    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    /**
     * @param stavke the stavke to set
     */
    public void setStavke(ArrayList<FakturaStavka> stavke) {
        this.stavke = stavke;
    }

    /**
     * @param nazivKupca the nazivKupca to set
     */
    public void setNazivKupca(String nazivKupca) {
        this.nazivKupca = nazivKupca;
    }

    /**
     * @param ukupanIznos the ukupanIznos to set
     */
    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    @Override
    public String toString() {
        return this.sifra;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getSifra().equals(((Faktura) obj).getSifra());
    }
}
