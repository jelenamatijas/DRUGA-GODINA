import java.util.*;

class Predmet{
	static int ID = 1;
	int id;
	boolean statusOstecenja;
	int tezina;
	
	Predmet(){
		id = ID++;
		statusOstecenja = false;
		tezina = Main.rand.nextInt(1, 10);
	}
	
	@Override
	public String toString(){
		return "Predmet: " + id + " status ostecenja: "  + statusOstecenja + " Tezina: " + tezina;
	}
}