import java.util.*;
import java.util.function.Predicate;

class Main{
	
	static <T extends PodatakInterface> List<T> metoda(List<Predicate<T>> uslovi, int pocetak, int kraj, List<T> ... liste){
		List<T> rezultat = new ArrayList<>();
		for(List<T> lista : liste){
			if(lista==null){
				continue;
			}
			for(T t : lista){
				boolean ok = true;
				for(Predicate<T> p : uslovi){
					if(!p.test(t)){
						ok = false;
						break;
					}
				}
				if(ok){
					rezultat.add(t);
				}
			}
		}
		
		int start = Math.max(pocetak, 0);
		int stop = Math.min(kraj,rezultat.size());
		
		rezultat.sort(Comparator.comparing(t -> -t.hashCode()));
		return new ArrayList<>(rezultat.subList(start, stop));
	}
	
	static public void main(String args[]){
		Predicate<Student> p1 = s -> {
			if(s.brojIndeksa < 10){
				return true;
			}
			return false;
		};
		
		Predicate<Student> p2 = s ->{
			if(s.ime.length() <10){
				return true;
			}
			return false;
		};
		
		List<Predicate<Student>> uslovi = Arrays.asList(p1, p2);
		List<Student> l1 = new ArrayList<>();
		List<Student> l2 = new ArrayList<>();
		List<Student> l3 = new ArrayList<>();
		
		for(int i=0;i<10;i++){
			l1.add(new Student());
			l2.add(new Student());
			l3.add(new Student());
		}
		
		metoda(uslovi, 1, 6, l1, l2, l3).forEach(System.out::println);
	}
}