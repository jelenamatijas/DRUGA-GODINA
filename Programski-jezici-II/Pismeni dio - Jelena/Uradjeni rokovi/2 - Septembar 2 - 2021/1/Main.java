import java.util.*;
import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Main{
	static List<Element> podaci = new ArrayList<>();
	static Object lock = new Object();
	static Object lock1 = new Object();
	static volatile boolean AUTO = false;
	
	static public void main(String args[]){
		
		Thread konzola = new Thread(() -> {
			Scanner scanner = new Scanner(System.in);
			FileWatcher fileWatcher = new FileWatcher();
			fileWatcher.start();
			boolean radi = true;
			while(radi){
				String red = "";
				if(scanner.hasNext()){
					red = scanner.nextLine();
					String []komande = red.trim().split(" ");
					if(komande[0].toLowerCase().equals("import")){
						AUTO = false;
						synchronized(lock1){
							lock1.notifyAll();
						}
						FileWatcher.obradi(Paths.get(komande[1]));
					}else if(komande[0].toLowerCase().equals("auto_import")){
						if(!fileWatcher.folderPaths.contains(komande[1])){
							fileWatcher.folderPaths.add(komande[1]);
						}
						AUTO = true;
						synchronized(lock1){
							lock1.notifyAll();
						}
					}else if(komande[0].toLowerCase().equals("status")){
						synchronized(lock){
							System.out.println("Broj objekata u kolekciji: " + podaci.size());
						}
						fileWatcher.folderPaths.forEach(System.out::println);
					}else if(komande[0].toLowerCase().equals("save")){
						synchronized(lock){
							try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("podaci.ser"))){
								out.writeObject(podaci);
							}catch(Exception e){
								System.out.println("Greska pri serializaciji.");
							}
						}
					}else if(komande[0].toLowerCase().equals("stop")){
						fileWatcher.radi = false;
						AUTO = false;
						synchronized(lock1){
							lock1.notifyAll();
						}
						radi = false;
					}
				}
			}
			scanner.close();
		});
		
		konzola.start();
		try{
			konzola.join();
		}catch(InterruptedException e){
			System.out.println("Greska pri stopiranju konzole.");
		}
	}
}