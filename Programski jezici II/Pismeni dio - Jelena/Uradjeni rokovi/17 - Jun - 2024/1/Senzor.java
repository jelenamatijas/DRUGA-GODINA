import java.util.*;

abstract class Senzor extends Thread{
	Integer[] data;
	String senzorName;
	volatile boolean radi;
	
	public Senzor(){
		radi = false;
	}
	
	public abstract void ocitajVrijednost();
	
	@Override
	public void run(){
		radi = true;
		System.out.println("Senzor " + senzorName + " je POCINJE sa radom.");
		while(radi){
			this.ocitajVrijednost();
			try{
				sleep(500);
			}catch(InterruptedException e){
				System.out.println("Greska pri radu senzora " + senzorName);
			}
		}
		System.out.println("Senzor " + senzorName + " je ZAVRSIO sa radom.");
	}
}