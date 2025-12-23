package src;


import src.gost.*;
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.io.Serializable;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


public class Main{
	
	public static HashMap<String, Double> TABELA_USLUGA = new HashMap<>();
	
	public static void main(String args[]){
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Gost> gosti = new ArrayList<>();
		ArrayList<Gost> gostiRestoran = new ArrayList<>();
		ArrayList<Gost> gostiBazen = new ArrayList<>();
		PriorityQueue<Gost> salaQueue = new PriorityQueue<>((Comparator & Serializable) (e1, e2) -> {
			if(e1 instanceof GostFirme && e2 instanceof GostFirme)
				return 0;
			if(e1 instanceof ObicniGost && e2 instanceof ObicniGost)
				return 0;
			if(e1 instanceof GostFirme)
				return -1;
			else 
				return 1;
		});
		Random random = new Random();
		TABELA_USLUGA.put("Dodatni obrok", (double)(random.nextInt(100) + 10));
		TABELA_USLUGA.put("Korištenje projektora", (double)(random.nextInt(100) + 10));
		TABELA_USLUGA.put("Korištenje racunara", (double)(random.nextInt(100) + 10));
		TABELA_USLUGA.put("Korištenje džakuzi bazena", (double)(random.nextInt(100) + 10));
		TABELA_USLUGA.put("Koktel", (double)(random.nextInt(100) + 10));
		IntStream.range(0, 10).forEach( i -> {
			gosti.add(new GostFirme());
			gosti.add(new ObicniGost());
		});
		
		IntStream.range(0, 20).forEach(count ->{
			if(count < 5){
				Gost tmp = gosti.get(random.nextInt(gosti.size()));
				gostiRestoran.add(tmp);
				gosti.remove(tmp);
			} else if(count >= 5 && count < 10){
				Gost tmp = gosti.get(random.nextInt(gosti.size()));
				gostiBazen.add(tmp);
				gosti.remove(tmp);
			} else{
				Gost tmp = gosti.get(random.nextInt(gosti.size()));
				salaQueue.add(tmp);
				gosti.remove(tmp);
			}
		});
		
		try{
			File ulazakUSalu = new File(System.getProperty("user.dir"), "ulazakUSalu.ser");
			if(ulazakUSalu.exists())
				ulazakUSalu.delete();
			ulazakUSalu.createNewFile();
			ObjectOutputStream salaQueueStream = new ObjectOutputStream(new FileOutputStream(ulazakUSalu));
			salaQueueStream.writeObject(salaQueue);
			salaQueueStream.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Red za salu");
		salaQueue.stream().forEach(System.out::println);
		RestoranBazenObserve thread1 = new RestoranBazenObserve(new BazenObserve(gostiBazen), new RestoranObserve(gostiRestoran));
		SalaObserve thread2 = new SalaObserve(salaQueue);
		try{
			while(!"start".equals(in.readLine()));
		} catch(Exception e){
			e.printStackTrace();
		}		
		
		thread1.start();
		thread2.start();
		
	}
}