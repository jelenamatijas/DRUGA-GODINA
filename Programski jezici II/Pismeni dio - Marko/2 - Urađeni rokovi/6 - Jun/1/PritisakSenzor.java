import java.util.*;
import java.io.*;

public class PritisakSenzor extends Senzor {
	
	// Posljednjih 5 pritisaka
	public ArrayList<Integer> pritisci = new ArrayList<>();
	
	public static File txtLogFile = new File("pritisakTxtLog.txt");
	
	public PritisakSenzor() {
		super();
		try {
			if (!txtLogFile.exists()) {
				txtLogFile.createNewFile();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public synchronized void mjeri() {
		Integer randomPritisak = Main.random.nextInt(100);
		
		this.pritisci.add(0, randomPritisak);
		this.upisiMjerenjeUFajl();
		
		if (this.pritisci.size() == 6) {
			this.pritisci.remove(5);
		}
		System.out.println("Novi izmjereni pritisak: " + randomPritisak + "Pa");
	}
	
	public void prikaziProsjek() {
		if (this.pritisci.size() == 5) {
			Integer suma = 0;
			for (Integer pritisak : this.pritisci) {
				suma += pritisak;
			}
			Double prosjecanPritisak = (double) ((double) suma / 5);
			System.out.println("Prosjecan pritisak prethodnih 5 mjerenja je: " + prosjecanPritisak + "Pa");
		} else {
			System.out.println("Nije izvrseno dovoljno mjerenja, trenutno izvrseno: " + this.pritisci.size() + " mjerenja");
		}
	}
	
	public void upisiMjerenjeUFajl() {
		try (PrintWriter pw = new PrintWriter(new FileWriter(txtLogFile, true))) {
			pw.println(this.pritisci.get(0) + "Pa\n");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "PritisakSenzor{naziv=" + this.naziv + ", status=" + this.status + ", pritisci=" + this.pritisci + "}";
	}
}