import java.util.*;
import java.util.stream.*;
import java.util.function.Function;

class Main{
	static Random rand = new Random();
	static public void main(String []args){
		LinkedList<Vozilo> grupa1 = new LinkedList<>();
		LinkedList<Vozilo> grupa2 = new LinkedList<>();
		
		for(int i=0;i<30;i++){
			grupa1.add(new Vozilo());
			grupa2.add(new Vozilo());
		}
		
		System.out.println("******************** Grupa 1 ********************");
		grupa1.forEach(System.out::println);
		System.out.println("******************** Grupa 2 ********************");
		grupa2.forEach(System.out::println);
		
		System.out.println("******************** 1 ********************");
		LinkedList<Vozilo> grupa = new LinkedList<>();
		grupa.addAll(grupa1.stream().filter((Vozilo v) -> v.boja==Vozilo.Boja.CRVENA && v.snaga > 120).toList());
		grupa.addAll(grupa2.stream().filter((Vozilo v) -> v.boja==Vozilo.Boja.CRVENA && v.snaga > 120).toList());
		grupa.forEach(System.out::println);
		
		System.out.println("******************** 2 ********************");
		grupa.stream().sorted(Comparator.comparing((Vozilo v) -> -v.godina)).forEach(System.out::println);
		
		System.out.println("******************** 3 ********************");
		Function<LinkedList<Vozilo>, Integer> sumiraj = vozila -> {
			int x=0;
			for(Vozilo v: vozila){
				if(v.tip==Vozilo.Tip.SUV && v.godina<2000){
					x+= 0;
				}
				x+= v.brSjedista;
			}
			return x;
		};
		
		long suma =  sumiraj.apply(grupa);
		System.out.println(suma);
		
		System.out.println("******************** 4 ********************");
		double prosjek = grupa.stream().mapToInt((Vozilo v) -> v.snaga).average().orElse(0.0);
		System.out.println("Prosjek: " + prosjek);
		Vozilo v = grupa.stream().min(Comparator.comparingDouble(a -> Math.abs(a.snaga-prosjek))).orElse(null);
		System.out.println(v);
	}
}