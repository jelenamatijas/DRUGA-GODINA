class Proizvodjac implements Element{
	String naziv;
	
	Proizvodjac(String s){
		naziv = s;
	}
	
	@Override
	public String toString(){
		return naziv;
	}
}