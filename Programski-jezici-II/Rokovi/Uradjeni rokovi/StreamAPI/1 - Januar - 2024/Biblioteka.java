import java.util.*;

class Biblioteka{
	ArrayList<Sekcija> sekcije;
	
	public Biblioteka(){
		sekcije = new ArrayList<>();
		for(int i=0; i<3; i++){
			sekcije.add(new Sekcija());
		}
	}
	
	@Override
	public String toString(){
		String k = "";
		for(Sekcija sekcija : sekcije){
			k+= "\t" + sekcija + "\n";
		}
		return "Biblioteka -> \n\t" + k;
	}
}