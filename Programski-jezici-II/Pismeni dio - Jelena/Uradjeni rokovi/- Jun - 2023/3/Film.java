import java.util.*;

class Film implements Comparable<Film>{
	String naziv;
	List<Glumac> glumci;
	int godina;
	Zanr zanr;
	int trajanje;
	double budzet;
	
	static public enum Zanr{
		KOMEDIJA, DRAMA, SCIFI, TRILER;
	}
	
	static int id = 1;
	
	Film(){
		naziv = "FILM_" + id;
		glumci = new ArrayList<>();
		
		int br = Main.rand.nextInt(1,6);
		for(int i=0;i<br;i++){
			glumci.add(new Glumac());
		}
		
		godina = Main.rand.nextInt(1989, 2026);
		int x = Main.rand.nextInt(0,4);
		if(x == 0){
			zanr = Zanr.KOMEDIJA;
		}else if(x ==1 ){
			zanr = Zanr.DRAMA;
		}else if(x ==2 ){
			zanr = Zanr.SCIFI;
		}else{
			zanr = Zanr.TRILER;
		}
		
		trajanje = Main.rand.nextInt(60, 200);
		budzet = Main.rand.nextInt(500000, 1000000);
		id++;
	}
	
	@Override
	public int compareTo(Film f){
		return Double.compare(budzet, f.budzet);
	}
	
	@Override
	public String toString(){
		String g = "";
		for(Glumac gl : glumci){
			g += "\n\t\t" + gl.ime + " " + gl.prezime;
		}
		return "FILM -> " + naziv + "\n\tGodina: " + godina + " Zanr: " + zanr + "\n\tTrajanje: " + trajanje + "min Budzet: " + budzet + "\n\tGlumci: " +g;
	}
}