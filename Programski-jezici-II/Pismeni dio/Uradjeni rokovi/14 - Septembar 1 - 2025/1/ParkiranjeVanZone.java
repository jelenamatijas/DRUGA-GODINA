import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.*;

class ParkiranjeVanZone extends TeskiPrekrsaj{
	static int id = 1;
	public ParkiranjeVanZone(){
		super("Parkiranje van zone");
	}
	
	public void generisi(){
		long pocetak = System.currentTimeMillis();
		while(System.currentTimeMillis() - pocetak < 20000){
			try{
				Path putanja = Paths.get("folder/kaznena_naljepnica_" + id + ".kazna");
				Files.createFile(putanja);
				id++;
				boolean stop = false;
				while(!stop){
					if(System.currentTimeMillis() - pocetak >= 20000){
						break;
					}
					
					int x = Main.rand.nextInt(0,30);
					int y = Main.rand.nextInt(0,15);
					
					if(Main.mapa[x][y] == null){
						Main.mapa[x][y] = new String(putanja.toString());
						stop = true;
					}
				}
				try{
					Thread.sleep(500);
				}catch(InterruptedException e){
					System.out.println("GRESKA pri pauziranju generisanja fajlova za parkiranje van zone.");
				}
			}catch(IOException e){
				System.out.println("GRESKA prilikom kreiranja fajla");
			}
		}
	}
	
	@Override
	public String toString(){
		return super.toString();
	}
}