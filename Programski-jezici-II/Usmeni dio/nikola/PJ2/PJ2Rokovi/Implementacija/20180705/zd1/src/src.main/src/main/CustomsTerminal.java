package src.main;

import src.transport.*;
import java.util.AbstractQueue;
import static src.main.Main.OFFENDERS;


public class CustomsTerminal extends Thread{
	
	private AbstractQueue<CustomsObject> queue;
	
	public CustomsTerminal(AbstractQueue<CustomsObject> queue){
		this.queue = queue;
	}
	
	private synchronized void checkCustoms(Object toCheck) throws Exception{
			if(toCheck instanceof Truck){
				System.out.println("Obradjuje se carina za " + toCheck + " u iznosu od " + ((Truck)toCheck).getCustomsValue()*0.15);
			}
			System.out.println("Pregled osoba");
			for(Person personAtCustoms: ((Vehicle)toCheck).getPassengers()){
				checkCustomsForPerson(personAtCustoms);
				policeCheck(personAtCustoms);
			}
	}
	
	private void checkCustomsForPerson(Person toCheck) throws Exception{
		System.out.println("Obracun carine za " + toCheck);
		Thread.sleep(2000);
		if(toCheck.anythingToDeclare()){
			System.out.println("Obracunata je carina u iznosu " + toCheck.getCustomsValue()*0.1);
		} else{
				if(toCheck.getCustomsValue() == null)
					System.out.println("Osoba nije imala nista za prijaviti");
				else 
					System.out.println("Osoba je pokusala da prokrijumcari robu vrijednosti " + toCheck.getCustomsValue() + " te je naplacena kazna u iznosu od " + ((toCheck.getCustomsValue()*1.1)));
					OFFENDERS.add(toCheck);
				}		
		
	}
	
	private void policeCheck(Person toCheck) throws Exception {
		System.out.println("Provjera dokumenta za osobu " + toCheck);
		Thread.sleep(1000);
		if(toCheck.getHasPassport()){
			System.out.println("Dokumenta su uredna");
		} else{
			System.out.println("Dokumenta nisu uredna");
			OFFENDERS.add(toCheck);
		}
			
	}
	
	public void run(){
		try{
			while(queue.size() > 0){
			Object toCheck = queue.remove();
			if(toCheck instanceof Vehicle){
				System.out.println("Obradjuje se vozilo " + toCheck);
				checkCustoms(((Vehicle)toCheck));
			} else{
				System.out.println("Obradjuje se osoba" + toCheck);
				checkCustomsForPerson(((Person)toCheck));
				policeCheck(((Person)toCheck));
			}
			
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}