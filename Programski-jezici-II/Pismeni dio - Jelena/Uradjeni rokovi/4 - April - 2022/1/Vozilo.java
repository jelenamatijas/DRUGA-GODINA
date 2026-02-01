class Vozilo implements Comparable{
	Vozac vozac;
	int brojPutnika;
	int cijena;
	int id;
	Motor motor;
	static int ID = 1;
	
	Vozilo(){}
	
	Vozilo(int brP, int c){
		id = ID++;
		vozac = new Vozac();
		brojPutnika = brP;
		cijena = c;
		motor = new Motor();
	}
	
	@Override
	public int compareTo(Object o){
		Vozilo v = (Vozilo )o;
		if(id > v.id){
			return 1;
		}
		return -1;
	}
	
	@Override
	public String toString(){
		return "Vozac: " + vozac + " - Broj putnika: " + brojPutnika + " - Cijena: " + cijena + " - Motor: " + motor;
	}
}