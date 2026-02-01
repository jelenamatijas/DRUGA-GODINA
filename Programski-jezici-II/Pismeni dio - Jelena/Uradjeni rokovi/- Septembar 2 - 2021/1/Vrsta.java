class Vrsta implements Element{
	String naziv;
	
	Vrsta(String s){
		naziv = s;
	}
	
	@Override
	public String toString(){
		return naziv;
	}
}