import java.util.*;

class KomunikacioniModul extends Thread{
	static public enum Prioritet{
		INFO, UPOZORENJE, KRITICNO;
	}
	
	volatile boolean modulRadi;
	
	KomunikacioniModul(){
		modulRadi = true;
	}
	
	public void run(){
		while(modulRadi){
			synchronized(Main.lockKomunikacija){
				if(!Main.poruke.isEmpty()){
					for(Poruka p : Main.poruke){
						System.out.println(p);
					}
					Main.poruke.clear();
				}
			}
			try{
                sleep(1000);
            }catch(InterruptedException e){  
                modulRadi = false;
                break;  
            }
		}
	}
}