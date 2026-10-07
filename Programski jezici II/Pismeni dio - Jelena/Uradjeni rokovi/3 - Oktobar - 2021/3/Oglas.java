
class Oglas{
	String naziv, opis;
	String datum;
	int vrijemeTrajanja;
	int plata;
	int godineIskustva;
	String grad;
	Kategorija kategorija;
	static int id=1;
	
	Oglas(Kategorija k){
		naziv = "Oglas_"+id;
		opis = "Opis_" +id;
		vrijemeTrajanja = Main.rand.nextInt(1, 40);
		datum = Main.rand.nextInt(1, 5) +":"+Main.rand.nextInt(1, 5) +":"+Main.rand.nextInt(2024, 2026);
		plata = Main.rand.nextInt(1000, 3000);
		godineIskustva = Main.rand.nextInt(5, 20);
		grad = "Grad_" + Main.rand.nextInt(1, 10);
		kategorija = k;
		id++;
	}
	
	enum Kategorija{
		IT, MEDICINA, EKONOMIJA, NOVINARSTVO, PRAVO;
	}
	
	@Override
	public String toString(){
		return naziv + " " + opis + " " + datum + " " + vrijemeTrajanja + " " + plata + " " + godineIskustva + " " + godineIskustva + " " + kategorija;
	}
}
