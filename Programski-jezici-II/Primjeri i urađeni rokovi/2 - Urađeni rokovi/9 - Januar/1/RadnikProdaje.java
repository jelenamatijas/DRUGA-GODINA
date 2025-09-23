public class RadnikProdaje extends Zaposleni {
	
	public RadnikProdaje() {
		super();
	}
	
	@Override
	public void run() {
		radi = true;
		System.out.println(this + " POCINJE sa radom!");
		
		int n = 1;
		int prosloVrijeme = 0;
		
		while (radi) {
			String zadatak = "Ponuda za prodaju #" + n;
			System.out.println(zadatak);
			uradjeniZadaci.add(zadatak);
			try {
				for (int i = 0; i < 3; i++) {
					sleep(1000);
					prosloVrijeme++;
					if (prosloVrijeme == vrijemePauze) {
						System.out.println(this + " OTISAO na pauzu");
						sleep(5000);
						System.out.println(this + " se VRATIO sa pauze i nastavlja rad");
					}
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			n++;
			
			if (uradjeniZadaci.size() == 10) {
				synchronized (Main.lockObject) {
					try {
						Main.lockObject.wait();
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}
	
	@Override
	public String toString() {
		return "\nRadnikProdaje{ime=" + ime + ", prezime=" + prezime + ", godineRada=" + godineRada + ", cijenaRada=" + cijenaRada + ", ponudeZaProdaju=" + uradjeniZadaci + "}";
	}
}