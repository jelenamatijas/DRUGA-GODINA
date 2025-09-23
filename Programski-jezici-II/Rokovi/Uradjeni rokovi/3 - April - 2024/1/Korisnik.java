import java.util.*;
import java.io.*;


class Korisnik extends Thread{
	String ime, prezime, oblastInteresovanja;
	int godine, brClanske, red;
	static int redniBroj =0;
	
	public Korisnik(){
		ime = "Ime" + Main.rand.nextInt(11);
		prezime = "Prezime" + Main.rand.nextInt(11);
		int x = Main.rand.nextInt(3);
		if(x == 0){
			oblastInteresovanja = "BELETRISTIKA";
		}else if(x == 1){
			oblastInteresovanja = "KNJIGE ZA DJECU";
		}else{
			oblastInteresovanja = "STRUCNA LITERATURA";
		}
		red = redniBroj++;
		godine = Main.rand.nextInt(30) + 1;
		brClanske = Main.rand.nextInt(3000) + 1000;
		
	}
	private static Object lock = new Object();
	
	private void upisi(String naslov){
		synchronized(lock){
			try(PrintWriter pw = new PrintWriter(new FileWriter(Main.file, true))){
				pw.println(brClanske + "#" + "\"" +naslov+"\"");
				(Main.brojPodignutihKnjiga)++;
				System.out.println(brClanske + " je IZNAJMIO knjigu " + naslov);
			}catch(IOException e){
				System.out.println("Greska pri upisivanju u fajl.");
			}
		}
	}
	
	@Override
	public void run(){
		int kolona = 0;
		System.out.println("\n" + this + " POCINJE SA KRETANJEM.");
		while(kolona < 20){
			if(Main.knjige[red][kolona] != null){
				try{
					Object obj = Main.knjige[red][kolona];
					System.out.println("\n" + this + " JE NAISAO NA KNJIGU: " + obj.getClass().getSimpleName());
					if(obj instanceof Beletristika && "BELETRISTIKA".equalsIgnoreCase(oblastInteresovanja)){
						upisi(((Beletristika)obj).naslov);
						Main.knjige[red][kolona] = null;
					}else if(obj instanceof StrucnaLiteratura && "STRUCNA LITERATURA".equalsIgnoreCase(oblastInteresovanja)){
						upisi(((StrucnaLiteratura)obj).naslov);
						Main.knjige[red][kolona] = null;
					}else if(obj instanceof KnjigeZaDjecu && "KNJIGE ZA DJECU".equalsIgnoreCase(oblastInteresovanja)){
						KnjigeZaDjecu s = (KnjigeZaDjecu)obj;
						if(s.starostDjeteta != godine){
							throw new AgeOutOfBoundException("Godine djeteta ne odgovaraju preporucenost starosti za citanje knjige.");
						}else{
							upisi(((KnjigeZaDjecu)obj).naslov);
							Main.knjige[red][kolona] = null;
						}
					}
				}catch(AgeOutOfBoundException e){
					e.getMessage();
				}catch(Exception e){
					e.printStackTrace();
				}
				try{
					sleep(1000);
				}catch(InterruptedException e){
					System.out.println("Greska dok je korisnik cekao jednu sekundu.");
				}
				
			}
			kolona++;
		}
	}
	
	@Override
	public String toString(){
		return "Korisnik{" + ime + " " + prezime + " Oblast interesovanja: " + oblastInteresovanja + " Godine:" + godine + " Broj clanske karte:" + brClanske + "}";
	}
}