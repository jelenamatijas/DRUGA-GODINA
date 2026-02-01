import java.util.*;

public class Radnik extends Thread {
	
	String ime;
	String prezime;
	int godinaRodjenja;
	
	Random random = new Random();
	
	private static int redniBroj = 1;
	
	public Radnik() {
		ime = "ime" + redniBroj;
		prezime = "prezime" + redniBroj++;
		godinaRodjenja = Main.random.nextInt(6) + 1995;
	}
	
	@Override
	public void run() {
		
		while (!this.isInterrupted()) {
			synchronized (Main.stopObject) {
				if (Main.stop) {
					try {
						Main.stopObject.wait();
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
			
			Predmet predmet = Main.proizvodaTraka.poll();
			if (this instanceof VariocInterface) {
				if (random.nextInt(100) < predmet.vjerovatnocaOstecenja) {
					predmet.statusOstecenja = true;
				}
				System.out.println(this + " vari " + predmet);
				
				try {
					sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				
			} else if (this instanceof BravarInterface) {
				if (random.nextInt(100) < predmet.vjerovatnocaOstecenja) {
					predmet.statusOstecenja = true;
				}
				System.out.println(this + " bravari " + predmet);
				
				try {
					sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
			synchronized (Main.lockObject) {
				Main.skladiste.add(predmet);
			}
			System.out.println(this + " je vratio " + predmet + " u skladište");
		}
		//System.out.println()
		
		synchronized (Main.lockObject) {
			Main.lockObject.notifyAll();
		}
	}
}