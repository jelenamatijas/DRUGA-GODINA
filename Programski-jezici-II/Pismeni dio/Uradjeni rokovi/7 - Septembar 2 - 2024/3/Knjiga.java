class Knjiga{
	
	String naziv, autor;
	int brojStranica, godinaIzdavanja;
	
	static int id = 1;
	
	public Knjiga(){
		naziv = "Naziv" + id;
		autor = "Autor" +id;
		id++;
		brojStranica = Main.rand.nextInt(400) + 100;
		godinaIzdavanja = Main.rand.nextInt(26) + 2000;
	}
	
	@Override
	public String toString(){
		return "Knjiga{" + naziv + " " + autor + " Broj stranica: " + brojStranica + " Godina izdavanja: " + godinaIzdavanja + "}"; 
	}
}