package src.klase;

import java.util.ArrayList;

public class Polje{
	
	int x, y;
	
	public ArrayList<Atrakcija> atrakcije = new ArrayList<>();
	
	public Polje(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	
	public String toString(){
		return "Polje " + y + " " + x;
	}
	
}