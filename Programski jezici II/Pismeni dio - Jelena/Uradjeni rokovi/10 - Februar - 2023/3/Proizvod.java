class Proizvod{
	String naziv;
	double cijena;
	Kategorija kategorija;
	
	public Proizvod(String naziv, double cijena, Kategorija kategorija){
		this.naziv = naziv;
		this.cijena = cijena;
		this.kategorija = kategorija;
	}
	
	@Override
	public String toString(){
		return naziv + " " + cijena + " " + kategorija;
	}
}