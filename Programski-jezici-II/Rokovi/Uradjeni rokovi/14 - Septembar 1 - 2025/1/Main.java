import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

class Main{
	static Random rand = new Random();
	static String[][] mapa = new String[30][15];
	
	public static void main(String []args){
		File file = new File("folder");
		file.mkdir();
		try{
			Files.walk(Paths.get("folder")).forEach(path -> {
				try{
					if(!path.equals(Paths.get("folder"))){
						Files.delete(path);
					}
				}catch(IOException e){
					System.out.println("GRESKA pri brisanju fajla.");
				}
			});
		}catch(IOException e){
			System.out.println("GRESKA pri prolasku kroz folder.");
		}
		ParkiranjeVanZone pvz = new ParkiranjeVanZone();
		DvostrukoParkiranje dp = new DvostrukoParkiranje();
		IstekloVrijeme iv = new IstekloVrijeme();
		pvz.generisi();
		dp.generisi();
		iv.generisi();
		System.out.println("Generisanje zavrseno.");


		KazneniInspektor inspektor = new KazneniInspektor();
		Thread konzola = new Thread(() -> {
			Scanner scanner = new Scanner(System.in);
			while(true){
				String line = scanner.nextLine().trim();
				if("STOP".equalsIgnoreCase(line)){
					inspektor.radi = false;
					inspektor.pauza = true;
					double brojPolja = ((30.0*50.0 - inspektor.brojPredjenihPolja)/(30.0*50.0))*100;
					System.out.println("Preostalo je jos: " + brojPolja + "% polja da predje");
					try{
						inspektor.join();
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					break;
				}else if("PAUZA".equalsIgnoreCase(line)){
					inspektor.pauza = true;
				}else if("START".equalsIgnoreCase(line)){
					inspektor.start();
				}else if("NASTAVI".equalsIgnoreCase(line)){
					inspektor.pauza = false;
				}
			}
		});
		
		konzola.start();
		try{
			konzola.join();
			if(inspektor.isAlive()){
				inspektor.join();
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}