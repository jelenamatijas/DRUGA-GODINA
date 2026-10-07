package pismeni.R_2021_07_30.Z01;

import java.util.*;
import java.io.*;

public class Simulacija{

	static boolean KRAJ = false;
	static int BR_ARTIKAL = 200;
	static int UKUPNO = 6 * BR_ARTIKAL;

	static ArrayList<Kasa> kase = new ArrayList<>();
	static ArrayList<Kupac> kupci = new ArrayList<>();
	static ArrayList<Artikal> artikli = new ArrayList<>(UKUPNO);


	public static void main(String[] args){
		initKase();
		initKupci();
		initArtikli();
		try{
			for(Kupac k: kupci)
				k.start();
			for(Kasa k: kase)
				k.start();


			/*for(Kasa k: kase)
				k.join();
			for(Kupac k: kupci)
				k.join();*/

			System.out.println("Simulacija završena!");
			System.out.println("Neuspješne kupovine:");
			deser();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private static void deser(){
		File[] files = new File(".").listFiles();
		for(File file: files){
			if(file.getName().endsWith(".ser")){
				try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
					Kupac kupac = (Kupac) ois.readObject();
					Kasa kasa = (Kasa) ois.readObject();
					System.out.println(kupac + " " + kasa);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}

	}

	public static void initKase(){
		for(int i = 0; i<2; i++){
			kase.add(new Kasa(TipKase.KORPA, true));
			kase.add(new Kasa(TipKase.KOLICA, true));
			kase.add(new Kasa(TipKase.SAMOSTALNA_KUPOVINA, false));
		}
		kase.forEach(System.out::println);
	}

	public static void initKupci(){
		for(int i = 0; i<10; i++){
			kupci.add(new PravnoLice());
			kupci.add(new Dijete());
			kupci.add(new Odrasli(true));
			kupci.add(new Odrasli(false));
		}
		kupci.forEach(System.out::println);
	}

	public static void initArtikli(){
		for(int i=0; i<BR_ARTIKAL; i++){
			artikli.add(new Hljeb());
			artikli.add(new Mlijeko());
			artikli.add(new Cigarete());
			artikli.add(new Pivo());
			artikli.add(new Sok());
			artikli.add(new Slatkis());
		}
		//artikli.forEach(System.out::println);
	}
}