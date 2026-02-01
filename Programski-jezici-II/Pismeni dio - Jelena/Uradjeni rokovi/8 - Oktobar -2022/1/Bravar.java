class Bravar extends Radnik implements OtvoriInterface{
	static int id=1;
	Bravar(){
		super("Ime_Bravara_" + id, "Prezime_Bravara_" + id);
		id++;
	}
	
	@Override
	public void akcija(){
		System.out.println(this + " otvara " + predmet);
		
		// Logika oštećenja - 6% za kutije
		int sansa = Main.rand.nextInt(100);
		if(sansa < 6){
			predmet.statusOstecenja = true;
			System.out.println("  OSTECENJE prilikom otvaranja! " + predmet);
		}
	}
	
	@Override
	public String toString(){
		return ime + " " + prezime + " " + godinaRodjenja;
	}
}