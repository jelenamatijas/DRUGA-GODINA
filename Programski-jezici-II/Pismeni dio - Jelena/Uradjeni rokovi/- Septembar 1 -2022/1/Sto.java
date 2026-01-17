import java.util.*;

class Sto implements Runnable{
	int brojMjesta;
	int id;
	boolean zaPusace;
	GrupaGostiju grupa = null;
	boolean prazan;
	Meni meni;
	Meni meni2;
	
	Sto(int x, int i, boolean z, Meni meni, Meni meni2){
		brojMjesta = x;
		id = i;
		zaPusace = z;
		prazan = true;
		this.meni = meni;
		this.meni2 = meni2;
	}
	
	public void start(){
		new Thread(this).start();
	}
	
	public void run(){
		int suma = 0;
		List<Stavka> veg = meni.getVegetarijanskeStavke();
		for(Gost g:grupa.gosti){
			Stavka s;
			Stavka p;
			if(g instanceof VegetarijanacInterface){
				s = veg.get(Main.rand.nextInt(0, veg.size()));
				try{
					Thread.sleep(200);
				}catch(InterruptedException e){
					System.out.println("Greska pri citanu menija.");
				}
			}else{
				s = meni.stavke.get(Main.rand.nextInt(0, meni.stavke.size()));
				try{
					Thread.sleep(200);
				}catch(InterruptedException e){
					System.out.println("Greska pri citanu menija.");
				}
			}
			p = meni2.stavke.get(Main.rand.nextInt(0, meni2.stavke.size()));
			System.out.println("STO " + id + ": " + g + " je odabrao jelo: " + s + " i pice: " + p);
			suma+= s.cijena + p.cijena;
		}
		
		if(suma <= grupa.ukupanIznos()){
			System.out.println("Grupa " + grupa + " je usluzena.");
		}else{
			System.out.println("Grupa " + grupa + " nije usluzena jer nemaju dovoljno novca.");
		}
		grupa = null;
		prazan  =true;
	}
}