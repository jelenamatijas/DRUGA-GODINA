package src.traka;

import java.util.LinkedList;
import src.traka.masine.*;

public class Traka extends Thread{
	Priprema priprema = new Priprema();
	Gruba gruba = new Gruba();
	Fina fina = new Fina();
	Pakovanje pakovanje = new Pakovanje();
	String proizvod;
	int count;
	LinkedList<Materijal> materijal;
	
	public Traka(LinkedList<Materijal> materijal, String proizvod){
		this.materijal = materijal;
		this.proizvod = proizvod;
		
	}
	
	public void run(){
		long currTime = System.currentTimeMillis();
		while(materijal.size() > 0){
			
			System.out.println(this + " obradjuje " + materijal.peek());
			priprema.obradi(materijal.peek());
			if(!gruba.obradi(materijal.peek()))
				fina.obradi(materijal.peek());
			pakovanje.obradi(materijal.peek());
			System.out.println("Napravljen je " + proizvod + " od materijala " + materijal.peek());
			materijal.remove();
			
			
		}
		System.out.println("Runtime: " + (System.currentTimeMillis()-currTime)/1000);
	}
}