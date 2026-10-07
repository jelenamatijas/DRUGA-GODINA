import java.util.*;
import java.io.*;

public class Korisnik extends Thread {
	
	public String ime;
	public String prezime;
	public int godineStarosti;
	public String oblastInteresovanja;
	public int brojClanskeKarte;
	
	public int red;
	
	public static long trenutnoVrijemeUMilisekundama = System.currentTimeMillis();
	
	private static int redniBroj = 1;
	
	private static ArrayList<Integer> clanskeKarte = new ArrayList<>();
	static {
		for (int i = 1; i <= 10; i++) {
			clanskeKarte.add(i);
		}
		Collections.shuffle(clanskeKarte);
	}
	
	public Korisnik(int red) {
		this.ime = "Ime" + redniBroj;
		this.prezime = "Prezime" + redniBroj;
		this.godineStarosti = Main.random.nextInt(25) + 1;
		
		int zanrIliOblast = Main.random.nextInt(2);
		if (zanrIliOblast == 0) {
			this.oblastInteresovanja = "Zanr" + (Main.random.nextInt(5) + 1);
		} else {
			this.oblastInteresovanja = "Oblast" + (Main.random.nextInt(5) + 1);
		}
		
		this.brojClanskeKarte = clanskeKarte.get(0);
		clanskeKarte.remove(0);
		
		this.red = red;
	}
	
	private void upisiUFajl(String naslov) {
		try (PrintWriter pw = new PrintWriter(new FileWriter("BIBLIOTEKA-" + trenutnoVrijemeUMilisekundama + ".txt", true))) {
			pw.println(this.brojClanskeKarte + "#" + "\"" + naslov + "\"");
		} catch (IOException ex) {
			System.out.println("GRESKA tokom upisa u fajl!");
		}
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			synchronized (Main.lockObject) {
				if (Main.mapa[this.red][i] instanceof Knjiga) {
					Knjiga knjiga = (Knjiga) Main.mapa[this.red][i];
					System.out.println("\n" + this + " je naisao na " + knjiga);
					if (knjiga instanceof Beletristika) {
						Beletristika beletristika = (Beletristika) knjiga;
						if (this.oblastInteresovanja.equalsIgnoreCase(beletristika.zanr)) {
							System.out.println(this + " je PODIGAO " + beletristika);
							Main.brojPodignutihKnjiga++;
							upisiUFajl(beletristika.naslov);
							Main.mapa[this.red][i] = null;
						}
					} else if (knjiga instanceof StrucnaLiteratura) {
						StrucnaLiteratura strucnaLiteratura = (StrucnaLiteratura) knjiga;
						if (this.oblastInteresovanja.equalsIgnoreCase(strucnaLiteratura.oblast)) {
							System.out.println("\n" + this + " je PODIGAO " + strucnaLiteratura);
							Main.brojPodignutihKnjiga++;
							upisiUFajl(strucnaLiteratura.naslov);
							Main.mapa[this.red][i] = null;
						}
					} else if (knjiga instanceof DjecaKnjiga) {
						DjecaKnjiga djecaKnjiga = (DjecaKnjiga) knjiga;
						try {
							if (this.godineStarosti == djecaKnjiga.preporucenaGodinaStarosti) {
								System.out.println("\n" + this + " je PODIGAO " + djecaKnjiga);
								Main.brojPodignutihKnjiga++;
								upisiUFajl(djecaKnjiga.naslov);
								Main.mapa[this.red][i] = null;
							} else {
								throw new PogresneGodineStarostiIzuzetak("Godine starosti: " + this.godineStarosti + " se ne poklapaju sa preporucenim godinama: " + djecaKnjiga.preporucenaGodinaStarosti);
							}
						} catch (PogresneGodineStarostiIzuzetak ex) {
							ex.getMessage();
						}
					}
				}
				// Potencijalno: Ispis ako korisnik nije naisao na knjigu (nema potrebe, samo komplikuje provjeru programa)
			}
			
			try {
				sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	@Override
	public String toString() {
		return "Korisnik{ime=" + this.ime + ", prezime=" + this.prezime + ", godineStarosti=" + this.godineStarosti + ", oblastInteresovanja=" + this.oblastInteresovanja + ", brojClanskeKarte=" + this.brojClanskeKarte + "}";
	}
}