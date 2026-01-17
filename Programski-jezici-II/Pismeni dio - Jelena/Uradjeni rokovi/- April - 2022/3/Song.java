class Song{
	String naziv;
	String izvodjac;
	int godina;
	int duzina;
	
	Song(String n, String i, int g, int d){
		naziv = n;
		izvodjac = i;
		godina = g;
		duzina = d;
	}
	
	@Override
	public String toString(){
		return naziv + " " + izvodjac + " " + godina  + " " + duzina;
	}
}