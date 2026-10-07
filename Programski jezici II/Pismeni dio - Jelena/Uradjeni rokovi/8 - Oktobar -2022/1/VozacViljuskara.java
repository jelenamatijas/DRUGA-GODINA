class VozacViljuskara extends Vozac{
	VozacViljuskara(){
		super();
	}
	
	@Override
	public void akcija(){
		System.out.println(this + " prevozi " + predmet);
	}
	
	@Override
	public String toString(){
		return "Vozac viljuskara -> " + super.toString();
	}
}