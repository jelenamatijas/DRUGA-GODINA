import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Main{
	
	public static void main(String args[]){
		LinkedList<Automobil> automobili1 = new LinkedList<>();
		
		for(int i=0;i<10;i++){
			Automobil a = new Automobil();
			automobili1.add(a);
		}
		
		LinkedList<Automobil> automobili2 = new LinkedList<>();
		
		for(int i=0;i<10;i++){
			Automobil a = new Automobil();
			automobili2.add(a);
		}
		
		LinkedList<Automobil> automobili = new LinkedList<>();
		automobili.addAll(automobili1);
		automobili.addAll(automobili2);
		
		System.out.println(automobili);
		System.out.println("=====================================================================");
		
		//1
		automobili.stream().filter(automobil -> (automobil.snaga >= 150 && automobil.tip == Automobil.TIP.SUV)).collect(Collectors.toList()).forEach(automobil -> System.out.println(automobil));
		System.out.println("=====================================================================");
	
		//2
		automobili.stream().sorted(Comparator.comparingInt(automobil -> -automobil.snaga)).forEach(automobil -> System.out.println(automobil));
		
	
		//3.
		int sum = automobili.stream().filter(automobil -> automobil.tip == Automobil.TIP.HATCHBACK && automobil.brojVrata>2).mapToInt(a -> a.brojVrata).sum();
		System.out.println(sum);
		
		Function<LinkedList<Automobil>, Integer> sumiraj = list -> list.stream().filter(automobil -> automobil.tip == Automobil.TIP.HATCHBACK && automobil.brojVrata>2).mapToInt(a -> a.brojVrata).sum();
		sum = sumiraj.apply(automobili);
		System.out.println(sum);
		System.out.println("=====================================================================");
		
		//4.
		OptionalDouble prosjek = automobili.stream().mapToInt(a -> a.snaga).average();
		System.out.println("Prosjecna snaga: " + prosjek.getAsDouble());
		
		Optional<Automobil> auto = automobili.stream().min(Comparator.comparingDouble(a -> Math.abs((double)a.snaga - prosjek.getAsDouble())));
		System.out.println(auto.get());
	}
}