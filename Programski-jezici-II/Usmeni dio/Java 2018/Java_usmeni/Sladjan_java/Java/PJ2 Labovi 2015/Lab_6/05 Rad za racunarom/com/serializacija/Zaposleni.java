package com.serializacija;

import java.io.Serializable;
import java.util.Scanner;

public class Zaposleni implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ime;
	private String prezime;
	private String korisnickoIme;
	private int brojSati = 0;

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

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public int getBrojSati() {
		return brojSati;
	}

	public void setBrojSati(int brojSati) {
		this.brojSati = brojSati;
	}

	public static Zaposleni unosZaposlenog(){
		Zaposleni zap=new Zaposleni();
		Scanner ulaz=new Scanner(System.in);
		System.out.println("Unesite ime: ");
		zap.setIme(ulaz.nextLine());
		System.out.println("Unesite prezime: ");
		zap.setPrezime(ulaz.nextLine());
		System.out.println("Unesite korisnicko ime: ");
		zap.setKorisnickoIme(ulaz.nextLine());
		return zap;
	}
}
