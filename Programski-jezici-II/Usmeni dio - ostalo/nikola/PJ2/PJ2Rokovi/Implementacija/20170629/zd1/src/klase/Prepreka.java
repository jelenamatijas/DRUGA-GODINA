package src.klase;


public abstract class Prepreka {
	
	public String ime;
	
	public static Voda newVoda(){
		return new Voda();
	}
	
	public static Vatra newVatra(){
		return new Vatra();
	}
	
	public static Kamen newKamen(){
		return new Kamen();
	}
	
}

interface IPoplavi{
	int JACINA = 1;
	default void poplavi(int pos){
		System.out.println("Voda je poplavila polje " + pos);
	}
}

interface IGori{
	int JACINA = 2;
		default void gori(int pos){
		System.out.println("Vatra gori na poziciji" + pos);
	}
}

interface IObrusi{
		int JACINA = 3;
		default void obrusi(int pos){
		System.out.println("Kamen se obrusio na poziciji" + pos);
	}
}

class Voda extends Prepreka implements IPoplavi{
	Voda(){
		ime = "Voda";
	}
}

class Vatra extends Prepreka implements IGori{
	Vatra(){
		ime = "Vatra";
	}
}

class Kamen extends Prepreka implements IObrusi{
	Kamen(){
		ime = "Kamen";
	}
}