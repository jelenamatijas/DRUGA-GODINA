package net.etfbl.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) throws Exception {
		ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
		BufferedReader br = new BufferedReader(new FileReader("korisnici.txt"));
		String linija;
		while((linija=br.readLine())!=null){
			String elementi[] = linija.split(";");
			korisnici.add(new Korisnik(elementi[0],elementi[1], elementi[2], Double.parseDouble(elementi[3])));
		}
		for (Korisnik korisnik : korisnici) {
			System.out.println(korisnik);
			System.out.println();
		}
		br.close();
	}

}
