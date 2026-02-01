import java.util.*;

class Drzava{
	Map<String, Univerzitet> univerziteti;
	
	Drzava(){
		univerziteti = new HashMap<>();
	}
	
	@Override
	public String toString(){
		String s = "";
		for(String f:univerziteti.keySet()){
			s +="\t\n" + univerziteti.get(f);
		}
		return s;
	}
}