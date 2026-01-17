class Vozac{
	String ime, prezime;
	static int id = 1;
	
	Vozac(){
		ime = "IME_" + id;
		prezime = "PREZIME_" + id++;
	}
	
	@Override
	public String toString(){
		return ime + " " + prezime;
	}
}