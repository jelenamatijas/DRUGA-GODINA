import java.util.*;

public abstract class Vozilo extends Thread {
	
	public String naziv;
	public int kapacitet;
	public int trenutniBrojPutnika;
	public int trajanjeSimulacije;
	public ArrayList<Putnik> putnici = new ArrayList<>();
	
	private static int redniBroj = 1;
	
	public Vozilo() {
		this.naziv = "Naziv" + redniBroj;
		this.kapacitet = Main.random.nextInt(11) + 10; // 10-20
		this.trenutniBrojPutnika = 0;
		this.trajanjeSimulacije = Main.random.nextInt(11) + 20; // 20-30
	}
	
	@Override
	public void run() {
		int prosloVrijeme = 0;
		while (prosloVrijeme <= trajanjeSimulacije) {
			boolean dodavanje;
			synchronized (Main.random) {
				dodavanje = Main.random.nextInt(100) < 50;
			}
			if (dodavanje) {
				ArrayList<Putnik> noviPutnici;
				if (this instanceof TramvajSaVagonimaInterface) {
					synchronized (Main.random) {
						noviPutnici = PoolPutnikaThread.povuciPutnike(Main.random.nextInt(5) + 1);
					}
				}
				else {
					synchronized (Main.random) {
						noviPutnici = PoolPutnikaThread.povuciPutnike(Main.random.nextInt(3) + 1);
					}
				}
				try {
					if (this.trenutniBrojPutnika + noviPutnici.size() < this.kapacitet) {
						this.trenutniBrojPutnika += noviPutnici.size();
						for (int i = 0; i < noviPutnici.size(); i++) {
							this.putnici.add(noviPutnici.get(i)); // Novi putnici se dodaju na kraj
						}
						System.out.println("\n" + this + "---USAO broj putnika=" + noviPutnici.size() + ", simulacija=" + prosloVrijeme + "/" + this.trajanjeSimulacije);
					}
					else {
						throw new MojIzuzetak("\n" + "IZUZETAK: U vozilu " + this + " nema mjesta za " + noviPutnici.size() + " putnika!");
					}
				} catch (MojIzuzetak ex) {
					ex.printStackTrace();
				}
			}
			else {
				ArrayList<Putnik> putniciZaVracanje = new ArrayList<>();
				int brojPutnikaZaVracanje;
				if (this instanceof TramvajSaVagonimaInterface) {
					synchronized (Main.random) {
						brojPutnikaZaVracanje = Main.random.nextInt(5) + 1;
					}
				}
				else {
					synchronized (Main.random) {
						brojPutnikaZaVracanje = Main.random.nextInt(3) + 1;
					}
				}
				try {
					if (this.trenutniBrojPutnika < brojPutnikaZaVracanje) {
						throw new MojIzuzetak("\n" + "IZUZETAK: U vozilu " + this + " treba izaci " + brojPutnikaZaVracanje + " putnika!");
					}
					else {
						for (int i = 0; i < brojPutnikaZaVracanje; i++) {
							putniciZaVracanje.add(this.putnici.get(0)); // Uzimaju se oni koji su najduze u vozilu
						}
						PoolPutnikaThread.vratiPutnike(putniciZaVracanje);
						System.out.println("\n" + this + "---IZASAO broj putnika=" + putniciZaVracanje.size() + ", simulacija=" + prosloVrijeme + "/" + this.trajanjeSimulacije);
					}
				} catch (MojIzuzetak ex) {
					ex.printStackTrace();
				}
			}
			try {
				sleep(2000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			prosloVrijeme += 2;
			
			if (this instanceof BaterijskoPunjenjeInterface) {
				BaterijskoPunjenjeInterface baterijskoVozilo = (BaterijskoPunjenjeInterface) this;
				baterijskoVozilo.smanjiBateriju(1);
			}
		}
		
		if (this instanceof BaterijskoPunjenjeInterface) {
			synchronized (Main.vozilaSaBaterijom) {
				Main.vozilaSaBaterijom.add(this);
			}
		}
		else {
			synchronized (Main.vozilaBezBaterije) {
				Main.vozilaBezBaterije.add(this);
			}
		}
	}
}