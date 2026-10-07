import java.util.*;

class Film{
	String naziv;
	ArrayList<Glumac> glumci;
	int godina;
	Zanr zanr;
	int trajanje;
	double budzet;
	
	static int id = 1;
	
	public Film(){
		naziv = "Naziv_" + id;
		glumci = new ArrayList<>();
		for(int i=0; i<5; i++){
			glumci.add(new Glumac());
		}
		godina = Main.rand.nextInt(26) + 2000;
		if(id % 4 == 0){
			zanr = Zanr.AKCIJA;
		}else if(id % 4 ==1){
			zanr = Zanr.KOMEDIJA;
		}else if(id % 4 == 2){
			zanr = Zanr.SCIFI;
		}else{
			zanr = Zanr.ROMANTIKA;
		}
		trajanje = Main.rand.nextInt(60, 180);
		budzet = Main.rand.nextDouble(1000,1000000);
		id++;
	}
	
	@Override
	public String toString(){
		String s = "\n";
		for(Glumac g : glumci){
			s += g + "\n";
		}
		return "Film -> " + naziv + s + "\tGodina: " + godina + " Zanr: " + zanr + " Trajanje: " + trajanje + " Budzet: " + budzet;
	}
	
	public enum Zanr{
		AKCIJA, KOMEDIJA, SCIFI, ROMANTIKA;
	}
}