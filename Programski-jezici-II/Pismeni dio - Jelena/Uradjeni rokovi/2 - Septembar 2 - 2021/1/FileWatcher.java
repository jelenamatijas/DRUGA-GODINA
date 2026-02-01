import java.util.*;
import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class FileWatcher extends Thread{
	List<String> folderPaths;
	static List<Path> fileNames;
	boolean radi = true;
	
	FileWatcher(){
		folderPaths =new ArrayList<>();
		fileNames = new ArrayList<>();
	}
	
	static public void obradi(Path file){
		if(!fileNames.contains(file)){
			fileNames.add(file);
			String ekstenzija = file.toString().substring(file.toString().lastIndexOf(".")+1).toLowerCase();
			if(ekstenzija.equals("csv")){
				new Thread(new CSVAdapter(file.toString())).start();
			}else if(ekstenzija.equals("json")){
				new Thread(new JSONAdapter(file.toString())).start();
			}
		}
	}
	
	public void run(){
		while(radi){
			try{
				if (folderPaths.isEmpty()) {
                    Thread.sleep(1000); 
                    continue;
                }
				folderPaths.forEach(folderPath -> {
					if(!radi){
						return;
					}
					Path path = Paths.get(folderPath);
					try{
						if (Files.exists(path) && Files.isDirectory(path)) {
							Files.walk(path).forEach(file ->{
								
								synchronized(Main.lock1){
									if(!Main.AUTO){
										try{
											if(!radi){
												return;
											}
											Main.lock1.wait();
										}catch(InterruptedException e){
											if(!radi){
												System.out.println("KRAJ.");
												return;
											}
										}
									}
								}
								if(radi){
									obradi(file);
								}
								
							});
						}
						try{
								Thread.sleep(2000);
						}catch (InterruptedException e) {
							System.out.println("FileWatcher prekinut.");
						}
					}catch(IOException e){
						System.out.println("Greska pri obilasku foldera.");
					}
				});
			}catch(Exception e){
				System.out.println("Greska pri obilasku foldera.");
			}
		}
	}
}