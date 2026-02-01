import java.util.*;
import java.io.*;
import java.util.stream.*;

class Student implements Runnable {
	static int ID = 1;
	int id;
	String ime, prezime;
	int brojGlasova;
	String univerzitet, fakultet;
	boolean prosaoPrvu = false;
	boolean prosaoDrugu = false;
	
	Student(String ime, String prezime, String un, String f){
		brojGlasova = 0;
		id = ID;
		this.ime = ime;
		this.prezime = prezime;
		univerzitet = un;
		fakultet = f;
	}
	
	public void run(){
		synchronized(Main.lock){
			if(Main.faza == 1){
				int max = Main.drzava.univerziteti.get(univerzitet).fakulteti.get(fakultet).studenti.size();
				int x;
				do{
					x = Main.rand.nextInt(0, max);
				}while(Main.drzava.univerziteti.get(univerzitet).fakulteti.get(fakultet).studenti.get(x).id == id);
				Main.drzava.univerziteti.get(univerzitet).fakulteti.get(fakultet).studenti.get(x).brojGlasova++;
				
			} else if(Main.faza == 2){
				
				List<Student> kandidati = Main.drzava.univerziteti.get(univerzitet).fakulteti.values().stream()
					.flatMap(fa -> fa.studenti.stream())
					.filter(s -> s.prosaoPrvu && s.id != this.id)
					.toList();
				
				if(!kandidati.isEmpty()){
					int x = Main.rand.nextInt(0, kandidati.size());
					kandidati.get(x).brojGlasova++;
				}
				
			} else if(Main.faza == 3){
				
				List<Student> kandidati = Main.drzava.univerziteti.values().stream()
					.flatMap(un -> un.fakulteti.values().stream())
					.flatMap(fa -> fa.studenti.stream())
					.filter(s -> s.prosaoDrugu && s.id != this.id) 
					.toList();
				
				if(!kandidati.isEmpty()){
					int x = Main.rand.nextInt(0, kandidati.size());
					kandidati.get(x).brojGlasova++;
				}
			}
			
		}
		try{
			Thread.sleep(Main.rand.nextInt(100, 500));
		}catch(InterruptedException e){
			System.out.println("Greska pri glasanju studenta: " + this);
		}
		System.out.println(this + " glasao.");
	}
	
	@Override
	public String toString(){
		return ime + " " + prezime + " -> " + univerzitet +", " + fakultet;
	}
}