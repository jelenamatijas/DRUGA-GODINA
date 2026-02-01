class Vozac extends Radnik implements PrevozInterface{
	static int id=1;
	Vozac(){
		super("Ime_Vozac_" + id, "Prezime_Vozac_" + id);
		id++;
	}
	
	@Override
	public void akcija(){
		System.out.println(this + " prevozi " + predmet);
	}
	
	@Override
	public String toString(){
		return ime + " " + prezime + " " + godinaRodjenja;
	}
}