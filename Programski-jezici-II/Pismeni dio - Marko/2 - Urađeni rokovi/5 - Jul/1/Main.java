import java.util.*;
import java.io.*;

public class Main {
	
	public static Random random = new Random();
	
	public static ArrayList<Vozilo> vozilaSaBaterijom = new ArrayList<>();
	public static ArrayList<Vozilo> vozilaBezBaterije = new ArrayList<>();
	
	public static void main(String[] args) {
		PoolPutnikaThread pool = new PoolPutnikaThread();
		pool.start();
		
		ArrayList<Vozilo> vozila = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			vozila.add(new TramvajSaVagonima());
			vozila.add(new TramvajBezVagona());
			vozila.add(new GradskiAutobus());
			vozila.add(new PrigradskiAutobus());
		}
		
		for (Vozilo vozilo : vozila) {
			vozilo.start();
		}
		
		for (Vozilo vozilo : vozila) {
            try {
                vozilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		
		pool.interrupt();
		
		try (PrintWriter pw = new PrintWriter(new FileWriter("vozila_sa_baterijom.txt"))) {
			for (Vozilo vozilo : vozilaSaBaterijom) {
				pw.println(vozilo);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		try (PrintWriter pw = new PrintWriter(new FileWriter("vozila_bez_baterije.txt"))) {
			for (Vozilo vozilo : vozilaBezBaterije) {
				pw.println(vozilo);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}