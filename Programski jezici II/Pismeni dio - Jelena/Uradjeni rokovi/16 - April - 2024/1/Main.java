import java.util.*;
import java.io.*;
import java.time.LocalDateTime;

class Main{
	public static Random rand = new Random();
	public static Object[][] knjige;
	public static int brojPodignutihKnjiga = 0;
	public static String file = "BIBLIOTEKA-" +  System.currentTimeMillis() + ".txt";
	
	public static void main(String args[]){
		if(args.length == 1){
			int brKnjiga = Integer.parseInt(args[0]);
			int brJedneVrste = brKnjiga/3;
			if(brKnjiga >=30){
				
			knjige  = new Object[10][20];
			
			for(int i=0; i< brJedneVrste; ){
				int x = rand.nextInt(0, 10);
				int y = rand.nextInt(0, 20);
				if(knjige[x][y] == null){
					knjige[x][y] = new Beletristika();
					i++;
				}
			}
			
			for(int i=0; i< brJedneVrste; ){
				int x = rand.nextInt(0, 10);
				int y = rand.nextInt(0, 20);
				if(knjige[x][y] == null){
					knjige[x][y] = new KnjigeZaDjecu();
					i++;
				}
			}
			
			for(int i=0; i< brJedneVrste; ){
				int x = rand.nextInt(0, 10);
				int y = rand.nextInt(0, 20);
				if(knjige[x][y] == null){
					knjige[x][y] = new StrucnaLiteratura();
					i++;
				}
			}
			
			System.out.println("Kreirano je " + (brJedneVrste*3) + " knjiga. Nije kreirano " + (brKnjiga - brJedneVrste*3) + " knjiga.");
			
			long pocetak = System.currentTimeMillis();
			ArrayList<Korisnik> korisnici = new ArrayList<>();
			for(int i=0;i<10;i++){
				Korisnik k = new Korisnik();
				korisnici.add(k);
				k.start();
			}
			
			try{
				for(Korisnik k : korisnici){
					k.join();
				}
			}catch(InterruptedException e){
				e.getMessage();
			}
			
			long kraj = System.currentTimeMillis();
			double procenat = (double)brojPodignutihKnjiga/brKnjiga*100;
			System.out.println("Procenat podignutih knjiga: " + procenat + "%");
			try(PrintWriter pw = new PrintWriter(new FileWriter(file, true))){
				pw.println("Datum: " + LocalDateTime.now());
				pw.println("Vrijeme trajanja simulacije: " + ((double)(kraj - pocetak)/1000) + "s");
				pw.println("Procenat iznajmljenih knjiga: " + procenat + "%");
			}catch(IOException e){
				System.out.println("Greska pri upisu statistike.");
			}
			
			try(BufferedReader bf = new BufferedReader(new FileReader(file))){
				String line;
				System.out.println("Sadrzaj fajla:");
				while((line = bf.readLine())!=null){
					System.out.println(line);
				}
			}catch(IOException e){
				System.out.println("Greska pri citanju fajla.");
			}
				
			}else{
				System.out.println("Broj knjiga ne moze biti manji od 30.");
			}
			
		}else{
			System.out.println("Nije unesen broj knjiga.");
		}
	}
}