import java.util.*;

class ClanTima extends Thread{
	String ime, prezime;
	int godineRada;
	volatile static int sprintId = 1;
	volatile static boolean radi = true;
	public ClanTima(String a, String b, int c){
		ime = a;
		prezime = b;
		godineRada = c;
	}
	
	@Override
	public void run(){
		Random rand = new Random();
		while(sprintId <= 3){
			if(this instanceof ScrumMasterInterface){
				synchronized(Main.zadaci){
					if(radi){
						((ScrumMaster)this).scrumMaster(" generise zadatke za sprint " + sprintId);
						radi = false;
						for(int i=0; i<5;i++){
							Main.zadaci.add(new Zadatak());
						}
					}else {
						try{
							Thread.sleep(10);
						}catch(InterruptedException e){
							e.printStackTrace();
						}
						
					}
				}
				synchronized(Main.uradjeniZadaci){
					if(!radi && Main.uradjeniZadaci.size()==5){
						((ScrumMaster)this).scrumMaster(" upisuje podatke u datoteku u sprintu " + sprintId);
						((ScrumMaster)this).upisi(Main.uradjeniZadaci, sprintId);
						Main.uradjeniZadaci.clear();
						sprintId++;
						radi = true;
					}else {
						try{
							Thread.sleep(10);
						}catch(InterruptedException e){
							e.printStackTrace();
						}
					}
				}
					
				
			}else if((this instanceof ProductOwnerInterface)){
				((ProductOwner)this).productOwner();
				try{
					sleep(2000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}else{
				Developer d = (Developer)this;
				Zadatak z = null;
				synchronized(Main.zadaci){
					if(Main.zadaci.size()>0){
						z = Main.zadaci.poll();
					}
				}
				if(z != null){
					d.developer(z.naziv);
					int vrijeme = rand.nextInt(1000, 3001);
					try{
						sleep(vrijeme);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					z.trajanjeRada = vrijeme;
					z.developer = d.ime + " " + d.prezime;
					synchronized(Main.uradjeniZadaci){
						Main.uradjeniZadaci.add(z);
					}	
				}else {
						try{
							Thread.sleep(10);
						}catch(InterruptedException e){
							e.printStackTrace();
						}
				}
			}
		}
	}
	
	@Override
	public String toString(){
		return "Ime: " + ime + " Prezime: " + prezime + " Godine iskustva: " + godineRada;
	}
}