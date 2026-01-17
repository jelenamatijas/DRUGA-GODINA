import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

class KazneniInspektor extends Thread{
	int brojPredjenihPolja;
	volatile boolean radi;
	volatile boolean pauza;
	public KazneniInspektor(){
		super();
		brojPredjenihPolja = 0;
		radi = true;
		pauza = false;
	}
	
	@Override
	public void run(){
		for(int i =0; i< 30 && radi;i++){
			for(int j=0; j<15 && radi;){
				if(!pauza){
					if(Main.mapa[i][j] != null){
						String polje = Main.mapa[i][j];
						if(polje.endsWith(".kazna")){
							try(PrintWriter pw = new PrintWriter(new FileWriter(polje, true))){
								pw.println("PREKRSAJ EVIDENTIRAN.");
								System.out.println(i + ":" + j + " -> PREKRSAJ EVIDENTIRAN");
							}catch(IOException e){
								System.out.println("GRESKA pri otvaranju fajla .kazna");
							}
						}else if(polje.endsWith(".ser")){
							try{
								List<String> sadrzaj = Files.readAllLines(Paths.get(polje));
								System.out.println(sadrzaj);
							}catch(IOException e){
								System.out.println("GRESKA pri otvaranju fajla .ser");
							}
						}else{
							System.out.println(i + ":" + j + " -> ISTEKLO");
						}
					}else{
						System.out.println(i + ":" + j);
					}
					brojPredjenihPolja++;
					j++;
					try{
						sleep(300);
					}catch(InterruptedException e){
						System.out.println("GRESKA pri pauziranju inspektora.");
					}
				}
			}
		}
	}
}