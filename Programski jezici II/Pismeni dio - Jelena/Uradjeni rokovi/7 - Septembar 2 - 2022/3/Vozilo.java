import java.util.*;

class Vozilo{
	int godina;
	int snaga;
	int brSjedista;
	Boja boja;
	Tip tip;
	
	Vozilo(){
		godina = Main.rand.nextInt(1990, 2021);
		snaga = Main.rand.nextInt(50, 300);
		brSjedista = 4;
		boja = Boja.values()[Main.rand.nextInt(1, 10)%3];
		tip = Tip.values()[Main.rand.nextInt(1, 10)%3];
	}
	
	
	/*boolean equals(Object o){
		if(this.getClass() != o.getClass()){
			return false;
		}
		
		Vozilo other = (Vozilo)o;
		if(tip != other.tip){
			return false;
		}
		
		if(godina==other.godina && snaga==other.snaga && brSjedista==other.brSjedista && boja==other.boja){
			return true;
		}
		return false;
	}
	
	int hashCode(){
		return Objects.hash(godina, snaga, brSjedista, boja, tip);
	}*/
	
	public enum Boja{
		CRVENA, BIJELA, PLAVA;
	}
	
	public enum Tip{
		SEDAN, KARAVAN, SUV;
	}
	
	@Override
	public String toString(){
		return "Vozilo -> godina: " + godina + " snaga: " + snaga + " broj sjedista: " + brSjedista + " boja: " + boja + " tip: " + tip;
	}
}