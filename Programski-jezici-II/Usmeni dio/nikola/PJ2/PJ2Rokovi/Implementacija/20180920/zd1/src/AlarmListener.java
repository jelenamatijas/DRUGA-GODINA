package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import src.storage.Storage;
import java.util.concurrent.locks.ReentrantLock;


public class AlarmListener extends Thread {
	public static final ReentrantLock SYNC_LOCK = new ReentrantLock();
	private Storage company;
	static BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
	
	
	public AlarmListener(Storage company) {
		this.company = company;
	}
	
	@Override 
		public void run(){
				Alarm alarm = new Alarm();
				alarm.start();
				while(true){
					synchronized(this){
						try{
							if("ALARM".equals(s.readLine())){
								try{
									SYNC_LOCK.lock();
									System.out.println("pause");
									synchronized(alarm){
										alarm.wait();
									}
								} finally{
									SYNC_LOCK.unlock();									
								}
							}
						} catch(Exception e){
							e.printStackTrace();
						}			
					}
					notifyAll();
				}
		}
		
}

class Alarm extends Thread{
	public void run(){
		
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			synchronized(this){
				try{
					while(!"ALARM_END".equals(s.readLine()));
				} catch(Exception e){
					e.printStackTrace();
				}
				
				notifyAll();
			}
		}
	}
}