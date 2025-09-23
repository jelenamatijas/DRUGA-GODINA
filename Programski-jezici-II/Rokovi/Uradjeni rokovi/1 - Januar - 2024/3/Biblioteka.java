import java.util.*;

class Biblioteka{
	ArrayList<Sekcija> sekcije;
	
	public Biblioteka(){
		sekcije = new ArrayList<>();
		for(int i=0; i< (new Random()).nextInt(5)+1; i++){
			Sekcija k = new Sekcija();
			sekcije.add(k);
		}
		
	}
	
	@Override
	public String toString(){
		return "Biblioteka{" + sekcije.toString() + "}";
	}
}