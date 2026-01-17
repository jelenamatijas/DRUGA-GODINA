import java.util.*;
import java.util.concurrent.*;

public class Main {
	
	public static Random random = new Random();
	public static Object lockObject = new Object();
	public static Map<Developer, List<Zadatak>> zadaciMapa = new HashMap<>();
	
	public static BlockingQueue<Zadatak> zadaci = new PriorityBlockingQueue<>(100, Comparator.comparing(zadatak -> zadatak.prioritet, Comparator.reverseOrder()));
	
	public static final int BROJ_SPRINTOVA = 3;
	
	public static void main(String[] args) {
		
		List<Clan> clanoviTima = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			clanoviTima.add(new Developer());
		}
		
		clanoviTima.add(new ScrumMaster());
		clanoviTima.add(new ProductOwner());
		
		for (Clan clan : clanoviTima) {
			clan.start();
		}
		
		try {
			for (Clan clan : clanoviTima) {
				clan.join();
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
	}
}