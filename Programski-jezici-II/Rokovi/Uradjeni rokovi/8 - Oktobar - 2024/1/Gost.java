import java.io.*;
import java.util.*;

class Gost extends Thread{
	String ime, prezime;
	int godine;
	static int id = 1;
	boolean imaKupon;
	boolean bazen;
	boolean masaza;
	boolean sauna;
	
	public Gost(){
		prezime = "Prezime" + id;
		ime = "Ime" + id++;
		this.imaKupon = true;
		bazen = false;
		masaza = false;
		sauna = false;
		godine = Main.rand.nextInt(20, 40);
	}
	
	private void naplati(String usluga){
		GostLicniTrosak g = (GostLicniTrosak)this;
		double naplata = Main.rand.nextDouble(1, Math.min(12, g.novac));
		System.out.println("Naplata za " + usluga + " od " + naplata + "KM za gosta:\n" + this);
		System.out.println("Novac " + g.novac + " umanjen.");
		g.novac -= naplata;
	}
	
	@Override
	public void run(){
		if(this.sauna){
			if(!imaKupon){
				synchronized(Main.lock){
					try(PrintWriter pw = new PrintWriter(new FileWriter("pristupSauniOtkazan.txt", true))){
						pw.println(this);
					}catch(IOException e){
						System.out.println("GRESKA pri upisu u fajl.");
					}
				}
				System.out.println(this + " NEMA KUPON ZA PRISTUP SAUNI.");
			}else{
				if(!(this instanceof FirmaInterface)){
					this.naplati("saune");
				}else{
					System.out.println(this + " JE U SAUNI.");
				}
			}
			Main.sauna.remove(this);
			try{
				sleep(3000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
		}else if(this.masaza){
			if(!(this instanceof FirmaInterface)){
				this.naplati("masazu");
			}else{
				System.out.println(this + " JE NA MASAZI.");
			}
			
			try{
				sleep(3000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
		}else if(this.bazen){
			if(!(this instanceof FirmaInterface)){
				this.naplati("bazen");
			}else{
				System.out.println(this + " JE NA BAZENU.");
			}
			try{
				sleep(2000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public String toString(){
		return "Ime: " + ime + " Prezime: " + prezime + " Godine: " + godine + " Ima kupon: " + imaKupon;
	}
}