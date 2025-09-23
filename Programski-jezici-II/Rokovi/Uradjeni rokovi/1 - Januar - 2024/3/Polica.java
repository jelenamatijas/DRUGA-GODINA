import java.util.*;

class Polica{
	private static int ID = 1;
	int idPolice;
	ArrayList<Knjiga> knjige;
	
	public Polica(){
		idPolice = ID;
		ID++;
		knjige = new ArrayList<>();
		for(int i=0; i< (new Random()).nextInt(5)+1; i++){
			Knjiga k = new Knjiga();
			knjige.add(k);
		}
		
	}
	
	@Override
	public String toString(){
		return "\nPolica{ID police: " + idPolice + knjige.toString() + "}";
	}
	
}