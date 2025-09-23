import java.io.*;

class SenzorVibracija extends Senzor{
	public SenzorVibracija(String s){
		super();
		senzorName = s;
		data = new Integer[1];
		data[0] = 0;
	}
	
	@Override
	public void ocitajVrijednost(){
		Integer t = Main.rand.nextInt(50)+1;
		try(PrintWriter pw = new PrintWriter(new FileWriter(senzorName + ".txt", true))){
			pw.println("Ocitana nova vrijednost VIBRACIJE: " + t);
			System.out.println(senzorName + " Ocitana nova vrijednost VIBRACIJE: " + t);
			if(t >= data[0]*2){
				pw.println("UPOZORENJE: VIBRACIJA dvostruko veca od prethodne vrijednosti: " + t);
				System.out.println(senzorName + " UPOZORENJE: VIBRACIJA dvostruko veca od prethodne vrijednosti: " + t);
			}
		}catch(IOException e){
			System.out.println("GRESKA pri upisu u fajl: " + senzorName + ".txt");
		}
		data[0] = t;
	}
}