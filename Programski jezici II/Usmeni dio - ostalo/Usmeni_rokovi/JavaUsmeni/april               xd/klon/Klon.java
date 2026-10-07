import java.util.*;

public class Klon implements Cloneable{
	public static void main(String[] args){
		Klon klon = new Klon();
		try{
		Klon klon1 = klon.clone();
		}catch(CloneNotSupportedException e){}
	}
	protected Klon clone() throws CloneNotSupportedException{
		System.out.println("Bakac je kloc");
		return new Klon();
	}
}