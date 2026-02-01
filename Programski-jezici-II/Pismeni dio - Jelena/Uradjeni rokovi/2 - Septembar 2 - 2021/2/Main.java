import java.util.*;
import java.util.stream.*;

class Main{
	static Random rand = new Random();
	static public void main(String args[]){
		List<Student> studenti = new ArrayList<>();
		
		for(int i=0;i<80;i++){
			studenti.add(new Student());
		}
		
		System.out.println("Ukupan broj studenata po godini studija: ");
		studenti.stream().collect(Collectors.groupingBy((Student s) -> s.godinaStudija)).forEach((godina, lista) -> {
			System.out.println(godina +":");
			for(Student student : lista){
				System.out.println(student);
			}
		});
		
		System.out.println("Tri najcesca prezimena: ");
		//List<Map.Entry<String, Long>> mapa
		studenti.stream().collect(Collectors.groupingBy((Student s) -> s.prezime, Collectors.counting())).
					entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(3).forEach( entry -> {
						System.out.println(entry.getKey());
					});
					
		System.out.println("Najceste ime: ");
		studenti.stream().collect(Collectors.groupingBy((Student s) -> s.ime, Collectors.counting())).entrySet().stream().
				sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(1).forEach(entry -> {
					System.out.println(entry.getKey());
				});
				
		System.out.println("Studenti po godinama starosti: ");
		studenti.stream().collect(Collectors.groupingBy((Student s) -> {
			int starost = 2026 - s.godinaRodjenja;
			int donja = (starost/6)*6;
			int gornja = donja+5;
			return donja + "-" + gornja;
		})).forEach((grupa, lista) -> {
			System.out.println(grupa);
			lista.forEach(System.out::println);
		});
		
		System.out.println("Prikaz svih studenata grupisanih po prezimenu:");
		studenti.stream().collect(Collectors.groupingBy((Student s) -> s.prezime)).forEach((grupa, lista) -> {
			System.out.println(grupa);
			lista.forEach(System.out::println);
		});
		
		System.out.println("Najbolji studenti po godini:");
		studenti.stream().collect(Collectors.groupingBy((Student s) -> s.godinaStudija)).forEach((godina, lista) -> {
			System.out.print(godina +": ");
			System.out.println(lista.stream().max(Comparator.comparing((Student s) -> s.prosjecnaOcjena)).get());
		});
		
		System.out.println("Godine rodjenja: ");
		System.out.println(studenti.stream().map(s -> s.godinaRodjenja).distinct().sorted().map(String::valueOf).reduce((a,b) -> a+"#"+b).orElse(""));
		
		
		System.out.println("Prikaz studenata sortirano po prezimenu: ");
		studenti.stream().sorted(Comparator.comparing((Student s) -> s.prezime).reversed()).toList().forEach(student -> {
			System.out.println(student);
		});
	}
}