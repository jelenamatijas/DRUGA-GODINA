import java.util.*;
import java.util.function.Function;

class Main{
	public static <T> List<String> transformisi(List<T> lista, List<Function<T, String>> transformacije, int pocetak, int kraj){
		List<String> rezultati = new ArrayList<>();
		for(int i=0; i< Math.min(lista.size(), kraj);i++){
			T t = lista.get(i);
			try{
				for(Function<T, String> f : transformacije){
					rezultati.add(f.apply(t));
				}
			}catch(Exception e){
				System.out.println("Transformacija preskocena."); 
			}
			
		}
		
		return rezultati;
	}
	
	public static void main(String []args){
		List<Proizvod> proizvodi = Arrays.asList(
                new Proizvod("Hljeb", 2.5, 10),
                new Proizvod("Mlijeko", 1.7, 5),
                new Proizvod("Sir", 6.0, 3)
        );
		
		List<Function<Proizvod, String>> transformacije = new ArrayList<>();
		transformacije.add(p -> "Naziv: " + p.naziv);
		transformacije.add(p -> "Ukupna vrijednost: " + p.cijena * p.kolicina);
		
		List<String> rezultati = transformisi(proizvodi, transformacije, 0, 5);
		rezultati.forEach(System.out::println);
	}
}