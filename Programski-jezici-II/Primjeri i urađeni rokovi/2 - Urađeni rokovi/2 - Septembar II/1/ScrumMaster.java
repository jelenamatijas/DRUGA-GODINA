import java.io.*;
import java.util.*;

public class ScrumMaster extends Clan {
	
	public ScrumMaster() {
		super();
	}
	
	public synchronized void dodajZadatke() {
		for (int i = 0; i < 5; i++) {
			Main.zadaci.add(new Zadatak());
		}
		System.out.println(this + " je dodao zadatke!");
	}
	
	public void upisiUTxtFajl(int i) {
		synchronized (Main.lockObject) {
			try (PrintWriter pw = new PrintWriter(new FileWriter("sprint_" + i + ".txt"))) {
				for (Map.Entry<Developer, List<Zadatak>> entry : Main.zadaciMapa.entrySet()) {
					pw.println(entry.getKey() + ": " + entry.getValue());
				}
			} catch (IOException ex) {
				System.out.println("GRESKA prilikom upisa u txt fajl");
				return;
			}
			
		}
	}
	
	@Override
	public void run() {
		int i = 0;
		while (i < 3) {
			this.dodajZadatke();
			
			synchronized (Main.lockObject) {
				try {
					Main.lockObject.wait();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
			this.upisiUTxtFajl(i);
			i++;
		}
		
		synchronized (Main.lockObject) {
			Developer.radi = false;
		}
		
	}
	
	@Override
	public String toString() {
		return "ScrumMaster{ime=" + ime + ", prezime=" + prezime + ", godineRada=" + godineRada + "}";
	}
}