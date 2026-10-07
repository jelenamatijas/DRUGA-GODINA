import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.*;

class Main{
	public static int brojObrisanih = 0;
	
	public static void main(String args[]){
		if(args.length != 2){
			System.out.println("GRESKA format mora biti java Main <PUTANJA_FOLDERA> <TEKST>");
			return;
		}
		
		Path root = Paths.get(args[0]);
		String tekst = args[1];
		
		if(!Files.exists(root)){
			System.out.println("Unesena putanja ka folderu nije validna.");
			return;
		}
		
		
		
		try{
			Files.walk(root).forEach(file -> {
				String filename = (file.getFileName()).toString().split("\\.")[0].toLowerCase();
				if(filename.contains(tekst.toLowerCase())){
					try{
						Files.delete(file);
						System.out.println("Obrisan je fajl: " + filename);
						brojObrisanih++;
					}catch(IOException e){
						System.out.println("GRESKA prilikom brisanja fajla: " + filename);
					}
				}
				
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("Broj obrisanih fajlova: " + brojObrisanih);
	}
}