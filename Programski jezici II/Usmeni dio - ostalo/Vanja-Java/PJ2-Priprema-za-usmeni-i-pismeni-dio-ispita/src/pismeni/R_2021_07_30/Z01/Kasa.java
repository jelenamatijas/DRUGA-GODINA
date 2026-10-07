package pismeni.R_2021_07_30.Z01;

import java.util.*;
import java.util.concurrent.*;
import java.io.*;

public class Kasa extends Thread implements Serializable{

	static int usluzenoKupaca = 0;
	String naziv;
	TipKase tip;
	boolean fizickoLice;
	PriorityBlockingQueue<Kupac> kupci = new PriorityBlockingQueue<>();
	ArrayList<String> neuspjesneKupovine = new ArrayList<>();
	static int count;

	public Kasa(TipKase tip, boolean fizickoLice){
		naziv = "Kasa " + (++count);
		this.tip = tip;
		this.fizickoLice = fizickoLice;
	}

	public void run(){
		while(usluzenoKupaca < 40){
			System.out.println("USLUŽENO JE " + (40-kupci.size()) + " KUPACA");
			System.out.println("-------------------------> " + usluzenoKupaca);
			try{
				Kupac kupac = kupci.take();
				double trosak = kupac.kupljeno.stream().mapToDouble(a -> a.cijena).sum();
				boolean imaPivoIliCigarete = kupac.kupljeno.stream().filter(a -> a instanceof ArtikalZaPlus18).count() > 0;
				if((kupac instanceof Maloljetno) && imaPivoIliCigarete){
					ser(kupac, this);
				}else if(kupac.iznos < trosak){
					ser(kupac, this);
				}else{
					System.out.println(kupac + " na kasi: " + naziv + " CIJENA: " + trosak);
					kupac.iznos -= trosak;
				}
				synchronized(this){
						usluzenoKupaca++;
				}
				Thread.sleep(1000);
			}catch(Exception e){
				e.printStackTrace();
			}

		}
	}

	private void ser(Kupac kupac, Kasa kasa){
		String ime = ((kupac instanceof Maloljetno) ? "child_" : "buyer_") + System.currentTimeMillis();
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ime))){
			oos.writeObject(kupac);
			oos.writeObject(kasa);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public String toString(){
		return tip + " " + fizickoLice;
	}
}