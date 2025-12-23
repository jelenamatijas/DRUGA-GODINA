package src;

import src.list.Lista;
import java.util.Random;


public class AddThread <T> extends Thread{
	
	private Lista<T> head;
	private int toAdd;
	
	public AddThread(Lista<T> head, int toAdd){
		this.head = head;
		this.toAdd = toAdd;
	}
	
	public void run(){
		while(toAdd > 0){
			Integer o = Integer.valueOf(new Random().nextInt());
			System.out.println("Dodaje se " + o);
			head.dodaj((T)o);
			toAdd--;
			try{
				Thread.sleep(100);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
	}
}	