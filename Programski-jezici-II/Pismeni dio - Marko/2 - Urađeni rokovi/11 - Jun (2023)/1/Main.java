import java.util.*;
import java.util.concurrent.*;

public class Main {
	
	public static Random random = new Random();
	public static Object lockObject = new Object();
	
	public static ArrayBlockingQueue<Osoba> redOsoba = new ArrayBlockingQueue<>(20);
	
	public static void main(String[] args) {
		ArrayList<FizickoLice> fizickaLica = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			fizickaLica.add(new FizickoLice());
		}
		
		ArrayList<PravnoLice> pravnaLica = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			pravnaLica.add(new PravnoLice());
		}
		
		ArrayList<Osoba> sveOsobe = new ArrayList<>();
		sveOsobe.addAll(fizickaLica);
		sveOsobe.addAll(pravnaLica);
		Collections.shuffle(sveOsobe);
		
		for (int i = 0; i < 20; i++) {
			redOsoba.add(sveOsobe.get(i));
		}
		
		Salter salter1 = new Salter();
		Salter salter2 = new Salter();
		
		salter1.start();
		salter2.start();
		
		try {
			salter1.join();
			salter2.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}