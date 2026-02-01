import java.util.*;

class Sekcija{
	private static int ID = 1;
	int idSekcije;
	ArrayList<Polica> police;
	
	public Sekcija(){
		idSekcije = ID;
		ID++;
		police = new ArrayList<>();
		for(int i=0; i< (new Random()).nextInt(5)+1; i++){
			Polica k = new Polica();
			police.add(k);
		}
		
	}
	
	@Override
	public String toString(){
		return "\n\nSekcija{ID sekcije: " + idSekcije + police.toString() + "}";
	}
}