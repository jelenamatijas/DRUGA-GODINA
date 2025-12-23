package src;

import src.storage.*;
import src.item.*;
import src.worker.*;
import java.util.Random;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import static src.worker.Worker.SYNC_LOCK;

public class Main {
	
	public static long SPEED = 3000;
	public static ArrayList<Item> storage = new ArrayList<>();
	public static ArrayList<Item> assemblyLine = new ArrayList<>();
	
	public static void main(String []args) {
		
		for(int count = 0; count < 5; count++) {
			storage.add(new Box(new Random().nextDouble()*10, new Object()));
			storage.add(new Sheet(new Random().nextDouble()*10));
			storage.add(new Cable(new Random().nextDouble()*10));
		}		

		Storage company = new Storage();
		AlarmListener alarm = new AlarmListener(company);
		Worker.setAlarm(alarm);
		company.start();
		alarm.start();
		
	}
}