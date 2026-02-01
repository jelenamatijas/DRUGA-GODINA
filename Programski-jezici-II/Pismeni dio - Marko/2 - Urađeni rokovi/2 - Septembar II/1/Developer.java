import java.util.*;

public class Developer extends Clan {
	
	int trajanjeRada;
	public static boolean radi = true;
	
	public Developer() {
		super();
		trajanjeRada = Main.random.nextInt(3) + 1;
	}
	
	@Override
	public void run() {
		
		while (radi) {
			
			if (!radi) {
				break;
			}
			
			synchronized (Main.lockObject) {
				Main.zadaciMapa.clear();
			}
			
			List<Zadatak> zadaci = new ArrayList<>();
			while (Main.zadaci.peek() != null) {
				Zadatak zadatak = Main.zadaci.poll();
				System.out.println(this + " je uradio zadatak: " + zadatak);
				zadaci.add(zadatak);
				
				try {
					sleep(this.trajanjeRada * 1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
			
			synchronized (Main.lockObject) {
				Main.zadaciMapa.put(this, zadaci);
			}
			
			synchronized (Main.lockObject) {
				Main.lockObject.notifyAll();
			}
		}
	}
	
	@Override
	public String toString() {
		return "Developer{ime=" + ime + ", prezime=" + prezime + ", godineRada=" + godineRada + ", trajanjeRada=" + trajanjeRada + "s}";
	}
}