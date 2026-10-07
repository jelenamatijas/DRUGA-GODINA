class Putnik{
	String ime, prezime;
	static int id = 1;
	public Putnik(){
		ime = "Ime" + id;
		prezime = "Prezime" + id;
		id++;
	}
	
	@Override
	public String toString(){
		return "Putnik{ " + ime + " " + prezime + "}";
	}
}