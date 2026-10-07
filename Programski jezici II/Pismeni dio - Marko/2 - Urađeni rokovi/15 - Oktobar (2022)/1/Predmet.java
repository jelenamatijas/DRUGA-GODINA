public class Predmet {
	
	int id;
	boolean statusOstecenja;
	int tezina;
	int vjerovatnocaOstecenja;
	
	private static int redniBroj = 1;
	
	public Predmet() {
		id = redniBroj++;
		statusOstecenja = false;
		tezina = Main.random.nextInt(10) + 1;
		vjerovatnocaOstecenja = 6;
	}
}