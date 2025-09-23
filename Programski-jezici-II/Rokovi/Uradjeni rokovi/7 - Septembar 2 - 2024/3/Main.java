import java.util.*;
import java.io.*;

class Main{
	public static Random rand = new Random();
	static Stack<Knjiga> s  = new Stack<>();
	public static Object lock = new Object();
	
	public static void main(String []args){
		Dodaj dodaj = new Dodaj();
		Uzmi uzmi = new Uzmi();
		
		long pocetak = System.currentTimeMillis();
		
		dodaj.start();
		uzmi.start();
		while((System.currentTimeMillis() - pocetak) < 20000){}
		
		dodaj.kraj = false;
		uzmi.kraj = false;
		try{
			dodaj.join();
			uzmi.join();
		}catch(InterruptedException e){
			System.out.println("GRESKA pri zaustavljanju niti.");
		}
		
		try(PrintWriter pw = new PrintWriter(new FileWriter("preostaleKnjige.txt", true))){
			while(!s.empty()){
				try{
					pw.println(s.pop());
				}catch(EmptyStackException e){
					System.out.println("Stack je ISPRAZNJEN.");
				}
				
			}
			pw.println("\n=================================================================\n");
		}catch(IOException e){
			System.out.println("GRESKA pri upisu u fajl.");
		}
	}
}