package src.main;

import java.util.PriorityQueue;
import java.util.concurrent.LinkedBlockingQueue;
import src.transport.*;
import java.util.Random;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.Serializable;
import java.util.Comparator;


public class Main{
	
	public static final LinkedBlockingQueue<Person> OFFENDERS = new LinkedBlockingQueue<>();
	
	public static void main(String args[]){
		BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
		
		LinkedBlockingQueue<CustomsObject> trucks = new LinkedBlockingQueue<>();
		PriorityQueue<CustomsObject> ordinaries = new PriorityQueue<>((Comparator<CustomsObject> & Serializable)(t, u)->{
			if(t instanceof Person && u instanceof Person)
				return 0;
			if(t instanceof Person)
				return 1;
			else if(u instanceof Person)
				return -1;
			else 
				return 0;
		});	
		
		System.out.println("Kreiranje prelaznika...");
		for(int i = 0; i < 20; i++){
			switch(new Random().nextInt(4)){
				case 0:
						ordinaries.add(new Person());
						break;
				case 1:
						trucks.add(new Truck());
						break;
				case 2:
						ordinaries.add(new Bus());
						break;
				case 3:
						ordinaries.add(new Person());
						break;
			}
		}	
		
		try{
		File trucksFile = new File(System.getProperty("user.dir"), "kamioni.ser");
		File ordinariesFile = new File(System.getProperty("user.dir"), "prioritetniRed.ser");
		
		trucksFile.createNewFile();
		ordinariesFile.createNewFile();
		
		ObjectOutputStream trucksStream = new ObjectOutputStream(new FileOutputStream(trucksFile));
		ObjectOutputStream ordinariesStream = new ObjectOutputStream(new FileOutputStream(ordinariesFile)); 
		

		trucksStream.writeObject(trucks);
		trucksStream.close();
		ordinariesStream.writeObject(ordinaries);
		ordinariesStream.close();
		
		}	catch(Exception e){
			e.printStackTrace();
			}		
		Customs customs = new Customs(new CustomsTerminal(trucks), new CustomsTerminal(ordinaries));
		
		System.out.println("Unesite START za pocetak");
		try{
			while(!"START".equals(cin.readLine()));
			customs.start();
			customs.join();			
		} catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Osobe koje su pocinile prekrsaj");
		OFFENDERS.stream().forEach(System.out::println);
		//ostao compile
	}
}