package src.police;

import src.main.*;
import src.vehicle.*;
import java.util.ArrayList;


public class Police extends Thread{
	private Pumpa station;
	public static ArrayList<Vehicle> evindencija = new ArrayList<>();
	
	
	public Police(Pump station){
		this.station = station;
	}
	
	public void run(){
		while(station.getQueue().size() != 0){
			try{
				Thread.sleep(5000);
				
			} catch(InterruptedException e){
				e.printStackTrace();
			}			
			for(Terminal terminal : station.getTerminals()){
				System.out.println("Provjera stanja na stanici ");
				if(terminal.getVehicle() != null && terminal.getType() != null && ((terminal.getVehicle().getType() instanceof Dizel && terminal.getService() instanceof Loz) || !terminal.getVehicle().getType().getClass().isInstance(terminal.getService()))){
					System.out.println("Evidentirano je da se na terminalu " + terminal + " toci " +  terminal.getService() +  " a vozilo " + terminal.getVehicle + " zahtejva " + terminal.getVehicle().getType());
					evindencija.add(terminal.getVehicle());	
				}
			}
		}	
	}
}