import java.util.*;
import java.io.*;
import java.util.logging.*;

class Banka{
	static int NUMBER_OF_CLIENTS = 3;
	Klijent klijenti[];
	public static Handler handler;

	{
		try{
			handler = new FileHandler("banka.log");
			Logger.getLogger(Banka.class.getName()).addHandler(handler);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	Banka(){
		klijenti = new Klijent[NUMBER_OF_CLIENTS];
		Scanner input = new Scanner(System.in);
		for(int i=0; i<NUMBER_OF_CLIENTS;i++){
			
			System.out.println("Unesi podatke za " + (i+1) + ". klijenta: ");
			System.out.print("Ime: ");
			String ime = input.nextLine();
			System.out.println();
			System.out.print("Prezime: ");
			String prezime = input.nextLine();
			System.out.println();
			System.out.print("JMBG: ");
			String jmbg = input.nextLine();
			System.out.println();
			System.out.print("Stanje na racunu: ");
			double stanjeNaRacunu = Double.parseDouble(input.nextLine());
			
			Klijent k = new Klijent(ime, prezime, jmbg, stanjeNaRacunu);
			
			klijenti[i] = k;
		}
		input.close();
	}
	
	
	public static void main(String args[]){
		Banka banka = new Banka();
		
		Random rand = new Random();
		System.out.println("Podaci o klijentima prije smanjenja stanja na racunu:");
		for(int i=0; i<NUMBER_OF_CLIENTS; i++){
			System.out.println(banka.klijenti[i]);
			double n;
			try{
				n = rand.nextDouble()*100;
				if(banka.klijenti[i].getStanjeNaRacunu() >= n){
					banka.klijenti[i].setStanjeNaRacunu(n);
				}else {
					throw new ValueException();
				}
			}catch(ValueException e){
				Logger.getLogger(Banka.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
				if(banka.klijenti[i].getStanjeNaRacunu()!=0){
					do {
						n = rand.nextDouble() * 100;
					} while (n > banka.klijenti[i].getStanjeNaRacunu());
					banka.klijenti[i].setStanjeNaRacunu(n);
				}
			}
		}
		
		System.out.println("Podaci o klijentima poslije smanjenja stanja na racunu:");
		for(int i=0; i<NUMBER_OF_CLIENTS; i++){
			System.out.println(banka.klijenti[i]);
		}
	}
	
}