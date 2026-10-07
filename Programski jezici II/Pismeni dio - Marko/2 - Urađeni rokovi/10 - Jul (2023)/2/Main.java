import java.nio.file.*;
import java.io.*;
import java.util.*;

public class Main {
	
	public static Path putanjaTxtFajla;
	public static Random random = new Random();
	
	public static Object lockObject = new Object();
	
	public static void main(String[] args) {
		if (args.length != 3 || Integer.parseInt(args[0]) <= 0 || Integer.parseInt(args[1]) <= 0) {
			System.out.println("GRESKA format: java Main <broj_niti_veci_od_0> <stop_broj_veci_od_0> <naziv_txt_fajla>");
			return;
		}
		
		int brojNiti = Integer.parseInt(args[0]);
		int krajBroj = Integer.parseInt(args[1]);
		String nazivTxtFajla = args[2];
		
		putanjaTxtFajla = Paths.get(nazivTxtFajla);
		
		try {
			if (!Files.exists(putanjaTxtFajla)) {
				Files.createFile(putanjaTxtFajla);
				Files.writeString(putanjaTxtFajla, "0");
			}
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom pravljenja ili upisa vrijednosti u txt fajl");
			return;
		}
		
		
		ArrayList<Nit> niti = new ArrayList<>();
		for (int i = 0; i < brojNiti; i++) {
			niti.add(new Nit(krajBroj));
		}
		
		for (Nit nit : niti) {
			nit.start();
		}
		
		/*
		boolean sveNitiZavrsile = false;
		while (!sveNitiZavrsile) {
			for (Nit nit : niti) {
				if (nit.radi) {
					break;
				}
			}
			sveNitiZavrsile = true;
		}
		*/
		
		try {
			for (Nit nit : niti) {
				nit.join();
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		
	}
}