import java.util.*;

class RadnikProdaje extends Zaposleni{
	public RadnikProdaje(){super();}
	
	@Override
	public void run(){
		radi = true;
		System.out.println(this + " pocije sa radom.");
		
		int n = 1;
		int prosloVrijeme = 0;
		
		while(radi){
			try{
				String zadatak = "Ponuda za prodaju #" + n;
				System.out.println(zadatak);
				zadaci.add(zadatak);
				for(int i=0;i<3;i++){
					sleep(1000);
					prosloVrijeme++;
					if(prosloVrijeme == vrijemePauze){
						System.out.println(this + " otisao na pauzu.");
						sleep(5000);
						System.out.println(this + " vratio se sa pauze. Nastavlja s radom.");
					}
					
				}
				n++;
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
			if(zadaci.size() == 10){
				synchronized(Main.lock) {
					try{
						Main.lock.wait();
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	@Override
	public String toString(){
		return "Radnik prodaje{" + ime + " " + prezime + " Godine rada: " + godine + " Cijena rada: " + cijena + " Ponude za prodaju: " + zadaci + "}\n";
	}
}