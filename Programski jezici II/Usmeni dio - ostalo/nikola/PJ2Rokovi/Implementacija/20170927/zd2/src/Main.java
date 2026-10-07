package src;

import src.traka.Traka;
import src.traka.masine.*;
import java.util.LinkedList;
import java.util.stream.IntStream;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;

/*
	Algoritam radi na sljedeci nacin. Za svaki materijal je poznato koliko treba da se od njega napravi potpun proizvod, tokom prolazka kroz odredjenu masinu. Posto se trazi da zavrse u priblizno istom vremenu, racuna se koliko vremena 
	bi trebalo da svaka masina pojedinacno obradi sav materijal jednom tipa. Zatim se trazi aritmetricka sredina vremena, kojoj sve masine treba da teze. Da bi to postigle djeli se dobijeno srednje vrijeme
	sa vremenom koje je potrebno jednoj masini da obradi jedan komad materijala, i cjeli dio se se uzima kao kolicina materijala koji treba poslati masini. Ako ima viska materijala, masini koja je dobila najmanje se
	daje visak. Radeci ovo za sav materijal dobijaju se skupovi sirovina koji su optimalni.
	*/


public class Main{
	
	
	public static void main(String []args){
	LinkedList<Materijal> m1 = new LinkedList<>(), m2 = new LinkedList<>(), m3 = new LinkedList<>();
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	Random rand = new Random();
	IntStream.range(0, 10).forEach(count ->{
		switch(rand.nextInt(3)){
			case 0:
				m1.push(new Staklo());
				m2.push(new Plastika());
				m3.push(new Metal());
				break;
			case 1:
				m2.push(new Staklo());
				m1.push(new Plastika());
				m3.push(new Metal());
				break;
			case 2:
				m3.push(new Staklo());
				m2.push(new Plastika());
				m1.push(new Metal());
				break;
		}
	});
	Traka t1 = new Traka(m1, "Ploca"), t2 = new Traka(m2, "Boca"), t3 = new Traka(m3, "Kuciste");
	try{
		System.out.println("Unesite START za pocetak");
		while(!"START".equals(in.readLine()));
	} catch(IOException e){
		e.printStackTrace();
	}
	
	t1.start();
	t2.start();
	t3.start();
	try{
		t1.join();
		t2.join();
		t3.join();
	} catch(InterruptedException e){
		e.printStackTrace();
	}
}
}