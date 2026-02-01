import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;

class AlarmWatcher extends Thread{
	boolean radi = false;
	
	public AlarmWatcher(){super();}
	
	@Override
	public void run(){
		radi = true;
		String staraVrijednost = "";
		Path putanja = Paths.get("alarm.txt");
		while(radi){
			try{
				String novaVrijednost = Files.readString(putanja);
				if(!novaVrijednost.equals(staraVrijednost)){
					staraVrijednost = novaVrijednost;
					
					if(staraVrijednost.trim().equals("1")){
						System.out.println("Alarm aktiviran.");
						Main.cekaj = true;
					}
					
					if(staraVrijednost.trim().equals("0")){
						if(Main.cekaj == true){
							synchronized(Main.lock){
								Main.cekaj = false;
								Main.lock.notifyAll();
							}
						}
					}
				}
				
			}catch(IOException e){
				System.out.println("Greska pri otvaranju alarm.txt");
				radi = false;
			}
		}
	}
}