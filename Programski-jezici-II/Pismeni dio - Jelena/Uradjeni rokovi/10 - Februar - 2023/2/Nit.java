import java.io.*;
import java.util.*;

class Nit extends Thread{
	int id;
	static int ID = 1;
	Random rand = new Random();
	boolean pogodak = false;
	public Nit(){
		id = ID++;
	}
	
	public void run(){         
		while(!pogodak){
			String kombinacija = rand.nextInt(10)+" "+rand.nextInt(10)+" "+rand.nextInt(10)+" "+rand.nextInt(10)+" "+rand.nextInt(10);
			String dobitna = null, brojNiti = null, naziviNiti = null;
			synchronized(Main.lock){
				try(BufferedReader bf = new BufferedReader(new FileReader("tombola.txt"))){
					dobitna = bf.readLine();
					if(dobitna.equals(kombinacija)){
						pogodak = true;
						System.out.println("NIT " + id + " je pogodila kombinaciju.");
						brojNiti = bf.readLine();
						brojNiti = (brojNiti == null) ? "1" : String.valueOf(Integer.parseInt(brojNiti) + 1);
						naziviNiti = bf.readLine();
						naziviNiti = (naziviNiti == null) ? String.valueOf(id) : (naziviNiti + " " + id);
					}
				}catch(IOException e){
					System.out.println("GRESKA pri citanju fajla.");
				}
				if(brojNiti!=null && naziviNiti!=null){
					try(PrintWriter pw = new PrintWriter("tombola.txt")){
						pw.println(dobitna);
						pw.println(brojNiti);
						pw.println(naziviNiti);
					}catch(IOException e){
						System.out.println("GRESKA pri upisu u fajl.");
					}
				}
			}
		}
	}
}