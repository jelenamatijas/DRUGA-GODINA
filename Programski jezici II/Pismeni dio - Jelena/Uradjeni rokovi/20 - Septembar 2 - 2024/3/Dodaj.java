import java.util.*;

class Dodaj extends Thread{
	boolean kraj;
	
	public Dodaj(){
		kraj = true;
	}
	
	@Override
	public void run(){
		while(kraj){
			Knjiga k =new Knjiga();
			synchronized(Main.lock){
				Main.s.push(k);
			}
			
			System.out.println("Dodana je nova knjiga: " + k);
			try{
				sleep(250);
			}catch(InterruptedException e){
				System.out.println("GRESKA prilikom pauziranja niti za dodavanje.");
			}
		}
	}
}