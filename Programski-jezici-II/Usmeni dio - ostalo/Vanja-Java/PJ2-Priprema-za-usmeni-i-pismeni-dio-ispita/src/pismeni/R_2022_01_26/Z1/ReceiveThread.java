import java.util.*;

public class ReceiveThread extends Thread{

	HashMap<String, Korisnik> korisnici = new HashMap<>();
	
	public ReceiveThread(){
		korisnici.put("Alisa", new Korisnik("Alisa"));
		korisnici.put("Bob", new Korisnik("Bob"));
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