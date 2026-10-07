package src;

import src.list.*;

public class RemoveThread <T> extends Thread{
	
	private Lista<T> head;
	private int toRemove;
	
	public RemoveThread(Lista<T> head, int toRemove){
		this.head = head;
		this.toRemove = toRemove;
	}
	
	public void run(){
		while(toRemove > 0){
			try{
				System.out.println("Brise se  " + head.obrisi());
				toRemove--;
				Thread.sleep(100);
			} catch(InterruptedException | ListException e){
				e.printStackTrace();
			}
				
			}
	}
		
}