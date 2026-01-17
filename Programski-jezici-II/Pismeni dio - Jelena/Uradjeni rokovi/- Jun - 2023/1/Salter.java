import java.io.*;
import java.io.File;

class Salter extends Thread{
	static int ID = 1;
	int id;
	
	Salter(){
		id = ID++;
	}
	
	public void run(){
		Osoba o = null;
		boolean radi = true;
		while(radi){
			o=null;
			synchronized(Main.lock){
				o = Main.osobe.poll();
			}
			if(o != null){
				System.out.println("SALTER: " + id + " -> " + o);
				int ukupnaCijena = 0;
				for(Vozilo v : o.vozila){
					ukupnaCijena += v.ukupnaCijena;
					System.out.println("Salter: " + id +" -> Obradjuje se vozilo: " + v + " osobe " + o);
				}
				if(ukupnaCijena <= o.novac){
					try(PrintWriter pw = new PrintWriter(new FileWriter(new File("folder" + File.separator + "Osoba" + o.ID + ".txt")))){
						pw.println("ID saltera: " + id + " -> ID osobe: " + o.ID);
						System.out.println("Osoba " + o + " je uspjesno registravala svoja vozila");
					}catch(IOException e){
						System.out.println("Greska pri upisu uspjesne registracije." + e);
					}
					
				}else{
					try(PrintWriter pw = new PrintWriter(new FileWriter("folder" + File.separator + "NEUSPJESNE.txt", true))){
						pw.println("ID saltera: " + id + " -> ID osobe: " + o.ID);
						System.out.println("Osoba " + o + " je neuspjesno registravala svoja vozila");
					}catch(IOException e){
						System.out.println("Greska pri upisu neuspjesne registracije." + e);
					}
				}
			}else{
				radi = false;
			}
			try{
				sleep(300);
			}catch(InterruptedException e){
				System.out.println("GRESKA:" + e);
			}
		}
	}
}