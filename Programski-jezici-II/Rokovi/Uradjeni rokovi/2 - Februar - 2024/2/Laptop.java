class Laptop{
	String naziv, sifra;
	int kolicina;
	double cijena;
	
	public Laptop(){super();}
	
	@Override
	public String toString(){
		return "Naziv: " + " " + naziv + " Sifra: " + sifra + " Kolicina: " + kolicina + " Cijena: " + cijena; 
	}
}