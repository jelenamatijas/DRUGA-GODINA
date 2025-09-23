import java.util.*;

public class Nit extends Thread {
	
	int id;
	Random random = new Random();
	
	private static int redniBroj = 1;
	
	public Nit() {
		id = redniBroj++;
	}
	
	@Override
	public void run() {
		boolean obradaOdPocetka = random.nextInt(2) == 0;
		
		while (!Main.studenti.isEmpty()) {
			if (Main.topStudenti.size() == 2) {
				break;
			}
			
			boolean uzelaStudenta = false;
			if (Main.random.nextInt(100) < 2) {
				uzelaStudenta = true;
				Student student;
				if (obradaOdPocetka) {
					student = Main.studenti.pollFirst();
				} else {
					student = Main.studenti.pollLast();
				}
				
				Main.topStudenti.add(student);
				
				System.out.println(this + (uzelaStudenta ? (" uzela " + student) : " nije uzela studenta"));
				
				try {
					sleep(10000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public String toString() {
		return "Nit{id=" + id + "}";
	}
}