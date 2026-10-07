package src.worker;

import src.item.*;

import static src.AlarmListener.SYNC_LOCK;
import static src.Main.storage;
import static src.Main.SPEED;
import static src.Main.assemblyLine;

public class ForkliftDriver extends Driver {
	
	public ForkliftDriver(String name, String surname){
		super(name, surname);
	}
	
	public boolean work(Item item) {
		return move(item);
	}
	
	public boolean move(Item item) {
		if(assemblyLine.contains(item))
			System.out.println(item + " je vec na traci");
		else if(item instanceof Box) {
				System.out.println(this + " prevozi" + item);
				return true;
		} else
			System.out.println(this + " ne moze da prevozi" + item);
		
		return false;
	}
	
	public void run(){
	synchronized(this){
			while(currPos < storage.size()) {
				if(SYNC_LOCK.isLocked()){
					synchronized(alarm){
						try{
							alarm.wait();
						} catch(Exception e){
							e.printStackTrace();
							}
					}					
				}
					synchronized(storage.get(currPos)) {
						System.out.println(this + " je na poziciji " + currPos);
						if(move(storage.get(currPos))){
							assemblyLine.add(storage.get(currPos));
							try {
									Thread.sleep(SPEED);
								} catch(Exception e){
									e.printStackTrace();
							}
						}
					}
			currPos++;
		}
		notify();
		}
	}	
	
	@Override 
		public String toString() {
			return super.toString() + " viljuskara";
		}
}