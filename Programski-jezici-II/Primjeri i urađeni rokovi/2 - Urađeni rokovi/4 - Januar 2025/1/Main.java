import java.util.Random;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	
	public static Object[][] mapa = new Object[8][30];
	
	public static Random random = new Random();
	public static int brojOsobaKojeSuDobilePosao = 0;
	public static ArrayList<Osoba> osobeKojeNisuDobilePosao = new ArrayList<>();
	
	private static void postavljanjeOglasa(int brojOglasa) {
		for (int i = 0; i < brojOglasa; ) {
			int randomX = random.nextInt(8);
			int randomY = random.nextInt(30);
			if (mapa[randomX][randomY] == null) {
				mapa[randomX][randomY] = new Oglas();
				i++;
			}
		}
	}
	
	public static void main(String[] args) {
		if (args.length < 1 || Integer.parseInt(args[0]) < 30) {
			System.out.println("args.length < 1 ILI Integer.parseInt(args[0]) < 30");
			return;
		}
		
		int brojOglasa = Integer.parseInt(args[0]);
		postavljanjeOglasa(brojOglasa);
		
		ArrayList<Osoba> osobe = new ArrayList<>();
		osobe.add(new Programer(0));
		osobe.add(new Programer(1));
		
		/*
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= 90; i++) {
			sb.append("Rijec" + i);
		}
		osobe.get(0).biografija = sb.toString();
		*/
		
		osobe.add(new TuristickiVodic(2));
		osobe.add(new TuristickiVodic(3));
		
		osobe.add(new Istoricar(4));
		osobe.add(new Istoricar(5));
		
		osobe.add(new Novinar(6));
		osobe.add(new Novinar(7));
		
		for (Osoba osoba : osobe) {
			osoba.start();
		}
		
		try {
			for (Osoba osoba : osobe) {
				osoba.join();
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		System.out.println("Broj osoba koje su dobile posao: " + brojOsobaKojeSuDobilePosao);
		System.out.println("Procenat osoba koje su dobile posao: " + (double) brojOsobaKojeSuDobilePosao / 8.0 * 100.0 + "%");
		
		int suma = 0;
		for (Osoba osoba : osobeKojeNisuDobilePosao) {
			suma += osoba.godineStarosti;
		}
		System.out.println("Prosjecna starost osoba koje nisu dobile posao: " + (suma / osobeKojeNisuDobilePosao.size()));
		
		try (PrintWriter pw = new PrintWriter("SIM-" + System.currentTimeMillis() + ".txt")) {
			for (Osoba osoba : osobeKojeNisuDobilePosao) {
				pw.write(osoba.toString() + "\n");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}