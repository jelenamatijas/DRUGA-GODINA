package model;

import java.util.ArrayList;

public class Narudzbenica implements ParserInterface<Narudzbenica> {
    private String sifra;
    private String datumKupovine;
    private ArrayList<NarudzbenicaStavka> stavke = new ArrayList<>();

    public Narudzbenica() {

    }

    public Narudzbenica(String sifra, String datumKupovine) {
        this.sifra = sifra;
        this.datumKupovine = datumKupovine;
    }

    @Override
    public Narudzbenica parse(String s) {
        String[] parts = s.split(",");
        return new Narudzbenica(parts[1], parts[2]);
    }

    /**
     * @return the sifra
     */
    public String getSifra() {
        return sifra;
    }

    /**
     * @return the datumKupovine
     */
    public String getDatumKupovine() {
        return datumKupovine;
    }

    /**
     * @return the stavke
     */
    public ArrayList<NarudzbenicaStavka> getStavke() {
        return stavke;
    }

    /**
     * @param sifra the sifra to set
     */
    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    /**
     * @param datumKupovine the datumKupovine to set
     */
    public void setDatumKupovine(String datumKupovine) {
        this.datumKupovine = datumKupovine;
    }

    /**
     * @param stavke the stavke to set
     */
    public void setStavke(ArrayList<NarudzbenicaStavka> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String toString() {
        return this.sifra;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getSifra().equals(((Narudzbenica) obj).getSifra());
    }
}
