class Knjiga{
	
	String naslov, autor;
	int brojStranica, godinaIzdavanja;
	static int id = 1;
	
	public Knjiga(){
		naslov = "Naslov" + id;
		autor = "Autor" + id;
		brojStranica = Main.rand.nextInt(200, 500);
		godinaIzdavanja = Main.rand.nextInt(126) + 1900;
		id++;
	}
	
	@Override
	public String toString(){
		return "Knjiga:" + naslov + " " + autor + " Broj stranica: " + brojStranica + " Godina izdavanja: " + godinaIzdavanja;
	}
}