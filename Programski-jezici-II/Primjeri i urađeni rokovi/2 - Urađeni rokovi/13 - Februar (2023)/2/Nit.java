import java.io.*;
import java.util.*;

public class Nit extends Thread {
	
	int id;
	ArrayList<Integer> dobitnaKombinacija = new ArrayList<>();
	Random random = new Random();
	
	private static int redniBroj = 1;
	
	public Nit() {
		id = redniBroj++;
	}
	
	public void napraviDobitnuKombinaciju() {
		for (int i = 0; i < 5; ) {
			int randomBroj = random.nextInt(10);
			if (!dobitnaKombinacija.contains(randomBroj)) {
				dobitnaKombinacija.add(randomBroj);
				i++;
			}
		}
		//Collections.sort(dobitnaKombinacija);
		dobitnaKombinacija.sort(Comparator.comparingInt(broj -> broj));
	}
	
	public void azurirajFajl(int trenutniBrojac, String pobjednickeNiti) {
		try (PrintWriter pw = new PrintWriter(new FileWriter("tombola.txt"))) {
			dobitnaKombinacija.forEach(broj -> {
				pw.print(broj + " ");
			});
			pw.println();
			pw.println((trenutniBrojac + 1));
			String[] idNiti = pobjednickeNiti.trim().split(" ");
			for (String nitId : idNiti) {
				pw.print(nitId + " ");
			}
			pw.print(this.id + " ");
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom povecavanja brojaca");
			ex.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		this.napraviDobitnuKombinaciju();
		
		synchronized (Main.lockObject) {
			try (BufferedReader br = new BufferedReader(new FileReader("tombola.txt"))) {
				ArrayList<Integer> tombolaKombinacija = new ArrayList<>();
				String prviRed = br.readLine().trim();
				String[] brojevi = prviRed.split(" ");
				for (String broj : brojevi) {
					tombolaKombinacija.add(Integer.parseInt(broj));
				}
				int trenutniBrojac = Integer.parseInt(br.readLine().trim());
				String pobjednickeNiti = br.readLine();
				if (pobjednickeNiti != null) {
					pobjednickeNiti.trim();
				} else {
					pobjednickeNiti = "";
				}
				//System.out.println(this + " " + dobitnaKombinacija);
				if (this.dobitnaKombinacija.containsAll(tombolaKombinacija)) {
					synchronized (Main.lockObject) {
						Main.brojNitiKojeSuPobjedile++;
					}
					System.out.println(this + " pobjedila " + dobitnaKombinacija);
					this.azurirajFajl(trenutniBrojac, pobjednickeNiti);
				}
			} catch (IOException ex) {
				System.out.println("GRESKA prilikom citanja dobitne kombinacije");
				ex.printStackTrace();
			}
		}
	}
	
	@Override
	public String toString() {
		return "Nit{id=" + id + "}";
	}
}