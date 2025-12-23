package src.main;

import src.police.*;
import src.vehicle.*;
import java.util.LinkedList;
import java.util.Random;

public class Terminal extends Thread{
	private Random rand = new Random();
	private Boolean run = true;
	private Vehicle vehicle;
	private Gorivo type;
	private LinkedList<Vehicle> queue;
	 public Terminal(LinkedList<Vehicle> queue){
		 this.queue = queue;
	 }
	 
	 void run(){
		 while(run){
			 synchronized(queue){
				 if(queue.size != 0){
					queue.wait(10000);
					vehicle = queue.pop();
					queue.notifyAll();				
				 } else{
					 run = false;
				 }
			 }
			 
			 if(run){
				int random = rand.nextInt(10);
				if(random == 0){
					type = (vehicle.getType() instanceof Dizel) ? new Benzin() : new Dizel();
				} else if(random == 1){
					type = new Loz();
				} else{
					type = (vehicle.getType() instanceof Dizel) ? new Dizel() : new Benzin():
				}
				while(vehicle.getCurrCap() < vehicle.getCap()){
					System.out.println("Trenutno stanje vozila na terminalu " + super + vehicle.getCurrCap());
					if(vehicle.getCap() - vehicle.getCurrCap() < 10){
						vehicle.setCurrCap(vehicle.getCap());
					} else{
						vehicle.setCurrCap(vehicle.getCurrCap + 10);
					}
				 
				 try{
					 Thread.sleep(1000);
				 } catch(InterruptedException e){
					 e.printStackTrace();
				 }
				 
				}
					System.out.println("Trenutno stanje " + vehicle.getCurrCap() + " punjenje gotovo na terminalu "  + super);
					vehicle = null;
					type = null;
			 }
			 
		 }
		 
	 }
	 
	 public Gorivo getType(){ return type;}
	 public Vehicle getVehicle(){ return vehicle;}
	 
	 
	 @Override 
	 public String toString(){
		 return "Trenutno vozilo " + vehicle + "  trenutno se sipa " + type + " na terminalu " + super;
	 }
}