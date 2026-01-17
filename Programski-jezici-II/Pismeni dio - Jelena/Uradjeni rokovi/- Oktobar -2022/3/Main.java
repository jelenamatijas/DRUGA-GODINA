import java.util.*;
import java.util.stream.*;

class Main{
	static Random rand = new Random();
	
	static public void main(String []args){
		List<Artikl> artikli = new ArrayList<>();
		for(int i=0;i<50;i++){
			artikli.add(new Artikl());
		}
		
		List<Rafa> rafe = new ArrayList<>();
		
		for(int i=0;i<50;i++){
			Rafa rafa = new Rafa();
			int size = Main.rand.nextInt(5, 50);
			for(int j=0;j<size;j++){
				int x = rand.nextInt(0, 50);
				rafa.artikli.put(artikli.get(x), rafa.artikli.getOrDefault(artikli.get(x), 0)+1);
			}
			rafe.add(rafa);
		}
		
		Firma firma = new Firma();
		int size = Main.rand.nextInt(2, 5);
		for(int i=0;i<size;i++){
			Skladiste skladiste = new Skladiste();
			int s = Main.rand.nextInt(5, 10);
			for(int j=0;j<s;){
				int x = rand.nextInt(0, 50);
				if(!skladiste.rafe.contains(rafe.get(x))){
					skladiste.rafe.add(rafe.get(x));
					j++;
				}
			}
			firma.skladista.add(skladiste);
		}
		
		System.out.println("=================== 1 ===================");
		
		long ukupnaKolicinaArtikala = firma.skladista.stream().flatMap(skladiste -> skladiste.rafe.stream()).flatMap(rafa -> rafa.artikli.entrySet().stream()).
				filter(artikl -> artikl.getKey().barkodovi.contains("Barkod_3")).mapToInt(Map.Entry::getValue).sum();
		System.out.println(ukupnaKolicinaArtikala);
		
		System.out.println("=================== 2 ===================");
		System.out.println(firma.skladista.stream().filter(skladiste -> skladiste.tip == Skladiste.TipSkladista.MALOPRODAJA).
					flatMap(skladiste -> skladiste.rafe.stream()).flatMap(rafa -> rafa.artikli.entrySet()
					.stream()).mapToDouble(artikl -> artikl.getKey().cijena*artikl.getValue()).sum());
					
		System.out.println("=================== 3 ===================");
		firma.skladista.stream().forEach(skladiste -> {
			Optional<Rafa> r = skladiste.rafe.stream().max(Comparator.comparing(rafa -> rafa.artikli.size()));
			System.out.println(skladiste.adresa + ": " + r + "\n\n");
		});
		
		firma.skladista.stream().sorted(Comparator.comparing(skladiste -> 
			-skladiste.rafe.stream().flatMap(rafa -> rafa.artikli.entrySet().stream()).mapToInt(artikl -> artikl.getValue()).sum()
		)).forEach(System.out::println);
	}
}