package model;

public class NarudzbenicaStavka implements ParserInterface<NarudzbenicaStavka> {
    public String sifra;
    public String nazivProizvoda;
    public double kolicina;
    public double cijena;

    public NarudzbenicaStavka() {

    }

    public NarudzbenicaStavka(String sifra, String nazivProizvoda, double kolicina, double cijena) {
        this.sifra = sifra;
        this.nazivProizvoda = nazivProizvoda;
        this.kolicina = kolicina;
        this.cijena = cijena;
    }

    @Override
    public NarudzbenicaStavka parse(String s) {
        String[] parts = s.split(",");
        return new NarudzbenicaStavka(parts[1], parts[2], Double.parseDouble(parts[3]), Double.parseDouble(parts[4]));
    }

    /**
     * @return the cijena
     */
    public double getCijena() {
        return cijena;
    }

    /**
     * @return the kolicina
     */
    public double getKolicina() {
        return kolicina;
    }

    /**
     * @return the nazivProizvoda
     */
    public String getNazivProizvoda() {
        return nazivProizvoda;
    }

    /**
     * @return the sifra
     */
    public String getSifra() {
        return sifra;
    }

    /**
     * @param cijena the cijena to set
     */
    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    /**
     * @param kolicina the kolicina to set
     */
    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    /**
     * @param nazivProizvoda the nazivProizvoda to set
     */
    public void setNazivProizvoda(String nazivProizvoda) {
        this.nazivProizvoda = nazivProizvoda;
    }

    /**
     * @param sifra the sifra to set
     */
    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return this.getNazivProizvoda() + " " + this.getKolicina();
    }

    public double ukupno() {
        return this.cijena * this.kolicina;
    }
}