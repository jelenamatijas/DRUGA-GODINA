package pismeni.R2018_09_06.Z01;

import java.util.*;
import java.io.*;

public class BenzinskaPumpa extends Thread {

	static ArrayList<Mjesto> mjesta = new ArrayList<>();
	ArrayList<Vozilo> serVozila = new ArrayList<>();

	public void run(){
		initMjesta();
		try{
			while(!Simulacija.vozila.isEmpty()){
				System.out.println("Broj vozila u redu: " + Simulacija.vozila.size());
				boolean ulje = new Random().nextInt(100) < 10;
				boolean pogresnoGorivo = new Random().nextInt(100) < 10;
				Vozilo vozilo = null;
				synchronized(this){
					vozilo = Simulacija.vozila.peek();
				}

				//Simulacija.vozila.remove(vozilo);
				if(vozilo.gorivo.equals(Gorivo.BENZIN) && (ulje || pogresnoGorivo))
					serVozila.add(vozilo);
				if(vozilo.gorivo.equals(Gorivo.DIZEL) && pogresnoGorivo)
					serVozila.add(vozilo);

				for(Mjesto m: mjesta){
					if(m.vozilo == null){
						if(pogresnoGorivo && vozilo.gorivo.equals(Gorivo.DIZEL))
							m.gorivo = Gorivo.BENZIN;
						else if(pogresnoGorivo && vozilo.gorivo.equals(Gorivo.BENZIN))
							m.gorivo = Gorivo.DIZEL;
						else if(ulje)
							m.gorivo = Gorivo.ULJE;
						else if(!pogresnoGorivo)
							m.gorivo = vozilo.gorivo;
						m.vozilo = vozilo;
						break;
					}
				}
				Thread.sleep(1000);
			}

			for(Mjesto m: mjesta)
				m.join();
			System.out.println("Simulacija je završena!");
			System.out.println("vozila u koje je sipano pogrešno gorivo i u koje je sipano lož ulje");
			serVozila.forEach(System.out::println);
			// vozila.ser vozila u koje je sipano pogrešno gorivo i u koje je sipano lož ulje
			try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("vozila.ser"))){
				for(Vozilo v: serVozila)
					oos.writeObject(v);
			}catch(Exception e){
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void initMjesta(){
		for(int i =0; i<4; i++){
			mjesta.add(new Mjesto());
			mjesta.get(i).start();
		}
	}
}