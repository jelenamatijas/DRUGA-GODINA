public abstract class Senzor extends Thread {
	
	public String naziv;
	public boolean status;
	
	private static int redniBroj = 1;
	
	public Senzor() {
		this.naziv = "Naziv" + redniBroj++;
		this.status = false;
	}
	
	public void upali() {
		this.status = true;
	}
	
	public void ugasi() {
		this.status = false;
	}
	
	public abstract void mjeri();
	
	@Override
	public void run() {
		System.out.println(this + " POCEO SA RADOM!");
		
		while (this.status) {
			this.mjeri();
			try {
				sleep(500);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		
		System.out.println(this + " ZAVRSIO SA RADOM!");
	}
}