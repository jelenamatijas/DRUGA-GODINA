import java.util.*;
import java.util.stream.Collectors;

class Main{
	static Random rand = new Random();
	public static void main(String args[]){
		LinkedList<Aktivnost> grupa = new LinkedList<>();
		Aktivnost ak = new Aktivnost();
		grupa.add(ak);
		grupa.add(ak);
		for(int i=0; i<98;i++){
			grupa.add(new Aktivnost());
		}
		
		grupa.forEach(System.out::println);
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.println("Unesi opciju (A,B,C,D,E,F,G, STOP): ");
			String line = scanner.nextLine().trim();
			if("A".equalsIgnoreCase(line)){
				List<Aktivnost> takmicariDo20 = grupa.stream().filter(a -> a.tip == Aktivnost.Tip.TAKMICENJE && a.brojUcesnika <=20).toList();
				takmicariDo20.forEach(System.out::println);
			}else if("B".equalsIgnoreCase(line)){
				grupa.stream().sorted(Comparator.comparingDouble((Aktivnost u) -> u.budzet).thenComparing(Comparator.comparingInt(u-> u.brojUcesnika))).forEach(System.out::println);
			}else if("C".equalsIgnoreCase(line)){
				double prosjek = grupa.stream().filter(u -> u.tip == Aktivnost.Tip.RADIONICA).mapToDouble(u -> u.budzet).average().getAsDouble();
				System.out.println("Prosjek budzeta: " + prosjek);
			}else if("D".equalsIgnoreCase(line)){
				Aktivnost min = grupa.stream().min(Comparator.comparingDouble(u -> u.budzet)).get();
				Aktivnost max = grupa.stream().max(Comparator.comparingDouble(u -> u.budzet)).get();
				System.out.println("MIN: " + min + "\nMAX: " + max);
			}else if("E".equalsIgnoreCase(line)){
				grupa = grupa.stream().distinct().collect(Collectors.toCollection(LinkedList::new));
				grupa.forEach(System.out::println);
			}else if("F".equalsIgnoreCase(line)){
				grupa.stream().collect(Collectors.groupingBy(u -> u.tip)).forEach((tip, lista) ->{
					double budzet = lista.stream().mapToDouble(u -> u.budzet).sum();
					System.out.println(tip + ":" + budzet);
				});
			}else if("G".equalsIgnoreCase(line)){
				List<Aktivnost> top3 = grupa.stream().sorted(Comparator.comparingDouble(u -> u.budzet/u.brojUcesnika)).limit(3).toList();
				top3.forEach(System.out::println);
			}else if("STOP".equalsIgnoreCase(line)){
				break;
			}
		}
		
	}
}