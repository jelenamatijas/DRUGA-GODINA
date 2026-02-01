package org.unibl.etf.pj2.predmet;

public class kvadar extends Predmet{
	private double a;
	private double b;
	private double c;
	
	public Sfera(double specificWeight){
		this.a = a;
		this.b = b;
		this.c = c;
		super(specificWeight);
	}
	
	@Override
		public void print(){
			super.print();
			System.out.println("Stranica a je " + a);
			System.out.println("Stranica b je " + b);
			System.out.println("Stranica c je " + c);
		}
	@Override
	public void read() throws PredmetException {
		System.out.println("Unesite podatke za kvadar:");
		Scanner scan = new Scanner(System.in);

		System.out.print("a = ");
		a = scan.nextInt();
		if (a < 1 || a > 100) {
			throw new PredmetException();
		}
		System.out.print("b = ");
		b = scan.nextInt();
		if (b < 1 || b > 100) {
			throw new PredmetException();
		}
		System.out.print("c = ");
		c = scan.nextInt();
		if (c < 1 || c > 100) {
			throw new PredmetException();
		}

	}

	@Override
	public double zapremina() {
		return a * b * c;
	}
}