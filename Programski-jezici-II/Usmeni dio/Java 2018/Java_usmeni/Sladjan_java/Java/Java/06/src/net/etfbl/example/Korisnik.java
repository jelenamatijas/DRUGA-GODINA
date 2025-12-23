package net.etfbl.example;

public class Korisnik {
	private String ime;
	private String prezime;
	private String fakultet;
	private double prosjek;


	public Korisnik() {
	
	}
	
	public Korisnik(String ime, String prezime, String fakultet, double prosjek) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.fakultet = fakultet;
		this.prosjek = prosjek;
	}

	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getFakultet() {
		return fakultet;
	}
	public void setFakultet(String fakultet) {
		this.fakultet = fakultet;
	}
	public double getProsjek() {
		return prosjek;
	}
	public void setProsjek(double prosjek) {
		this.prosjek = prosjek;
	}
	@Override
	public String toString() {
		return "Ime: " + ime + "\nPrezime: " + prezime + "\nFakultet: " + fakultet + "\nProsjek: " + prosjek;
	}
}
