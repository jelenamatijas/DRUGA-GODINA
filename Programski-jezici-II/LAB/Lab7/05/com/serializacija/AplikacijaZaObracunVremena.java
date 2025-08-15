package com.serializacija;

import java.util.Scanner;
import java.io.PrintWriter;

public class AplikacijaZaObracunVremena {

	/**
	 * @param args
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		long startTime = System.currentTimeMillis();

		ProracunVremena pv = new ProracunVremena();
		pv.sviZaposleni();
		System.out.println("Unesite 1 ukoliko niste registrovan zaposleni, u suprotnom unesite vase korisnicko ime!");
		Scanner ulaz = new Scanner(System.in);
		String temp = ulaz.nextLine();
		Zaposleni korisnik = new Zaposleni();
		
		if ("1".equals(temp)) {
			korisnik = Zaposleni.unosZaposlenog();
			pv.unosZaposlenog(korisnik);
		} else {
			korisnik = pv.ocitajZaposlenog(temp);
			System.out.println("Dobro dosli nazad korisnice " + korisnik.getKorisnickoIme());
		}
		System.out.println("Vase trenutno vrijeme koristenja je - " + korisnik.getBrojSati());

		System.out.println("Za izlaz iz APLIKACIJE unesite KRAJ!");
		String tempUlaz = ulaz.nextLine();
		
		if ("PAUZA".equals(tempUlaz)) {
			startTime += System.currentTimeMillis() + korisnik.getBrojSati();
			int bla = (int) (startTime / 3600000);
			korisnik.setBrojSati(bla);
			pv.unosZaposlenog(korisnik);
		} else if ("KRAJ".equals(tempUlaz)) {
			try {
				PrintWriter out = new PrintWriter("./zaposleni/" + korisnik.getKorisnickoIme() + ".txt");
				out.println(korisnik.getIme() + " " + korisnik.getPrezime() + " broj sati: " + korisnik.getBrojSati());
				out.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		ulaz.close();
	}

}
