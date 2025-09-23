class Proizvod{
	String naziv;
	double cijena, kolicina;
	
	public Proizvod(String naziv, double cijena, double kolicina){
		this.naziv = naziv;
		this.cijena = cijena;
		this.kolicina = kolicina;
	}
	
	@Override
	public String toString(){
		return "Proizvod -> " + naziv + " Kolicina: " + kolicina + " Cijena: " + cijena;
	}
}