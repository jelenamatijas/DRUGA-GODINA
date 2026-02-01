package src.storage;


import src.worker.*;

public class Storage extends Thread {
	
		private Locksmith locksmithThread = new Locksmith("Mirko", "Mirkovic");
		private Welder welderThread = new Welder("Darko", "Darkovic");
		private ForkliftDriver forkliftDriverThread = new ForkliftDriver("Marko", "Markovic");
		private Driver driverThread = new Driver("Zarko", "Zarkovic");
		
		public void run() {
			forkliftDriverThread.start();
			driverThread.start();
			synchronized(this){
				synchronized(forkliftDriverThread){
					try{
						forkliftDriverThread.wait();
					} catch(Exception e){
						e.printStackTrace();
						}
				}	
				synchronized(forkliftDriverThread){
					try{
						forkliftDriverThread.wait();
					} catch(Exception e){
						e.printStackTrace();
						}
				}			
			}
			locksmithThread.start();
			welderThread.start();
		}
}