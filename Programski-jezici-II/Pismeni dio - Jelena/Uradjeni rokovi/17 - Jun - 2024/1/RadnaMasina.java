
class RadnaMasina{
	int id;
	static int ID = 1;
	String model;
	boolean status;
	Senzor[] senzori;
	boolean pokrenutiSenzori;
	
	public RadnaMasina(String model, Senzor[] senzori){
		this.model = model;
		this.senzori = senzori;
		id = ID++;
		status = false;
		pokrenutiSenzori = false;
	}
	
	public void upali(){
		this.status = true;
		if(!pokrenutiSenzori){
				for(Senzor s : senzori){
					s.start();
				}
				pokrenutiSenzori = true;
				System.out.println("Masina " + model + " je POCELA SA RADOM.");
			}
	}
	
	public void ugasi(){
		this.status = false;
		System.out.println("Masina " + model + " ZAVRSAVA SA RADOM.");
		if(pokrenutiSenzori){
			try{
				for(Senzor s : senzori){
					s.radi = false;
					s.join();
				}
			}catch(InterruptedException e){
				System.out.println("GRESKA pri zaustavljanju masine " + model);
			}
		}
		System.out.println("Masina " + model + " je ZAVRSILA SA RADOM.");
	}
}