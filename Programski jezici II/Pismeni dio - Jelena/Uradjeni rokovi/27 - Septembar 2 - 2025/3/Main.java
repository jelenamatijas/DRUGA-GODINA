import java.util.*;
import java.util.stream.*;
import java.util.Comparator;

class Main{
	private static List<Proizvod> spoji(List<? extends Proizvod> lista1, List<? extends Proizvod> lista2, Comparator<Proizvod> comparator){
		return Stream.concat(lista1.stream(), lista2.stream()).sorted(comparator).collect(Collectors.toList());
	}
	
	public static void main(String []args){
		Kutija<PrehrambeniProizvod> prehrambeni = new Kutija<>();
		Kutija<ElektronskiProizvod> elektronika = new Kutija<>();
		Kutija<AlatProizvod> alati = new Kutija<>();
		
		alati.dodaj(new AlatProizvod("A1", "Aparat za varenje"));
        alati.dodaj(new AlatProizvod("A2", "Aku busilica"));
        elektronika.dodaj(new ElektronskiProizvod("E1", "Laptop ASUS"));
        elektronika.dodaj(new ElektronskiProizvod("E2", "TV Samsung"));
        prehrambeni.dodaj(new PrehrambeniProizvod("P1", "Ananas"));
        prehrambeni.dodaj(new PrehrambeniProizvod("P2", "Brašno"));
		
		System.out.println("========================= 1 =========================");
		List<Proizvod> filtrirani = new ArrayList<>();
		filtrirani.addAll(prehrambeni.filtriraj(p -> p.naziv.startsWith("A")));
		filtrirani.addAll(elektronika.filtriraj(p -> p.naziv.startsWith("A")));
		filtrirani.addAll(alati.filtriraj(p -> p.naziv.startsWith("A")));
		filtrirani.forEach(System.out::println);	
		
		System.out.println("========================= 2 =========================");
		List<Proizvod> sortirani = spoji(alati.proizvodi, elektronika.proizvodi, Comparator.comparing((Proizvod p) -> p.naziv));
		sortirani.forEach(System.out::println);
	}
}
