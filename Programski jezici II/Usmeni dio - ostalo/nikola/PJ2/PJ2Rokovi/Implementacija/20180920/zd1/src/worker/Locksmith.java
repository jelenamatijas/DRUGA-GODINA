package src.worker;

import src.item.*;

import static src.Main.SPEED;
import static src.Main.assemblyLine;
import static src.AlarmListener.SYNC_LOCK;

public class Locksmith extends Worker {
	
	public Locksmith(String name, String surname){
		super(name, surname);	
	}
	
	public boolean work(Item item) {
		if(item instanceof Box) 
			System.out.println(this + " otvara kutiju " + item);
		else {
			System.out.println(this + " ne moze da otvori objekat " + item);
			return false;
		}
		return true;
	}
	
		public void run(){
		while(currPos < assemblyLine.size()) {
				if(SYNC_LOCK.isLocked()){
					synchronized(alarm){
						try{
							alarm.wait();
						} catch(Exception e){
							e.printStackTrace();
							}
					}					
				}
				synchronized(assemblyLine.get(currPos)) {
					try{
						Thread.sleep(SPEED);
					} catch(Exception e) {
						e.printStackTrace();
					}
					System.out.println(this + " je na poziciji " + currPos);
					work(assemblyLine.get(currPos));
				}
			currPos++;
		}
	}
	
	@Override 
		public String toString() {
			return "Bravar " +  super.toString();
		}
}