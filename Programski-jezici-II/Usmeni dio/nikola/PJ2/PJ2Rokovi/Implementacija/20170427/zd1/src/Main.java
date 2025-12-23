package src;

import src.klase.*;
import java.util.stream.IntStream;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;


public class Main{
	
	public static Polje [][]mapa = new Polje[50][50];
	public static boolean parniDan = true; //valjda ako je danas parni dan za jednu crkvu, parni dan je za sve
	
	static {
		int brojCrkva = 0, brojMuzej = 0, brojSpomenik = 0, brojZabavniPark = 0;
		Random rand = new Random();
		TreeMap<Integer, Integer> crkvaPos = new TreeMap<>(), muzejPos = new TreeMap<>(), parkPos = new TreeMap<>(), spomenikPos = new TreeMap<>();
		List<Integer> tmpX = IntStream.range(0, 50).boxed().collect(Collectors.toList());
		Collections.shuffle(tmpX);
		List<Integer> crkvaX = tmpX.subList(0, 20);
		Collections.shuffle(tmpX);
		List<Integer> crkvaY = tmpX.subList(0, 20);
		
		IntStream.range(0, 20).forEach(count -> crkvaPos.put(crkvaY.get(count), crkvaX.get(count)));
		
		Collections.shuffle(tmpX);
		List<Integer> spomenikX = tmpX.subList(0, 20);
		Collections.shuffle(tmpX);
		List<Integer> spomenikY = tmpX.subList(0, 20);
		
		IntStream.range(0, 20).forEach(count -> spomenikPos.put(spomenikY.get(count), spomenikX.get(count)));
		
		Collections.shuffle(tmpX);
		List<Integer> zabavniParkX = tmpX.subList(0, 20);
		Collections.shuffle(tmpX);
		List<Integer> zabavniParkY = tmpX.subList(0, 20);
		
		IntStream.range(0, 20).forEach(count -> parkPos.put(zabavniParkY.get(count), zabavniParkX.get(count)));
		
		Collections.shuffle(tmpX);
		List<Integer> muzejX = tmpX.subList(0, 20);
		Collections.shuffle(tmpX);
		List<Integer> muzejY = tmpX.subList(0, 20);
		
		IntStream.range(0, 20).forEach(count -> muzejPos.put(muzejY.get(count), muzejX.get(count)));
		
		for(Integer y = 0; y < 50; y++){
			for(Integer x = 0; x < 50; x++){
				mapa[y][x] = new Polje(x, y);
				if(x == crkvaPos.get(y)){
					mapa[y][x].atrakcije.add(Atrakcija.newCrkva());
					brojCrkva++;
				} 
				if(x == spomenikPos.get(y)){
					mapa[y][x].atrakcije.add(Atrakcija.newSpomenik(Long.toHexString(Double.doubleToLongBits(rand.nextDouble()))));
					brojSpomenik++;
					
				}
				if(x == parkPos.get(y)){
					mapa[y][x].atrakcije.add(Atrakcija.newZabavniPark());
					brojZabavniPark++;
				}
				if(x == muzejPos.get(y)){
					mapa[y][x].atrakcije.add(Atrakcija.newMuzej(Long.toHexString(Double.doubleToLongBits(rand.nextDouble()))));
					brojMuzej++;
				}
			}
		}
		
		System.out.println("Broj muzeja" + brojMuzej + " Broj crkava " + brojCrkva + " Broj spomenika " + brojSpomenik + " Broj zabavnih parkova" + brojZabavniPark); 
	}
	
	public static void main(String args[]){
		Turist []turisti = new Turist[50];
		
		IntStream.range(0, 50).forEach(count -> turisti[count] = new Turist());
		System.out.println("Unesite start za pocetak simulacije ");
		
		try(BufferedReader in = new BufferedReader(new InputStreamReader(System.in))){
			while(!"start".equals(in.readLine()));
		} catch(IOException e){
			e.printStackTrace();
		}
		IntStream.range(0, 50).forEach(count -> turisti[count].start());
		
		for(int count = 0; count < 50; count++){
			try{
				turisti[count].join();
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
		List<Turist> turistiList = Arrays.asList(turisti);
		
		Collections.sort(turistiList, (t1, t2) -> t2.brojMjesta -t1.brojMjesta);
		
		turistiList.stream().forEach(t -> System.out.println(t + " procentualno broj posjecenih atrackija"  + ((double)t.brojMjesta*1.25)));
		
	}
}