package com.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class FTPUtilities {
	private static String listaDokumenata = "";

	public static String getListaDokumenata() {
		return listaDokumenata;
	}

	public static void setListaDokumenata(String listaDokumenata) {
		FTPUtilities.listaDokumenata = listaDokumenata;
	}

	// metoda za provjeru korisnika
	public static String checkUser(String message, ArrayList<User> users) {
		// message - USER student PASS student
		String ok = "332"; // NOK
		String[] tmp = message.split(" ");
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equals(tmp[1])
					&& users.get(i).getPassword().equals(tmp[3])) {
				ok = "230"; // OK
				break;
			}
		}
		return ok;
	}

	// metoda za vracanje liste fajlova
	public static void listaFajlova(String putanja) {
		File novi = new File(putanja);
		String[] lista = novi.list();

		for (int i = 0; i < lista.length; i++) {
			File temp = new File(putanja + "\\" + lista[i]);
			if (temp.isDirectory()) {
				listaDokumenata += "DIR " + lista[i] + "******";
				listaFajlova(putanja + "\\" + lista[i]);
			} else {
				listaDokumenata += "FILE " + lista[i] + "******";
			}
		}
	}

	// metoda za kreiranje novog foldera
	public static String kreirajFolder(String ime) {
		File folder = new File(ime);
		boolean isOK = folder.mkdir();
		String chek = "";
		if (isOK) {
			chek = "257";
		} else {
			chek = "451";
		}
		return chek;
	}

	// ocitavanje sadrzaja fajla
	public static String ocitajFajl(String ime) throws Exception {

		String tmp = "", line = "";
		File fileRead = new File(ime);
		if (fileRead.exists()) {
			BufferedReader citac = new BufferedReader(new FileReader(fileRead));
			while ((line = citac.readLine()) != null) {
				tmp += line;
			}
		} else {
			tmp = "550";
		}
		return tmp;
	}
}
