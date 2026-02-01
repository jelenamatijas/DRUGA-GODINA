import java.util.*;
import java.util.concurrent.*;

public class Main {
	
	public static Random random = new Random();
	
	public static ConcurrentSkipListSet<Student> studenti = new ConcurrentSkipListSet<Student>(Comparator.comparingDouble(Student::getProsjecnaOcjena).reversed());
	
	public static ArrayBlockingQueue<Student> topStudenti = new ArrayBlockingQueue<>(2);
	
	public static void main(String[] args) {
		for (int i = 0; i < 2000; i++) {
			studenti.add(new Student());
		}
		
		ArrayList<Nit> niti = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			niti.add(new Nit());
		}
		
		for (Nit nit : niti) {
			nit.start();
		}
		
		try {
			for (Nit nit : niti) {
				nit.join();
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		System.out.println("\nStudenti koji su dobili dodatni ispitni rok:");
		topStudenti.forEach(System.out::println);
	}
}