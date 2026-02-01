class Proizvod implements Element{
	String sifra, naziv;
	double kolicina, cijena;
	
	Proizvod(String s, String n, double k, double c){
		sifra = s;
		naziv = n;
		kolicina = k;
		cijena = c;
	}
	
	@Override
	public String toString(){
		return sifra +" "+naziv+" "+ kolicina +" "+ cijena;
	}
}