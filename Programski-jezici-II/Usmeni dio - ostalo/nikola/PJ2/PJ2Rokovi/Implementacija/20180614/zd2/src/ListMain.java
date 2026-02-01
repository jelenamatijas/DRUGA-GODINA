package src;

import src.list.*;
import java.util.Random;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;



public class ListMain{
	
	public static void main(String args[]){
		long start = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Lista<Integer> oList = null;
		File file = new File(System.getProperty("user.dir"), "lista.ser");
		if(file.exists()){
			try{
				ObjectInputStream read = new ObjectInputStream(new FileInputStream(file));
				oList = (Lista<Integer>)read.readObject();		
				read.close();
			} catch(ClassNotFoundException | IOException e){
				e.printStackTrace();
			}
				
			
		} else
			oList = new Lista<>(Integer.valueOf(new Random().nextInt()));
		System.out.println("Stanje liste pre pocetka simulacije: " + oList);
		AddThread<Integer> add = new AddThread<>(oList, new Random().nextInt(92));
		RemoveThread<Integer> remove = new RemoveThread<>(oList, new Random().nextInt(80));
		try{
			
			while(!"start".equals(in.readLine()));
			add.start();
			remove.start();			
			add.join();
			remove.join();
			ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(file));
			write.writeObject(oList);
			write.close();
			
		} catch(InterruptedException | IOException e){
			e.printStackTrace();
		}
			
		while(((double)(System.currentTimeMillis() - start))/60_000 < 2);
		//run time u minutima
		System.out.println("Run time " + ((double)(System.currentTimeMillis() - start)/60_000));
	}
}