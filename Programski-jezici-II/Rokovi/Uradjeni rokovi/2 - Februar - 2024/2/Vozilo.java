class Vozilo{
	String naziv, sifra, opis;
	
	public Vozilo(){super();}
	
	@Override
	public String toString(){
		return "Naziv: " + naziv + " Sifra: " + sifra + " Opis: " + opis;
	}
}