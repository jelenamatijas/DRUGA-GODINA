import java.util.*;

abstract class Letjelica extends Thread{
	static int ID=1;
	int id;
	int red;
	int kolona;
	boolean leti = true;
	
	public Letjelica(){
		id = ID++;
	}
	
	@Override
	public String toString(){
		return "LETJELICA: ID: " + id;
	}
	
	@Override
	public void run(){
		
		int k = kolona;
		while(leti){
			synchronized(Main.mapa){
				if(this instanceof NeutralizacijaRaketeInterface && !(this instanceof EnergetskiStitInterface)){
					Sonda s = (Sonda) this;
					if(k - 3 >=0 && s.laser.snaga>0){
						if(Main.mapa[red][k-3] != null){
							System.out.println(this + "\n\tISPALJUJE LASER na letjelicu\n\t" + Main.mapa[red][k-3]);
							s.laser.snaga--;
							if(!(Main.mapa[red][k-3] instanceof EnergetskiStitInterface)){
								System.out.println(Main.mapa[red][k-3] + " UNISTENA.");
								Main.mapa[red][k-3].leti = false;
								Main.mapa[red][k-3] = null;
							}else{
								System.out.println(Main.mapa[red][k-3] + "\n\tISPALJUJE RAKETU na letjelicu\n\t" + this);
								System.out.println("Raketa neutralisana.");
							}
						}
					}
					k--;
				}else{
					k++;
				}
				if(k>=0 && k<Main.n && Main.mapa[red][kolona] !=null){
					Main.mapa[red][k] = this;
					Main.mapa[red][kolona] = null;
					kolona = k;
				}else{
					leti = false;
				}
				/*try{
					sleep(20);
				}catch(InterruptedException e){
					e.printStackTrace();
				}*/
			}
		}
	}
}