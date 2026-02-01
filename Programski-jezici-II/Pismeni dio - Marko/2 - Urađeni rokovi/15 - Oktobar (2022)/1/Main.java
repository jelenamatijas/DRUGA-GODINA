// 4:50 PM
import java.util.concurrent.*;
import java.util.*;


public class Main {
	
	public static BlockingQueue<Predmet> skladiste = new LinkedBlockingQueue<>(40);
	public static ArrayBlockingQueue<Predmet> proizvodaTraka = new ArrayBlockingQueue<>(40);
	public static ArrayList<Radnik> radnici = new ArrayList<>();
	
	public static Object lockObject = new Object();
	public static Random random = new Random();
	
	public static Object stopObject = new Object();
	
	public static boolean stop = false;
	
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			skladiste.add(new Kutija());
			skladiste.add(new Sajla());
			skladiste.add(new Celicna());
			skladiste.add(new Inox());
		}
		
		radnici.add(new Varioc());
		radnici.add(new Bravar());
		radnici.add(new Kamiondzija());
		radnici.add(new Viljuskar());
		
		UzbunaNit uzbunaNit = new UzbunaNit();
		uzbunaNit.start();
		
		
		for (Radnik radnik : radnici) {
			radnik.start();
		}
		
		try {
			for (Radnik radnik : radnici) {
				radnik.join();
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		uzbunaNit.interrupt();
	}
}