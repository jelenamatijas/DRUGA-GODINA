import java.io.*;
import java.nio.file.Paths;
import java.util.*;

class DvostrukoParkiranje extends TeskiPrekrsaj{
	static int id = 1;
	public DvostrukoParkiranje(){
		super("Dvostruko parkiranje");
	}
	
	public void generisi(){
		for(int i=0; i< 30; i++){
			try(PrintWriter pw = new PrintWriter(new FileWriter("folder/dvostruko_parkiranje_" + id + ".ser"))){
				pw.println((new Vozilo()).toString());
				
				while(true){
					int x = Main.rand.nextInt(0,30);
					int y = Main.rand.nextInt(0,15);
					
					if(Main.mapa[x][y] == null){
						Main.mapa[x][y] = new String("folder/dvostruko_parkiranje_" + id + ".ser");
						break;
					}
				}
				
				id++;
			}catch(IOException e){
				System.out.println("GRESKA prilikom kreiranja fajla: " + "folder/dvostruko_parkiranje_" + id + ".ser");
			}
		}
	}
	
	@Override
	public String toString(){
		return super.toString();
	}
}