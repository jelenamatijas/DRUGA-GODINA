package src;

import src.hall.Hall;
import src.visitor.*;
import java.util.Random;
import java.io.FileWriter;
import java.util.Vector;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.IOException;
import java.lang.InterruptedException;
import java.io.File;

public class Main {
	private static final int ROWS = 6, COLLUMNS = 6;
	private static final String KIDS_HALL_NAME = "Djecija sala", ADULTS_HALL_NAME = "Sala za odrasle";
	
	public static void main(String []args) {
		Scanner s = new Scanner(System.in);
		int initialCap = (int)((double)(ROWS*COLLUMNS)*0.6);
		Vector<Visitor> childrenHallGroup = new Vector<Visitor>(), adultsHallGroup = new Vector<Visitor>();
		Hall childrenHall, adultsHall;
		for(int i = 0; i < initialCap; i++){
			int counter = 0;
			Adult adultForKids = new Adult(new Random().nextInt(5) + 1);
				childrenHallGroup.add(adultForKids);
				while(counter < adultForKids.getKids()){
					childrenHallGroup.add(new Kid());
					i++;
					counter++;
					if(i == initialCap)
						break;
				}
		}
		for(int i = 0; i < initialCap; i++)
			adultsHallGroup.add(new Adult(0));
		for(int i = 0; i < initialCap; i++){
			int counter = 0;
			Adult adultForKids = new Adult(new Random().nextInt(5) + 1);
			childrenHallGroup.add(adultForKids);
			while(counter < adultForKids.getKids()){
				childrenHallGroup.add(new Kid());
				counter++;
			}
		}
		for(int i = 0; i < initialCap - adultsHallGroup.size(); i++)
			adultsHallGroup.add(new Adult(0));
		childrenHall = new Hall(KIDS_HALL_NAME,  ROWS, COLLUMNS, childrenHallGroup);
		adultsHall = new Hall(ADULTS_HALL_NAME, ROWS, COLLUMNS, adultsHallGroup);
		Thread t1 = new Thread(childrenHall), t2 = new Thread(adultsHall);
		System.out.println("Unesite START");
		while(!"START".equals(s.nextLine()));
		try{
			t1.start();
			t2.start();
			t1.join();
			t2.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		try{
			File results = new File(	System.getProperty("user.dir"), "rezultati.txt");
			results.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(results));
			writer.write(childrenHall.toString());
			writer.newLine();
			writer.write(adultsHall.toString());
			writer.flush();
			} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
