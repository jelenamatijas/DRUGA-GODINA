import java.util.*;
import java.util.stream.*;
import java.util.function.Function;

class Main{
	static Random rand  = new Random();
	
	public static void main(String []args){
		ArrayList<Automobil> grupa1 = new ArrayList<>();
		ArrayList<Automobil> grupa2 = new ArrayList<>();
		
		for(int i=0;i<10;i++){
			grupa1.add(new Automobil());
			grupa2.add(new Automobil());
		}
		
		System.out.println("======================= GRUPA 1 =======================");
		grupa1.forEach(System.out::println);
		System.out.println("\n======================= GRUPA 2 =======================");
		grupa2.forEach(System.out::println);
		
		System.out.println("\n======================= 1 =======================");
		ArrayList<Automobil> grupa = new ArrayList<>();
		grupa.addAll(grupa1);
		grupa.addAll(grupa2);
		grupa.stream().filter(auto -> auto.snaga>150 && auto.tip == Automobil.Tip.SUV).toList().forEach(System.out::println);
		
		System.out.println("\n======================= 2 =======================");
		System.out.println("======================= GRUPA 1 =======================");
		grupa1.stream().sorted(Comparator.comparingInt(a -> -a.snaga)).forEach(System.out::println);
		System.out.println("\n======================= GRUPA 2 =======================");
		grupa2.stream().sorted(Comparator.comparingInt(a -> -a.snaga)).forEach(System.out::println);
		
		System.out.println("\n======================= 3 =======================");
		Function<List<Automobil>, Integer> filtriraj = lista -> lista.stream().filter(a -> a.tip == Automobil.Tip.HATCHBACK && a.brojVrata > 2).mapToInt(a -> a.brojVrata).sum();
		int kapacitetVrata = filtriraj.apply(grupa);
		System.out.println("Kapacitet vrata: " + kapacitetVrata);
		
		System.out.println("\n======================= 4 =======================");
		double prosjecnaSnaga = grupa.stream().mapToDouble(a -> a.snaga).average().getAsDouble();
		System.out.println("Prosjecna snaga: " + prosjecnaSnaga);
		Automobil auto = grupa.stream().min(Comparator.comparingDouble(a -> Math.abs(a.snaga - prosjecnaSnaga))).get();
		System.out.println(auto);
	}
}