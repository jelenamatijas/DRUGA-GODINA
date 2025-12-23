package src;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import src.klase.*;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Collections;


public class Main{
	
	public static Polje []mapa = new Polje[50];
	
	static {
		Random rand = new Random();
		List<Integer> toShuffle = IntStream.range(0, 50).boxed().collect(Collectors.toList());
		Collections.shuffle(toShuffle);
		List<Integer> bonusPos = toShuffle.subList(0, 5), vodaPos = toShuffle.subList(5, 10),
			vatraPos = toShuffle.subList(10, 15), kamenPos = toShuffle.subList(15, 20);
		IntStream.range(0, 50).boxed().forEach(count ->{
			
			if(bonusPos.contains(count)){
				mapa[count] = new Polje(null, rand.nextInt(4) + 2);
			} else if(vodaPos.contains(count)){
				mapa[count] = new Polje(Prepreka.newVoda(), 0);
			} else if(vatraPos.contains(count)){
				mapa[count] = new Polje(Prepreka.newVatra(), 0);
			} else if(kamenPos.contains(count)){
				mapa[count] = new Polje(Prepreka.newKamen(), 0);
			} else
				mapa[count] = new Polje(null, 0);
			
		});
	}
	
	public static void main(String []args){
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		List<Takmicar> takmicari = Arrays.asList(Takmicar.newPjesak(), Takmicar.newPilot(), Takmicar.newVozac());
		
		try{
			
			while(!"start".equals(in.readLine()));
			
		} catch(IOException e){
			e.printStackTrace();
		}
		
		takmicari.stream().forEach(t -> t.start());
		
		for(int count = 0; count < takmicari.size(); count++){
			try{
				
				takmicari.get(count).join();
				
			} catch(InterruptedException e){
				e.printStackTrace();
				
			}
		}
		
		Collections.sort(takmicari, (t1, t2) -> t2.score - t1.score);
		
		System.out.println("Rezultati: ");
		takmicari.stream().forEach(t -> System.out.println(t + " " + t.score));
		
		System.out.println("Pobjedio je " + takmicari.get(0));
		
	}
	
}