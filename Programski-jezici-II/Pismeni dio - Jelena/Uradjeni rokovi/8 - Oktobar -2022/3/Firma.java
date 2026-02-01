import java.util.*;

class Firma{
	List<Skladiste> skladista;
	
	Firma(){
		skladista = new ArrayList<>();
	}
	
	@Override
	public String toString(){
		String s = "";
		for(Skladiste a: skladista){
			s+=a +"\n";
		}
		
		return "Skladista:\n" +s;
	}
}