import java.util.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent;
import static java.nio.file.StandardWatchEventKinds.*;
import java.nio.file.WatchKey;

class Main{
	private static int ukupanBrojSamoglasnika=0;
	public static void main(String []args){
		if(args.length != 2){
			System.out.println("GRESKA: format: java Main <BROJ DIREKTORIJUMA> <PUTANJA DO DIREKTORIJUMA>");
			return;
		}
		
		int n=0;
		try{
			n = Integer.parseInt(args[0]);
		}catch(NumberFormatException e){
			System.out.println("GRESKA! Broj direktorijuma mora biti cjelobrojna vrijednost!");
		}
		
		String path = args[1];
		Path root = Paths.get(path);
		
		try{
			if(!Files.isDirectory(root) || !Files.exists(root)){
				Files.createDirectory(root);
				System.out.println("Dati direktorijum ne postoji te je napravljen novi: " + root);
			}
			
			for(int i=0; i<n; i++){
				Path noviDirektorijum = root.resolve(String.valueOf(i));
				Files.createDirectory(noviDirektorijum);
				System.out.println("Novi direktorijum je napravljen: " + noviDirektorijum);
			}
		}catch(IOException e){
			System.out.println("GRESKA prilikom kreiranja direktorijuma.");
		}
		
		try{
			WatchService watcher = FileSystems.getDefault().newWatchService();
			for(int i=0; i<n; i++){
				Path dir = root.resolve(String.valueOf(i));
				dir.register(watcher, ENTRY_CREATE, ENTRY_MODIFY);
			}
			
			System.out.println("Pracenje promjena u direktorijumu je pocelo.");
			
			while(true){
				WatchKey key = watcher.take();
				
				for(WatchEvent<?> event : key.pollEvents()){
					WatchEvent.Kind<?> kind = event.kind();
					
					@SuppressWarnings("unchecked")
					WatchEvent<Path> ev = (WatchEvent<Path>)event;
					
					Path imeFajla = ev.context();
					Path direktorijum = (Path) key.watchable();
					Path putanjaTxtFajla = direktorijum.resolve(imeFajla);
					
					if(kind == ENTRY_CREATE || kind == ENTRY_MODIFY){
						if(imeFajla.toString().endsWith(".txt")){
							ArrayList<Integer> samogalsnici = obradiFajl(putanjaTxtFajla);
							
							System.out.println("Datoteka: " + putanjaTxtFajla);
							System.out.println("NAPOMENA: Broj samoglasnika je redom a, e, i, o, u.");
							samogalsnici.stream().forEach(s -> System.out.println(s));
							ukupanBrojSamoglasnika += samogalsnici.stream().mapToInt(Integer::intValue).sum();
							System.out.println("Ukupan broj svih samoglasnika: " + ukupanBrojSamoglasnika);
						}
					}
				}
				
				boolean valid = key.reset();
				if(!valid){
					break;
				}
			}
			
		}catch(IOException e){
			System.out.println("GRESKA tokom WatchService.");
		}catch(InterruptedException e){
			System.out.println("GRESKA tokom WatchService (watcher.take()).");
		}
	}
	
	private static ArrayList<Integer> obradiFajl(Path putanjaTxtFajla){
		ArrayList<Integer> samoglasnici = new ArrayList<>();
		try(BufferedReader bf = new BufferedReader(new FileReader(putanjaTxtFajla.toString()))){
			String linija;
			int a = 0;
			int e = 0;
			int i = 0;
			int o = 0;
			int u = 0;
			
			while((linija = bf.readLine())!=null){
				for(char c : linija.toCharArray()){
					if( c == 'a' || c == 'A'){
						a++;
					}else if(c == 'e' || c == 'E'){
						e++;
					}else if(c == 'i' || c == 'I'){
						i++;
					}else if(c == 'o' || c == 'O'){
						o++;
					}else if(c == 'u' || c == 'U'){
						u++;
					}	
				
				}
			}
			
			samoglasnici.add(a);
			samoglasnici.add(e);
			samoglasnici.add(i);
			samoglasnici.add(o);
			samoglasnici.add(u);
		}catch(IOException e){
			System.out.println("GRESKA pri otvaranju fajla: " + putanjaTxtFajla);
		}
		return samoglasnici;
	}
}