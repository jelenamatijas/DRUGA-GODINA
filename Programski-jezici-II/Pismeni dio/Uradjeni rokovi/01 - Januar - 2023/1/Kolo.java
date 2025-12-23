import java.util.List;
import java.util.ArrayList;

class Kolo extends Thread{
	static int ID = 1;
	int id;
	String liga;
	String nazivKola;
	List<Partija> partije = new ArrayList<>();
	Kolo(String liga){
		id = ID++;
		nazivKola = "Kolo_" + ID++;
		this.liga = liga;
		if(ID == 7){
			ID = 1;
		}
	}
	
	public void run(){
		System.out.println(nazivKola + "-" + liga + " je zapocelo.");
		for(Partija p : partije){
			p.start();
		}
		try{
			for(Partija p : partije){
				p.join();
			}
		}catch(Exception e){
			System.out.println(e);
		}
		System.out.println(nazivKola + "-" + liga + " je zavrsilo.");
	}
}