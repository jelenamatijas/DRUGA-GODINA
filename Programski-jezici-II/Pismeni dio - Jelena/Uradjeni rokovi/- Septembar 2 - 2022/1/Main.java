import java.util.*;

class Main{
	static int REDOVI = 0;
	static int KOLONE = 0;
	static Ratnik[][] mapa = null;
	static Object lock = new Object();
	static Random rand = new Random();
	static volatile boolean radi = true;
	static int X,Y;
	static int unistenihLjudi = 0;
	static int unistenihZivotinja = 0;
	
	static void findXY(){
		do{
			X = Main.rand.nextInt(0, REDOVI);
			Y = Main.rand.nextInt(KOLONE/2, KOLONE);
		}while(mapa[X][Y] != null);
	}
	
	static public void main(String args[]){
		if(args.length != 2){
			System.out.println("POGRESAN FORMAT.");
			return;
		}
		
		REDOVI = Integer.parseInt(args[0]);
		KOLONE = Integer.parseInt(args[1]);
		
		mapa = new Ratnik[REDOVI][KOLONE];
		
		List<Ratnik> ratnici = new ArrayList<>();
		for(int i=1;i<=8;i++){
			do{
				X = Main.rand.nextInt(0, REDOVI);
				Y = Main.rand.nextInt(0, KOLONE/2);
			}while(mapa[X][Y] != null);
			
			Covjek covjek = new Covjek("Covjek_" + i, rand.nextInt(250, 651), rand.nextBoolean(), rand.nextBoolean(),X,Y);
			ratnici.add(covjek);
		}
		
		findXY();
		ratnici.add(new Feniks("Feniks_1",rand.nextInt(250, 651), X, Y));
		findXY();
		ratnici.add(new Feniks("Feniks_2",rand.nextInt(250, 651), X, Y));
		findXY();
		ratnici.add(new Cudoviste("Cudoviste_1",rand.nextInt(250, 651), X, Y));
		findXY();
		ratnici.add(new Cudoviste("Cudoviste_2",rand.nextInt(250, 651), X, Y));
		findXY();
		ratnici.add(new Pegaz("Pegaz_1",rand.nextInt(250, 651), X, Y));
		findXY();
		ratnici.add(new Pegaz("Pegaz_2",rand.nextInt(250, 651), X, Y));
		findXY();
		ratnici.add(new Sfinga("Sfinga_1",rand.nextInt(250, 651), X, Y));
		findXY();
		ratnici.add(new Sfinga("Sfinga_2",rand.nextInt(250, 651), X, Y));
		
		for(Ratnik r : ratnici){
			mapa[r.x][r.y] = r;
			r.start();
		}
		
		for(Ratnik r : ratnici){
			try{
				r.join();
			}catch(InterruptedException e){
				System.out.println("Greska pri cekanju zavrsetka ratnika.");
			}
		}
		
		System.out.println("\n========== FINALNI IZVJESTAJ ==========");
		System.out.println("Unistenih ljudi: " + unistenihLjudi);
		System.out.println("Unistenih zivotinja: " + unistenihZivotinja);
		System.out.println("\nPreostali ratnici:");
		for(Ratnik r : ratnici){
			if(r.ziv){
				System.out.println(r + " Snaga: " + String.format("%.2f", r.snaga));
			}
		}
		System.out.println("=======================================");
	}
}