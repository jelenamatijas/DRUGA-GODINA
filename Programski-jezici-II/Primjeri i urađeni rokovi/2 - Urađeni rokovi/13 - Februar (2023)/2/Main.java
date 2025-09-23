import java.io.*;
import java.util.*;

public class Main {
	
	public static Random random = new Random();
	public static Object lockObject = new Object();
	
	public static int brojNitiKojeSuPobjedile = 0;
	
	public static void main(String[] args) {
		
		if (args.length != 1) {
			System.out.println("GRESKA format: java Main <broj_niti>");
			return;
		}
		
		try (PrintWriter pw = new PrintWriter(new FileWriter("tombola.txt"))) {
			ArrayList<Integer> dobitnaKombinacija = new ArrayList<>();
			for (int i = 0; i < 5; ) {
				int randomBroj = Main.random.nextInt(10);
				if (!dobitnaKombinacija.contains(randomBroj)) {
					dobitnaKombinacija.add(randomBroj);
					i++;
				}
			}
			//Collections.sort(dobitnaKombinacija);
			dobitnaKombinacija.sort(Comparator.comparingInt(broj -> broj));
			System.out.println(dobitnaKombinacija);
			dobitnaKombinacija.forEach(broj -> {
				pw.print(broj + " ");
			});
			pw.print("\n0");
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom upisa dobitne kombinacije");
		}
		
		int brojNiti = Integer.parseInt(args[0]);
		ArrayList<Nit> niti = new ArrayList<>();
		for (int i = 0; i < brojNiti; i++) {
			niti.add(new Nit());
		}
		
		
		for (Nit nit : niti) {
			nit.start();
		}
		
		try {
			for (Nit nit : niti) {
				nit.join();
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		System.out.println("Broj niti koje su pobjedile: " + brojNitiKojeSuPobjedile);
	}
}