import java.util.*;
import java.util.stream.Collectors;

class Main{
	static Random rand = new Random();
	public static void main(String []args){
		LinkedList<Uredjaj> grupa = new LinkedList<>();
		/*Uredjaj u1 = new Uredjaj();
		Uredjaj u2 = new Uredjaj();
		Uredjaj u3 = new Uredjaj();*/
		for(int i=0;i<50;i++){
			grupa.add(new Uredjaj());
			/*grupa.add(u1);
			grupa.add(u2);
			grupa.add(u3);*/
		}
		
		grupa.forEach(System.out::println);
		
		System.out.println("\n========================== 1 ==========================");
		List<Uredjaj> laptopi2000 = grupa.stream().filter(u -> u.tip==Uredjaj.Tip.laptop && u.tezina>=2000).toList();
		laptopi2000.forEach(System.out::println);
		
		System.out.println("\n========================== 2 ==========================");
		grupa.stream().sorted(Comparator.comparingDouble((Uredjaj u) -> u.tezina).thenComparingInt(u -> u.cijena)).forEach(System.out::println);
		
		System.out.println("\n========================== 3 ==========================");
		double prosjecnaCijena = grupa.stream().filter(u -> u.tip == Uredjaj.Tip.telefon).mapToInt(u -> u.cijena).average().getAsDouble();
		System.out.println("Prosjecna cijena: " + prosjecnaCijena);
		
		System.out.println("\n========================== 4 ==========================");
		Uredjaj maxCijena = grupa.stream().max(Comparator.comparingInt((Uredjaj u) -> u.cijena)).get();
		System.out.println(maxCijena);
		
		System.out.println("\n========================== 5 ==========================");
		grupa = grupa.stream().distinct().collect(Collectors.toCollection(LinkedList::new));
		grupa.forEach(System.out::println);
	}
}
