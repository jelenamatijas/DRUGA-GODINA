package src;

import src.klase.*;
import java.util.List;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Arrays;

public class Main{
	
	public static Object []mapa = new Object[100];
	
	static {
		Random rand = new Random();
		List<Integer> rands = Arrays.asList(IntStream.range(0, 100).boxed().toArray(Integer[]::new));
		Collections.shuffle(rands);
		List<Integer> vodaRands = rands.subList(0, 10), vatraRands = rands.subList(10, 20), stijenaRands = rands.subList(20, 30);
		for(int count = 0; count < mapa.length; count++){
			if(vatraRands.contains(count))
				mapa[count] = new Vatra();
			else if(vodaRands.contains(count))
				mapa[count] = new Voda(rand.nextBoolean());
			else if(stijenaRands.contains(count))
				mapa[count] = new Stijena(rand.nextDouble()*100);
			else
				mapa[count] = new Object();
		}		
	}
	
	public static void main(String []asr){
		PriorityQueue<Takmicar> takmicari = new PriorityQueue<>((p1, p2) -> p2.energija - p1.energija);
		takmicari.add(new Pilot());
		takmicari.add(new Planinar());
		takmicari.add(new Vatrogasac());
		
		while(takmicari.size() != 0){
			Takmicar t = takmicari.remove();
			System.out.println("Pocinje " + t);
			t.start();
			try{
				t.join();
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}