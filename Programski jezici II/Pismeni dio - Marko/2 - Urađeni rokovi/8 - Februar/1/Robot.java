import java.io.*;

public class Robot extends Thread {
	
	String serijskiBroj;
	
	String tip; // Montazni, Cistacki, Istrazivacki
	
	int snaga;
	int autonomija;
	int visina;
	
	Senzor senzor;
	Motor motor;
	Baterija baterija;
	
	int vrijemeRada;
	
	private static int redniBroj = 1;
	
	public Robot(String tip, Senzor senzor) {
		this.serijskiBroj = "SerijskiBroj" + redniBroj;
		this.tip = tip;
		this.snaga = Main.random.nextInt(10) + 1;
		this.autonomija = 100;
		this.visina = Main.random.nextInt(3) + 1;
		this.motor = new Motor();
		this.baterija = new Baterija();
		
		vrijemeRada = Main.random.nextInt(21) + 10;
		
		redniBroj++;
	}
	
	private void upisiUTxtDatoteku() {
		try (PrintWriter pw = new PrintWriter(new FileWriter("ostecenja.txt", true))) {
			pw.println(this + " OSTECEN");
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom upisa u fajl");
		}
	}
	
	@Override
	public void run() {
		int prosloVrijeme = 0;
		while (prosloVrijeme <= vrijemeRada) {
			if (this.tip.equals("Montazni")) {
				System.out.println("\n" + this + " je u fazi MONTAZE");
			}
			else if (this.tip.equals("Cistacki")) {
				System.out.println("\n" + this + " je u fazi CISCENJA");
			}
			else {
				System.out.println("\n" + this + " je u fazi ISTRAZIVANJA");
			}
			try {
				sleep(1000);
			} catch (InterruptedException ex) {
				System.out.println("GRESKA prilikom spavanja niti");
			}
			prosloVrijeme++;
			
			synchronized (Main.random) {
				if (Main.random.nextInt(100) < 5) {
					upisiUTxtDatoteku();
				}
			}
			
			synchronized (Main.lockObject) {
				if (Main.cekanje == true) {
					try {
						Main.lockObject.wait();
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		}
		
		if (this.tip.equals("Montazni")) {
			System.out.println("\n" + this + " uspjesno uradio MONTAZE");
		} else if (this.tip.equals("Cistacki")) {
			System.out.println("\n" + this + " uspjesno OCISTIO prostor");
		} else {
			System.out.println("\n" + this + " uspjesno ISTRAZIO materiju");
		}
	}
	
	@Override
	public String toString() {
		return "Robot{serijskiBroj=" + serijskiBroj + ", tip=" + tip + ", snaga=" + snaga + ", autonomija=" + autonomija + ", visina=" + visina + ", senzor=" + senzor + ", motor=" + motor + ", baterija=" + baterija + "}";
	}
}