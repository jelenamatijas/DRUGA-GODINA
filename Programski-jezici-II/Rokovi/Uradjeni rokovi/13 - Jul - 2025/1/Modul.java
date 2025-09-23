
class Modul extends Thread{
	int index;
	boolean radi;
	boolean cekaj;
	int idModula;
	static int id = 0;
	
	public Modul(){
		index = 0;
		radi = false;
		idModula = id++;
		cekaj = true;
	}
	
	private void otkazao(){
		if(Main.rand.nextInt(101) <=5){
			radi = false;
		}
	}
	
	@Override
	public void run(){
		radi = true;
		cekaj = false;
		while(radi){
			otkazao();
			if(this instanceof ProizvodnjaInterface && !cekaj){
				synchronized(Main.lock){
					if(index < Main.size){
						Main.proizvedenaEnergija[index++] = Main.rijeka.nivo*100/Math.sqrt(2.0);
					}else{
						cekaj = true;
						Hidrocentrala.moduli[2].cekaj = false; 
					}
				}
				
				try{
					sleep(2000);
				}catch(InterruptedException e){
					System.out.println("GRESKA prilikom cekanja na rad proizvodnog modula " + id);
				}
			}else if(this instanceof DistribucijaInterface && !cekaj){
				synchronized(Main.proizvedenaEnergija){
					System.out.println("Proizvedena energija: ");
					for(int i=0;i<Main.size;i++){
						System.out.print(Main.proizvedenaEnergija[i] + " ");
					}
					System.out.println();
				}
				index = 0;
				cekaj = true;
			
				try{
					sleep(Main.rand.nextInt(0,1001));
				}catch(InterruptedException e){
					System.out.println("GRESKA prilikom rada distributivnog modula.");
				}
				
				Hidrocentrala.moduli[0].cekaj = false; 
				Hidrocentrala.moduli[1].cekaj = false; 
				
			}else if(this instanceof KontrolaInterface){
				System.out.println("Nivo rijeke: " + Main.rijeka.nivo);
				System.out.println("Aktivni moduli: ");
				for(Modul m : Hidrocentrala.moduli){
					if(!m.cekaj){
						System.out.print(m.idModula + " ");
					}
				}
				System.out.println();
				
				try{
					sleep(2000);
				}catch(InterruptedException e){
					System.out.println("GRESKA prilikom cekanja na rad kontrolnog modula.");
				}
			}
		}
	}
}