package org.unibl.etf.pj2.predmet;

import org.unibl.etf.pj2.izuzetak.*;

public class Sfera extends Predmet{
	private double r;
	
	public Sfera(double specificWeight){
		this.r =r;
		super(specificWeight);
	}
	
	@Override
		public void print(){
			super.print();
			System.out.println("Poluprecnik je " + r);
		}
	@Override
		public void read() throws PredmetException{
			Scanner skener = new Scanner(System.in);
			System.out.println("Unesite poluprecnik");
			poluprecnik = skener.nextInt();
			if (poluprecnik < 1 || poluprecnik > 100) {
				throw new PredmetException("Poluprecnik treba da ima vrijednost u opsegu od 1 do 100");
			}
		}
		
	Override
		public double zapremina() {
		return 4 / 3 * (Math.PI * Math.pow(poluprecnik, 3));
	}
}