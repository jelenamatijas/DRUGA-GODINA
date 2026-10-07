package src;

import src.klase.*;
import java.util.List;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class Main2{
	
	public static void main(String []args){
		Random rand = new Random();
		ArrayList<Takmicar> takmicari = new ArrayList<>();
		IntStream.range(0, 10).forEach(count -> {
			switch(rand.nextInt(3)){
				case 0:
					takmicari.add(new Pilot());
					break;
				case 1:
					takmicari.add(new Planinar());
					break;
				case 2:
					takmicari.add(new Vatrogasac());
					break;
			}
		});
		
		Collections.sort(takmicari, (p1, p2) -> p2.energija - p1.energija);
		takmicari.stream().forEach(System.out::println);
		System.out.println("Ukupna energija " + takmicari.stream().mapToInt(t -> t.energija).sum());
		
		ArrayList<Soba> sobe = new ArrayList<>();
		
		IntStream.range(0, 10).forEach(count -> sobe.add(new Soba()));
		Collections.sort(sobe, (s1, s2) -> s1.frizideri.stream().mapToInt(f -> f ? 1 : 0).sum() - s2.frizideri.stream().mapToInt(f -> f ? 1 : 0).sum());
		
		sobe.stream().forEach(System.out::println);
		System.out.println("Ukupno frizidera " + sobe.stream().mapToInt(soba -> soba.frizideri.stream().mapToInt(f -> f ? 1 : 0).sum()).sum());
	}
}