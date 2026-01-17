import java.util.*;

public class RadnaMasina extends Thread {
	
	public int serijskiBroj;
	public String model;
	public boolean status;
	public ArrayList<Senzor> senzori = new ArrayList<>();
	
	private static int redniBroj = 1;
	
	public RadnaMasina() {
		this.serijskiBroj = redniBroj;
		this.model = "Model" + redniBroj;
		this.status = false;
		redniBroj++;
	}
	
	public void upali() {
		this.status = true;
	}
	
	public void ugasi() {
		this.status = false;
	}
	
	@Override
	public void run() {
		this.upali();
		System.out.println(this + " POCELA SA RADOM!");
		
		for (int i = 0; i < this.senzori.size(); i++) {
			this.senzori.get(i).upali();
			this.senzori.get(i).start();
		}
		
		while (this.status) {
			try {
				sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		
		for (int i = 0; i < this.senzori.size(); i++) {
			this.senzori.get(i).ugasi();
		}
		
		System.out.println(this + " ZAVRSILA SA RADOM!");
	}
	
	@Override
	public String toString() {
		return "RadnaMasina{serijskiBroj=" + this.serijskiBroj + ", model=" + this.model + ", status=" + this.status + ", senzori=" + this.senzori + "}";
	}
}