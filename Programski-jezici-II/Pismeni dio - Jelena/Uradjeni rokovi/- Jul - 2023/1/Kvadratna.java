class Kvadratna extends Kockica implements RastavljenaInterface, GradjenaInterface{
	int	duzina;
	
	Kvadratna(int d){
		super(d*d, 4*d, "PLAVA");
		duzina = d;
	}
	
	@Override
	public int getDuzina(){
		return duzina;
	}
	
	@Override
	public String toString(){
		return super.toString() + " Kvadratna ->" + duzina;
	}
}