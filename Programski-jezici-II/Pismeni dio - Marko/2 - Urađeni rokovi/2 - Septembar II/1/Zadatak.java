public class Zadatak {
	
	String naslov;
	String opis;
	int prioritet;
	Vrsta vrsta;
	
	private static int redniBroj = 1;
	
	public Zadatak() {
		naslov = "naslov" + redniBroj;
		opis = "opis" + redniBroj++;
		prioritet = Main.random.nextInt(5) + 1;
		
		int randomVrsta = Main.random.nextInt(3);
		if (randomVrsta == 0) {
			vrsta = Vrsta.task;
		} else if (randomVrsta == 1) {
			vrsta = Vrsta.story;
		} else {
			vrsta = Vrsta.bug;
		}
	}
	
	@Override
	public String toString() {
		return "Zadatak{naslov=" + naslov + ", opis=" + opis + ", prioritet=" + prioritet + ", vrsta=" + vrsta + "}";
	}
	
	enum Vrsta {
		task, story, bug;
	}
}