import java.util.*;

class Skladiste{
	List<Rafa> rafe;
	static int ID=1;
	String adresa;
	TipSkladista tip;
	
	Skladiste(){
		rafe = new ArrayList<>();
		adresa ="Skladiste_" + ID++;
		if(Main.rand.nextBoolean()){
			tip = TipSkladista.VELEPRODAJA;
		}else{
			tip = TipSkladista.MALOPRODAJA;
		}
	}
	
	@Override
	public String toString(){
		String s = "";
		for(Rafa a: rafe){
			s+=a +"\n";
		}
		
		return adresa + " " + tip + " Rafe:\n" +s;
	}
	
	enum TipSkladista {
		VELEPRODAJA,
		MALOPRODAJA
	}
}