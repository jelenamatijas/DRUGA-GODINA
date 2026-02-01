import java.util.*;
import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Main{
	static volatile boolean radi = true;
	static Path directoryPath;
	
	static public void main(String args[]){
		if(args.length !=1){
			System.out.println("GRESKA: pogresan format.");
			return;
		}
		
		directoryPath = Paths.get(args[0]);
		
		Thread konzola = new Thread(() -> {
			Scanner scanner = new Scanner(System.in);
			while(true){
				if(scanner.hasNext()){
					String s = scanner.next().trim().toLowerCase();
					if(s.equals("stop")){
						radi = false;
						break;
					}
				}
			}
		});
		
		konzola.start();
		FileWatcher watcher = new FileWatcher();
		watcher.start();
		
		
	}
}