class Cilindricna extends Kockica{
	int poluprecnik;
	
	Cilindricna(int p){
		super(p*p*3.14, 2*3.14*p, "ZUTA");
		poluprecnik = p;
	}
	
	@Override
	public int getPoluprecnik(){
		return poluprecnik;
	}
	
	@Override
	public String toString(){
		return super.toString() + " Cilindricna ->" + poluprecnik;
	}
}