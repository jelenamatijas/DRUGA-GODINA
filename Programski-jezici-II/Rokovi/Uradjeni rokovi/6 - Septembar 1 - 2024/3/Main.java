import java.util.function.Predicate;
import java.util.List;
import java.util.ArrayList;

class Main{
	
	public static <T extends Podatak> List<T> filtrirajSortiraj(List<Predicate<T>> uslovi, int pocetak, int kraj, List<T>... listaPodataka){
		List<T> rezultati = new ArrayList<>();
		for(List<T> lista : listaPodataka){
			for(T t : lista){
				boolean zadovoljava = uslovi.stream().allMatch(uslov -> uslov.test(t));
				if(zadovoljava){
					rezultati.add(t);
				}
			}
		}
		
		rezultati.sort((a,b) -> Integer.compare(a.hashCode(), b.hashCode()));
		return rezultati.subList(pocetak, Math.min(kraj, rezultati.size()));
	}
	
	public static void main(String []args){
		List<Student> l1 = new ArrayList<>();
		l1.add(new Student("Marko", "Markovic", "1101/23"));
		l1.add(new Student("Pero", "Perovic", "1121/23"));
		l1.add(new Student("Ivo", "Ivic", "1131/23"));
		
		List<Student> l2 = new ArrayList<>();
		l2.add(new Student("Marica", "Maric", "1101/22"));
		l2.add(new Student("Perica", "Peric", "1121/22"));
		l2.add(new Student("Milica", "Milic", "1141/22"));
		
		List<Student> l3 = new ArrayList<>();
		l3.add(new Student("Milun", "Milunic", "1145/21"));
		l3.add(new Student("Milun", "Milunov", "1151/23"));
		l3.add(new Student("Mara", "Ivic", "1141/23"));
		
		Predicate<Student> uslov1 = student -> student.getPrezime().endsWith("ic");
		Predicate<Student> uslov2 = student -> student.getIme().startsWith("M");
		Predicate<Student> uslov3 = student -> student.getIndex().contains("4");
		List<Predicate<Student>> uslovi = new ArrayList<>();
		uslovi.add(uslov1);
		uslovi.add(uslov2);
		uslovi.add(uslov3);
		
		List<Student> rezultati = filtrirajSortiraj(uslovi, 0,3, l1,l2, l3);
		System.out.println("REZULTATI: ");
		rezultati.forEach(System.out::println);
	}
}