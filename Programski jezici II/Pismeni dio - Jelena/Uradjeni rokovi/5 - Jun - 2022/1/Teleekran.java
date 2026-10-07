import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

class Teleekran implements Runnable{
	BlockingQueue<String> poruke = new LinkedBlockingQueue<>();
	volatile boolean radi = true;
	
	public void prikaz(String s){
		poruke.offer(s);
	}
	
	public void zaustavi(){
		radi = false;
	}
	
	@Override
	public void run(){
		try{
			while(radi){
				String poruka = poruke.poll(100, TimeUnit.MILLISECONDS);
				if(poruka != null){
					System.out.println("Teleekran: " + poruka);
				}
			}
		}catch(InterruptedException e){
			System.out.println("Teleekran ugasen.");
		}
	}
}