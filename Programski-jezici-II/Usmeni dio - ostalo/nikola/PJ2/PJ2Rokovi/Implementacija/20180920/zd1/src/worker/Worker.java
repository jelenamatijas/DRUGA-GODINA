package src.worker;

import src.item.Item;
import java.util.ArrayList;
import src.AlarmListener;

import static src.AlarmListener.SYNC_LOCK;
import static src.Main.assemblyLine;

public abstract class Worker extends Thread{
	
	
	protected static AlarmListener alarm;
	private ArrayList<Item> items = new ArrayList<>();
	
	private String name, surname;
	protected int currPos;
	
	protected Worker(String name, String surname) {
		this.name = name; 
		this.surname = surname;
		currPos = 0;
	}
	
	public static void setAlarm(AlarmListener newAlarm){
		alarm = newAlarm;
	}
	
	public abstract boolean work(Item item);
	
	@Override 
		public String toString(){
			return name + " " + surname;
		}
	
} 