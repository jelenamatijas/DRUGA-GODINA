package pismeni.R2018_09_06.Z01;

import java.util.*;
import java.util.concurrent.*;

public class Simulacija{
	static PriorityBlockingQueue<Vozilo> vozila;

	public static void main(String[] args){
		vozila = generisiVozila();

		Scanner scan = new Scanner(System.in);
		while(true){
			if("START".equals(scan.nextLine()))
				new BenzinskaPumpa().start(); new Policajac().start(); break;
		}
	}

	public static PriorityBlockingQueue<Vozilo> generisiVozila(){
		PriorityBlockingQueue<Vozilo> vozila = new PriorityBlockingQueue<Vozilo>();
		for(int i = 0; i<5; i++){
			vozila.add(new Automobil());
			vozila.add(new Kamion());
			vozila.add(new Motocikl());
			vozila.add(new Traktor());
		}
		vozila.stream().forEach(System.out::println);
		return vozila;
	}
}