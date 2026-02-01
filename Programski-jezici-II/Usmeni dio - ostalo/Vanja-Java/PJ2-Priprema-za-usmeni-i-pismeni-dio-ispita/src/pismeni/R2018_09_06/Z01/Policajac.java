package pismeni.R2018_09_06.Z01;

public class Policajac extends Thread{

	public Policajac(){

	}

	public void run(){
		while(!Simulacija.vozila.isEmpty()){
			try{
				Thread.sleep(5000);
				for(Mjesto m: BenzinskaPumpa.mjesta){
					if(m.vozilo != null && m.vozilo.gorivo.equals(Gorivo.DIZEL) && m.gorivo.equals(Gorivo.ULJE)){
						System.out.println("POLICIJA: " + m.vozilo + " sipa LOŽ ULJE");
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}