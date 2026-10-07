import java.util.*;
import java.util.function.Predicate;

class Main{
	static public void main(String args[]){
		Predicate<AkcijaInterface> p1 = data ->{
			if(data.getOpis().length() <= 10){
				return true;
			}
			return false;
		};
		Predicate<AkcijaInterface> p2 = data -> {
			if(Integer.parseInt(data.getOpis().substring(5, data.getOpis().length())) %2 ==0){
				return true;
			}
			return false;
		};
		List<Predicate<AkcijaInterface>> uslovi = Arrays.asList(p1, p2);
		
		Skladiste s = new Skladiste();
		s.add(new Alarm());
		s.add(new Obavjestenje());
		s.add(new Alarm());
		s.add(new Obavjestenje());
		s.add(new Alarm());
		s.add(new Obavjestenje());
		
		s.print();
		
		s.runActions();
		s.search(uslovi);
		
		System.out.println(s.getFirst());
	}
}