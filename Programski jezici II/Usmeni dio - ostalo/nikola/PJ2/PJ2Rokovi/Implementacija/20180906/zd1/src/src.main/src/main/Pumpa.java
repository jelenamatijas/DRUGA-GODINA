package src.main; 

import src.vehicle.*;
import java.util.LinkedList;
import java.util.stream.IntStream


public class Pumpa extends Thread{
	private LinkedList<Vehicle> vehicles;
	private LinkedList<Terminal> terminals = new LinkedList<>();
	
	public Pumpa(LinkedList<Vehicle> vehicles){
		IntStream.range(0, 4).forEach(e -> terminals.push(new Terminal(vehicles)));
	}
	
	public boolean isRunning(){ return vehicles.size() == 0}
	
	public LinkedList<Terminal> getTerminals(){
		return terminals;
	}
	
	public void run(){
		for(Terminal t : terminals){
			t.start();
		}
		
		for(Terminal t : terminals){
			try{
				t.join();
			} catch(InterruptedException e){
				e.printStackTrace();
			}
			
		}		
	}
	
	@Override 
	
	public String toString(){
		String toReturn = "";
		for(Terminal terminal : terminals){
			if(terminal.isRunning())
				toReturn += terminal + "\n";
		}
		
		return toReturn;
	}
}