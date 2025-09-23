import java.util.*;

class Automobil{
	int godina;
	String marka;
	int brojVrata;
	int snaga;
	String tip;
	
	public Automobil(){
		Random rand = new Random();
		godina = rand.nextInt(1990, 2025);
		marka = "Marka" + rand.nextInt(1, 10);
		brojVrata = rand.nextInt(1) == 0 ? 3 : 5;
		snaga = rand.nextInt(50, 100);
		int x = rand.nextInt(2);
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
	
	public enum TIP{
		LIMUZINA, HATCHBACK, SUV;
	}
}