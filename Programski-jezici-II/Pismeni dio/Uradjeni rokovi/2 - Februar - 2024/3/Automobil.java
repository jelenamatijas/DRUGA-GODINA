import java.util.*;

public class Automobil{
	int godina;
	String marka;
	int brojVrata;
	int snaga;
	TIP tip;
	
	public Automobil(){
		Random rand = new Random();
		godina = rand.nextInt(1990, 2025);
		marka = "Marka" + rand.nextInt(1, 10);
		brojVrata = (rand.nextInt(2) == 0) ? 2 : 4;
		snaga = rand.nextInt(15) + 145;
		int x = rand.nextInt(3);
		if(x == 0){
			tip = TIP.LIMUZINA;
		}else if(x == 1){
			tip = TIP.HATCHBACK;
		}else{
			tip = TIP.SUV;
		}
	}
	
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}
		else if(obj == null || obj.getClass() != this.getClass()){
			return false;
		}else{
			Automobil a = (Automobil) obj;
			return (tip.equals(a.tip) && godina == a.godina && brojVrata == a.brojVrata && snaga == a.snaga && marka.equals(a.marka));
		}
	}
	
	@Override
	public String toString(){
		return "Automobil{Godina:" + godina + " Marka:" + marka + " Broj vrata:" + brojVrata + " Tip:" + tip + " Snaga:" + snaga + "}\n";
	}
	
	public enum TIP{
		LIMUZINA, HATCHBACK, SUV;
	}
}