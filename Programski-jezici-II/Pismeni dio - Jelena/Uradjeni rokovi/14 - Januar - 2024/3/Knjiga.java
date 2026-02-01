import java.util.*;

class Knjiga{
	String naslov;
	Autor autor;
	public Knjiga(){
		naslov = "Naslov" + (new Random()).nextInt(10) + 1;
		autor = new Autor();
	}
	
	@Override
	public String toString(){
		return "\nKnjiga{" + naslov + autor + "}";
	}
}