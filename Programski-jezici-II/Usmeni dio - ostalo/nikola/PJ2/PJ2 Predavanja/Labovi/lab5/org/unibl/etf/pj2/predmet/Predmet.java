package org.unibl.etf.pj2.predmet;

public abstract class Predmet{
	protected double specificWeight;
	protected static int numOfObjects = 0;
	protected int id;
	
	public Predmet(specificWeight){
		this.specificWeight = specificWeight;
		numOfObjects++;
		id = numOfObjects;
	}
	
	protected abstract void print(){
		System.out.println("Id objekta je " + id + " specificna tezina je " + specificWeight + "zapremina je " + zapremina() + 
		" tezina je " + tezina());
	}
	protected abstract void read() throws PredmetException;
	protected abstract double zapremina();
	
	public double tezina(){
		return specificWeight*zapremina();
	}
	
		public static int poredjenje(Predmet p1, Predmet p2) {
		if (p1.zapremina() > p2.zapremina())
			return 1; // prvi je veci -> rezultat 1
		else if (p1.zapremina() < p2.zapremina())
			return -1; // drugi je veci -> rezultat -1
		else
			return 0; // ako su jednaki
	}
	}
}