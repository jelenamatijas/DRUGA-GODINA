import java.io.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Terminal extends Thread{
	static int ID = 0;
	int id;
	
	Terminal(){
		id = ID++;
	}
	
	public void run(){
		Vozilo v = null;
		int taksa = 0;
		boolean putImaVozila = true;
		
		if(id == 0){ ///automobili
			boolean radi = true;
			while(putImaVozila || radi){
				synchronized(Main.lock3){
					if(Main.put.peek() == null){
						putImaVozila = false;
					}
				}
				
				synchronized(Main.lock){
					if(Main.automobili.peek() != null){
						v = Main.automobili.poll();
					}else{
						radi = false;
					}
				}
				
				if(v != null){
					if(v.motor.tip.equals("ELEKTRICNI")){
						taksa = 0;
					}else if(v.motor.tip.equals("HIBRIDNI")){
						taksa = 50;
					}else{
						taksa = 100;
					}
					
					upisi("Regularno.txt", taksa);
					
					System.out.println("Na terminalu za automobile obradjuje se vozilo: " + v + " -> TAKSA = " + taksa);
				}
				
				if(v != null){
					Main.ukupanBrojPutnika += v.brojPutnika;
					Main.ukupnaVrijednostTakse += taksa;
				}
				
				try{
					sleep(Main.rand.nextInt(100, 1001));
				}catch(InterruptedException e){
					System.out.println("GRESKA pri pauziranju terminala za automobile.");
				}
			}
			
		}else if(id ==1){//kamioni
			boolean radi = true;
			while(putImaVozila || radi){
				synchronized(Main.lock3){
					if(Main.put.peek() == null){
						putImaVozila = false;
					}
				}
				
				synchronized(Main.lock1){
					if(Main.kamioni.peek() != null){
						v = Main.kamioni.poll();
					}else{
						radi = false;
					}
				}
				
				if(v != null){
					taksa = 500;
					synchronized(Main.lockFile1){
						upisi("Posebno.txt", taksa);
					}
					
					System.out.println("Na terminalu za kamione obradjuje se vozilo: " + v + " -> TAKSA = " + taksa);
				}
				
				if(v != null){
					Main.ukupanBrojPutnika += v.brojPutnika;
					Main.ukupnaVrijednostTakse += taksa;
				}
				
				try{
					sleep(Main.rand.nextInt(100, 2001));
				}catch(InterruptedException e){
					System.out.println("GRESKA pri pauziranju terminala za kamione.");
				}
			}
		}else{
			boolean radi = true;
			while(putImaVozila || radi){
				
				synchronized(Main.lock3){
					if(Main.put.peek() == null){
						putImaVozila = false;
					}
				}
				
				synchronized(Main.lock2){
					if(Main.autobusi.peek() != null){
						v = Main.autobusi.poll();
					}else{
						radi = false;
					}
				}
				
				if(v != null){
					taksa = v.brojPutnika*20;
					synchronized(Main.lockFile1){
						upisi("Posebno.txt", taksa);
					}
					System.out.println("Na terminalu za autobuse obradjuje se vozilo: " + v + " -> TAKSA = " + taksa);
				}
				
				if(v != null){
					Main.ukupanBrojPutnika += v.brojPutnika;
					Main.ukupnaVrijednostTakse += taksa;
				}
				
				try{
					sleep(Main.rand.nextInt(100, 2001));
				}catch(InterruptedException e){
					System.out.println("GRESKA pri pauziranju terminala za autobuse.");
				}
			}
		}
		
		
	}
	
	void upisi(String filename, int taksa){
		File file = new File(filename);
		int ukupno = 0;
		try{
			if(file.exists()){
				ukupno = Integer.parseInt(Files.readString(Paths.get(filename)).trim());
			}
		}catch(SecurityException | IOException e){
			System.out.println("GRESKA pri otvaranju " + filename);
		}
		
		ukupno+=taksa;
		
		try(PrintWriter pw = new PrintWriter(new FileWriter(filename, false))){
			pw.print(ukupno);
		}catch(IOException e){
			System.out.println("GRESKA pri upisu u " + filename);
		}
	}
}