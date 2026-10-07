package src.main;

import src.vehicle.*;
import src.policija.*;
import java.util.LinkedList;
import java.io.File;
import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.stream.IntStream;
import java.util.Random;

import static src.Main.Pumpa.evidencija;

public class Main{
	
	public static void main(String args[]){
		Random rand = new Random();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		LinkedList<Vehicle> vehicles = new LinkedList<>();
		IntStream.range(0, 20).forEach(count -> {
			switch(rand.nextInt(4){
				case 0:
					vehicles.push(new Auto());
					break;
				case 1:
					vehicles.push(new Kamion());
					break;
				case 2:
					vehicles.push(new Traktor());
					break;
				case 3:
					vehicles.push(new Kamion());
					break;
			}
		});
		
		Pumpa pumpa = new Pumpa(vehicles);
		try{
			while(!"start".equals(in.readLine()));
			pumpa.start();
			pumpa.join();
			File vozila = new File(System.getProperty("user.dir"), "vozila.ser");
			if(vozila.exists())
				vozila.delete();
			vozila.createNewFile();
			ObjectInputStream vozilaStream = new ObjectInputStream(new FileInputStream(vozila));
			System.out.println("Vozila u koja je natoceno pogresno gorivo:");
			evidencija.stream().forEach(System.out::println);
			vozilaStream.writeObject(evidencija);
			vozilaStream.close();
		} catch(IOException | InterruptedException e){
			e.printStackTrace();
		}
		

	}
}