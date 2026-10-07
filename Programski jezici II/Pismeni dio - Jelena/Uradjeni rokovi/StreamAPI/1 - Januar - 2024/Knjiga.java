class Knjiga{
	String naziv;
	String autor;
	
	static int id = 1;
	
	public Knjiga(){
		naziv = "Naziv_" + id++;
		int x = Main.rand.nextInt(1, 11);
		autor = "Ime_" + x + " Prezime_" + x;
	}
	
	@Override
	public String toString(){
		return "Knjiga{" + naziv + " Autor:" + autor + "}";
	}
	
}