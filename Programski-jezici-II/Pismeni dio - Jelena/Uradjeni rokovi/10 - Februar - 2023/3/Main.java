import java.io.*;
import java.util.*;
import java.util.stream.*;

class Main{
	static Map<String, Kategorija> kategorije = new HashMap<>();
	static List<Proizvod> proizvodi = new ArrayList<>();
	
	static void ucitajKategorije(String path){
		List<String[]> veze = new ArrayList<>();
		try(BufferedReader bf = new BufferedReader(new FileReader(path))){
			String linija;
			while((linija = bf.readLine())!=null){
				String rijeci[] = linija.split(";");
				String naziv = rijeci[0];
				String roditelj = rijeci[1];
				
				kategorije.putIfAbsent(naziv, new Kategorija(naziv));
				if(!roditelj.equals("-")){
					veze.add(new String[]{roditelj, naziv});
				}
			}
		}catch(IOException e){
			System.out.println("GRESKA pri citanju kategorija.");
		}
		
		for(String[] s : veze){
			Kategorija dijete = kategorije.get(s[1]);
			Kategorija roditelj = kategorije.get(s[0]);
			roditelj.podkategorije.add(dijete);
		}
	}
	
	static void ucitajProizvode(String path){
		try(BufferedReader bf = new BufferedReader(new FileReader(path))){
			String linija;
			while((linija = bf.readLine())!=null){
				String rijeci[] = linija.split(";");
				String naziv = rijeci[0];
				String cijena = rijeci[1];
				String kategorija = rijeci[2];
				
				proizvodi.add(new Proizvod(naziv, Double.parseDouble(cijena), kategorije.get(kategorija)));
			}
		}catch(IOException e){
			System.out.println("GRESKA pri citanju proizvoda.");
		}
	}
	
	public static void main(String args[]){
		if(args.length != 2){
			System.out.println("GRESKA: pogresan format pri pokretanju.");
			return;
		}
		
		ucitajKategorije(args[0]);
		ucitajProizvode(args[1]);
		
		kategorije.forEach((key, value) -> {
			System.out.println(value.naziv + " -> " + value.podkategorije);
		});
		
		System.out.println("================================================");
		
		proizvodi.forEach(p -> System.out.println(p));
		try(PrintWriter pw = new PrintWriter(new FileWriter("sortirani_proizvodi.txt"))){
		}catch(IOException e){
			System.out.println("Greska pri upisu sortiranih prozizvoda.");
		}
		proizvodi.stream().sorted(Comparator.comparingInt((Proizvod p)-> p.kategorija.brojPodkategorija()).thenComparing(p-> p.naziv)).forEach(p->{
			try(PrintWriter pw = new PrintWriter(new FileWriter("sortirani_proizvodi.txt", true))){
				pw.println(p);
				pw.close();
			}catch(IOException e){
				System.out.println("Greska pri upisu sortiranih prozizvoda.");
			}
		});
		
		double ukupno = proizvodi.stream().mapToDouble(p->p.cijena).sum();
		System.out.println("\nUkupna cijena proizvoda: " + ukupno);
		
		proizvodi.stream().collect(Collectors.groupingBy(p-> p.kategorija)).forEach((kat,list) -> {
			System.out.println(kat + " -> " + list.stream().mapToDouble(p->p.cijena).sum());
		});
	}
	
}