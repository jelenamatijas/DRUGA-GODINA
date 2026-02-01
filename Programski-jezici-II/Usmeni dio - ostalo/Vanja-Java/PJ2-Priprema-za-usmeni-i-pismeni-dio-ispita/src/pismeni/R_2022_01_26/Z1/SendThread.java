import java.util.*;
import java.nio.file.*;
import java.io.*;

public class SendThread extends Thread{

	HashMap<String, Korisnik> korisnici = new HashMap<>();
	
	public SendThread(){
		korisnici.put("Alisa", new Korisnik("Alisa"));
		korisnici.put("Bob", new Korisnik("Bob"));
		new File("Alisa").mkdir();
		new File("Bob").mkdir();
		setDaemon(true);
		start();
	}
	
	@Override
	public void run() {
		try{
			while(!Main.FULL){
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	


}