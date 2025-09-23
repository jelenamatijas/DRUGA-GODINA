import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Obrada extends Thread{
	String s;
	public Obrada(String s){
		this.s = s;
	}
	
	@Override
	public void run(){
		Path root = Paths.get(s);
		try{
			Files.walk(root).forEach(path -> {
				if(Files.isRegularFile(path)){
					String putanja = path.toString();
					if(putanja.endsWith(Main.ekstenzija)){
						Main.pronadjeno++;
						try(PrintWriter pw = new PrintWriter(new FileWriter(path.getParent().toString() + File.separator + "pronadjeni.txt"))){
							pw.println(path.toString());
						}catch(IOException e){
							System.out.println("GRESKA pri uisivanju u fajl u folderu -> " + s);
						}
					}
					Main.ukupno++;
				}
			});
		}catch(IOException e){
			System.out.println("GRESKA pri prolasku kroz folder " + root);
		}
	}
}