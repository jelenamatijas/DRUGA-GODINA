import java.io.*;
import java.nio.file.*;

public class Nit extends Thread {
	
	int krajBroj;
	private static boolean radi = true;
	
	public Nit(int krajBroj) {
		super();
		this.krajBroj = krajBroj;
	}
	
	public int procitajBroj() {
		try {
			String sadrzajFajla = Files.readString(Main.putanjaTxtFajla).trim();
			//System.out.println(sadrzajFajla);
			int procitanBroj = Integer.parseInt(sadrzajFajla);
			return procitanBroj;
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom citanja txt fajla (nit)");
		}
		return -100;
	}
	
	public static void umanjiBroj(int trenutniBroj) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(Main.putanjaTxtFajla.toString()));
			pw.println(trenutniBroj);
			pw.close();
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom umanjivanja i upisa tog broja (nit)");
			return;
		}
	}
	
	public static void uvecajBroj(int trenutniBroj) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(Main.putanjaTxtFajla.toString()));
			pw.println(trenutniBroj);
			pw.close();
		} catch (IOException ex) {
			System.out.println("GRESKA prilikom povecavanja i upisa tog broja (nit)");
			return;
		}
	}
	
	@Override
	public void run() {
		int procitanBroj = -1000;
		
		while (radi) {
			synchronized (Main.lockObject) {
				procitanBroj = procitajBroj();
				if (Main.random.nextInt(10) < 3) {
					procitanBroj--;
					umanjiBroj(procitanBroj);
				}
				if (Main.random.nextInt(10) < 7) {
					procitanBroj++;
					uvecajBroj(procitanBroj);
				}
				if (procitanBroj == krajBroj) {
					radi = false;
				}
			}
			
			System.out.println("TRENUTNA VRIJEDNOST BROJA: " + procitanBroj);
			
			try {
				sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}