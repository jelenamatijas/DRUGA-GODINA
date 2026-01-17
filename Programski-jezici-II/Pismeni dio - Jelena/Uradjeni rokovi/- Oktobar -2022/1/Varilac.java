class Varilac extends Radnik implements VariInterface{
	static int id=1;
	Varilac(){
		super("Ime_Varilac_" + id, "Prezime_Varilac_" + id);
		id++;
	}
	
	@Override
	public void akcija(){
		System.out.println(this + " vari " + predmet);
		
		
		int sansa = Main.rand.nextInt(100);
		if(predmet instanceof InoxSajla){
			if(sansa < 3){
				predmet.statusOstecenja = true;
				System.out.println(" OSTECENJE prilikom varenja! " + predmet);
			}
		}
		else if(predmet instanceof CelicnaSajla){
			if(sansa < 7){
				predmet.statusOstecenja = true;
				System.out.println(" OSTECENJE prilikom varenja! " + predmet);
			}
		}
		else{
			
			if(sansa < 6){
				predmet.statusOstecenja = true;
				System.out.println("  OSTECENJE prilikom varenja! " + predmet);
			}
		}
	}
	
	@Override
	public String toString(){
		return " Varilac -> " + ime + " " + prezime + " " + godinaRodjenja;
	}
}