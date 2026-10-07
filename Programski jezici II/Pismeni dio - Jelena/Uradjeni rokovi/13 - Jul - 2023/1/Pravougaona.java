class Pravougaona extends Kockica implements GradjenaInterface, RastavljenaInterface, RotiranaInterface{
	int duzina;
	int	sirina;
	
	Pravougaona(int d, int s){
		super(d*s, 2*(d+s), "CRVENA");
		duzina = d;
		sirina = s;
	}
	
	@Override
	public int getSirina(){
		return sirina;
	}
	
	@Override
	public int getDuzina(){
		return duzina;
	}
	
	@Override
	public String toString(){
		return super.toString() + " Pravougaona ->" + duzina + "x" + sirina;
	}
}