package pismeni.R_2021_07_30.Z01;

import java.util.*;
import java.io.*;
import java.util.stream.*;

public abstract class Kupac extends Thread implements Comparable<Kupac>, Serializable{

	ArrayList<Artikal> kupljeno;
	String naziv;
	double iznos;
	boolean korpa; // ako je false, onda su kolica
	static int count;
	boolean prioritet = false;


	public Kupac(){

	}

	public Kupac(double iznos){
		naziv = "Kupac " + (++count);
		this.iznos = iznos;
		korpa = new Random().nextInt(2) == 1;
		kupljeno = new ArrayList<>();
	}


	public abstract boolean isFizickoLice();

	public String toString(){
		return naziv + " iznos: " + iznos + " korpa: " + korpa + " " + kupljeno;
	}

	public void run(){
		int i = 0;
		while(i < 20){
			int index = new Random().nextInt(Simulacija.UKUPNO);
			Artikal a = Simulacija.artikli.get(index);
			synchronized(a){
				if(!a.kupljen){
					kupljeno.add(a);
					a.kupljen = true;
					i++;
				}
			}
		}

		ArrayList<Kasa> kase = Simulacija.kase.stream().sorted((k1, k2) -> k1.kupci.size() - k2.kupci.size()).collect(Collectors.toCollection(ArrayList::new));
		// TODO ISPRAVITI
		for(Kasa kasa : kase){
			synchronized(kasa){
				if(this.isFizickoLice() && kasa.fizickoLice){
					if(korpa && (kasa.tip.equals(TipKase.KORPA) || kasa.tip.equals(TipKase.SAMOSTALNA_KUPOVINA))){
						kasa.kupci.put(this); break;
					}
					else if(!korpa && (kasa.tip.equals(TipKase.KOLICA) || kasa.tip.equals(TipKase.SAMOSTALNA_KUPOVINA))){
						kasa.kupci.put(this); break;
					}
				}else if(!this.isFizickoLice() && !kasa.fizickoLice){
					if(korpa && (kasa.tip.equals(TipKase.KORPA) || kasa.tip.equals(TipKase.SAMOSTALNA_KUPOVINA))){
						kasa.kupci.put(this); break;
					}
					else if(!korpa && (kasa.tip.equals(TipKase.KOLICA) || kasa.tip.equals(TipKase.SAMOSTALNA_KUPOVINA))){
						kasa.kupci.put(this); break;
					}
				}
			}
		}
		System.out.println("===================================================================================================");
	}

	public int compareTo(Kupac k){
		return Boolean.compare(k.prioritet, this.prioritet);
	}
}