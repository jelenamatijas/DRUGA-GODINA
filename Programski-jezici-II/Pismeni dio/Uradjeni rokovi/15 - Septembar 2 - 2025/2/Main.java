import java.nio.file.Files;
import java.io.File;
import java.io.*;
import java.awt.Desktop;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;

class Main{
	static volatile boolean exit = false;
	static final Object lock = new Object();
	
	static public void main(String args[]){
		if(args.length != 1){
			System.out.println("GRESKA u formatu: java Main <putanja_do_foldera>");
			return;
		}
		List<Path> images = new ArrayList<>();
		
		Path path = Paths.get(args[0]);
		
		Desktop desktop = Desktop.getDesktop();
		if(!desktop.isSupported(Desktop.Action.OPEN)){
			System.out.println("OPEN is not supported");
			return;
		}
		System.out.println("Enter 'Q' to stop the app.\n");
		
		Thread readLine = new Thread( () -> {
			Scanner scanner = new Scanner(System.in);
			while(true){
				String tekst = scanner.next();
				if(tekst.toLowerCase().equals("q")){
					synchronized(lock){
						exit = true;
					}
					scanner.close();
					return;
					
				}
			}
		});
		
		Thread search = new Thread( () -> {
			while(true){
				synchronized(lock){
					if(exit){
						break;
					}
				}
				try{
					Files.walk(path).forEach(p -> {
						if(Files.isRegularFile(p)){
							String name = p.getFileName().toString().toLowerCase()	;
							if(name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png") || name.endsWith(".gif") || name.endsWith(".bmp")){
								
								
								if(!images.contains(p)){
									try{
										desktop.open(p.toFile());
										System.out.println(p);
										images.add(p);
									}catch(IOException e){
										System.out.println("Greska pri otvaranju slike.");
									}
									
								}
							}
						}
						
					});
				}catch(IOException e){
					System.out.println("Greska pri prolasku kroz foldere.");
				}
			}
			
		
		});
		
		readLine.start();
		search.start();
		
		
	
	}
}