import java.util.*;

class Univerzitet{
	String naziv;
	Map<String, Fakultet> fakulteti;
	
	Univerzitet(String n){
		naziv = n;
		fakulteti = new HashMap<>();
	}
	
	@Override
	public String toString(){
		String s = "";
		for(String f:fakulteti.keySet()){
			s +="\t\n" + fakulteti.get(f);
		}
		return naziv + s;
	}
}