class VozacKamiona extends Vozac{
	VozacKamiona(){
		super();
	}
	
	@Override
	public void akcija(){
		System.out.println(this + " prevozi " + predmet);
	}
	
	@Override
	public String toString(){
		return "Vozac kamiona -> " + super.toString();
	}
}