package com.serializacija;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ProracunVremena {

	// smjestanje objekta Zaposleni u fajl
	public void unosZaposlenog(Zaposleni zaposleni) {
		try {
			FileOutputStream pisac = new FileOutputStream("./zaposleni/" + zaposleni.getKorisnickoIme() + ".ser");
			ObjectOutputStream upisObjekta = new ObjectOutputStream(pisac);
			upisObjekta.writeObject(zaposleni);
			upisObjekta.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fajl nije pronadjen!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IOException!");
		}

	}

	// ocitavanje objekta Zaposleni iz fajla
	public Zaposleni ocitajZaposlenog(String korisnickoIme) throws ClassNotFoundException {
		FileInputStream citac;
		Zaposleni zaposleni = new Zaposleni();
		try {
			citac = new FileInputStream("./zaposleni/" + korisnickoIme + ".ser");

			ObjectInputStream citanjeObjekta = new ObjectInputStream(citac);
			zaposleni = (Zaposleni) citanjeObjekta.readObject();
			citanjeObjekta.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fajl nije pronadjen!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IOException!");
		}
		return zaposleni;
	}

	// izlistavanje svih zaposlenih
	public void sviZaposleni() {
		File folderZaposleni = new File("./zaposleni");
		if(!folderZaposleni.exists()) {
			folderZaposleni.mkdir();
		}
		String[] zaposleni = folderZaposleni.list();
		System.out.println("TRENUTNO ZAPOSLENI...");
		for (int i = 0; i < zaposleni.length; i++) {
			if (zaposleni[i].contains(".ser")) {
				System.out.println(zaposleni[i].substring(0, zaposleni[i].length() - 4));
			}
		}
	}
}
