import java.util.Objects;

class Automobil{
	int godina;
	String marka;
	int brojVrata;
	int snaga;
	Tip tip;
	
	public Automobil(){
		godina = Main.rand.nextInt(30) + 1990;
		marka = "Marka_" + Main.rand.nextInt(1, 6);
		brojVrata = Main.rand.nextInt(1,4);
		snaga = Main.rand.nextInt(300);
		int x = Main.rand.nextInt(3);
		if(x%3==0){
			tip = Tip.LIMUZINA;
		}else if(x%3 == 1){
			tip = Tip.HATCHBACK;
		}else{
			tip = Tip.SUV;
		}
	}
	
	@Override
	public boolean equals(Object o){
		if(o == null || this.getClass() != o.getClass()){
			return false;
		}
		Automobil obj = (Automobil)o;
		return (godina == obj.godina && marka.equals(obj.marka) && brojVrata==obj.brojVrata && snaga==obj.snaga && tip == obj.tip);
	}
	
	public int hashcode(){
		return Objects.hash(godina, marka, brojVrata, snaga, tip);
	}
	
	@Override
	public String toString(){
		return "Automobil -> Godina: " + godina + " Marka: " + marka + " Broj vrata: " + brojVrata + " Snaga: " + snaga + " Tip: " + tip;
	}
	
	public enum Tip{
		LIMUZINA, HATCHBACK, SUV;
	}
}