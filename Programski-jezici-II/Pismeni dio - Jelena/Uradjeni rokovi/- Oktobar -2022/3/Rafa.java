import java.util.*;

class Rafa{
	Map<Artikl, Integer> artikli;
	static int ID=1;
	String oznaka;
	
	Rafa(){
		artikli = new HashMap<>();
		oznaka ="Rafa_" + ID++;
	}
	
	@Override
	public String toString(){		
		return oznaka + " Artikli:\n" +artikli;
	}
}