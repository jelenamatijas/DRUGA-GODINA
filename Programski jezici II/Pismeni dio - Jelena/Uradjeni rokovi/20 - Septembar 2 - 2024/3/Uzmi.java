import java.util.*;

class Uzmi extends Thread{
	boolean kraj;
	
	public Uzmi(){
		kraj = true;
	}
	
	@Override
	public void run(){
		while(kraj){
			if(Main.s.size() != 0){
				synchronized(Main.lock){
					try{
						Knjiga k = Main.s.pop();
						System.out.println("Knjiga uzeta sa steka: " + k);
					}catch(EmptyStackException e){
						System.out.println("Stack je trenutno prazan.");
					}
				}
			}
			try{
				sleep(400);
			}catch(InterruptedException e){
				System.out.println("GRESKA prilikom pauziranja niti za uzimanje sa steka.");
			}
		}
	}
	
	
}