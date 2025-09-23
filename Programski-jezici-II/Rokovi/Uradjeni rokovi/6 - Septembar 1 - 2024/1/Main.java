import java.util.*;

class Main{
	public static Letjelica[][] mapa;
	public static int n;
	public static void main(String []args){
		if(args.length != 1){
			System.out.println("GRESKA: format java Main <N>");
			return;
		}
		
		try{
			n = Integer.parseInt(args[0]);
		}catch(NumberFormatException e){
			System.out.println("GRESKA: argument mora biti cjelobrojna vrijednost.");
			return;
		}
		
		Letjelica[] letjelice = new Letjelica[4];
		letjelice[0] = new SvemirskiBrod();
		letjelice[1] = new MatricnaStanica();
		letjelice[2] = new Sonda();
		letjelice[3] = new Sonda();
		letjelice[0].kolona = 0;
		letjelice[1].kolona = 0;
		letjelice[2].kolona = n-1;
		letjelice[3].kolona = n-1;
		
		Random rand = new Random();
		
		mapa = new Letjelica[3][n];
		
		int x = rand.nextInt(3);
		mapa[x][0] = letjelice[0];
		letjelice[0].red = x;
		int y;
		while((y = rand.nextInt(3))==x);
		mapa[y][0] = letjelice[1];
		letjelice[1].red = y;
		
		x = rand.nextInt(3);
		mapa[x][n-1] = letjelice[2];
		letjelice[2].red = x;
		while((y = rand.nextInt(3))==x);
		mapa[y][n-1] = letjelice[3];
		letjelice[3].red = y;
		
		for(Letjelica l : letjelice){
			System.out.println(l);
		}
		
		System.out.println("===============================================================================");
		
		for(Letjelica l : letjelice){
			l.start();
		}
		
		
		
	}
}
