package pismeni.R2018_09_06.Z01;

public class Mjesto extends Thread {

	Vozilo vozilo;
	String naziv;
	Gorivo gorivo;
	static int count;

	public Mjesto(){
		naziv = "MJESTO " + (++count);
	}

	public void run(){
		try{
			while(!Simulacija.vozila.isEmpty()){
				if(vozilo != null){
					Simulacija.vozila.remove(vozilo);
					// puni gorivo
					while(vozilo.stanjeGoriva < vozilo.kapacitet){
						System.out.println(naziv +  ": puni se vozilo... " + vozilo + " gorivom: " + gorivo);
						Thread.sleep(1000);
						vozilo.uspiGorivo();
					}
					vozilo = null;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}