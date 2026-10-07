import java.util.*;

public class PoolPutnikaThread extends Thread {
	
	private static final int MIN_BROJ_PUTNIKA = 100;
	
	public static ArrayList<Putnik> putnici = new ArrayList<>();
	
	public PoolPutnikaThread() {
		int brojPutnika = Main.random.nextInt(10) + MIN_BROJ_PUTNIKA; // 101-110, *više od 100
		for (int i = 0; i < brojPutnika; i++) {
			putnici.add(new Putnik());
		}
	}
	
	public static synchronized ArrayList<Putnik> povuciPutnike(int brojPutnika) {
		ArrayList<Putnik> putniciZaPovlacenje = new ArrayList<>();
		for (int i = 0; i < brojPutnika; i++) {
			putniciZaPovlacenje.add(putnici.get(0));
			putnici.remove(0);
		}
		
		return putniciZaPovlacenje;
	}
	
	public static synchronized void vratiPutnike(ArrayList<Putnik> putniciZaVracanje) {
		for (int i = 0; i < putniciZaVracanje.size(); i++) {
			putnici.add(putniciZaVracanje.get(i));
		}
	}
	
	
	
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				if (putnici.size() < MIN_BROJ_PUTNIKA) {
					for (int i = 0; i < 10; i++) {
						putnici.add(new Putnik());
					}
				}
				sleep(1000);
			} catch (InterruptedException ex) {
				System.out.println("\nPoolPutnikaThread zavrsava rad!");
				break;
			}
		}
	}
}