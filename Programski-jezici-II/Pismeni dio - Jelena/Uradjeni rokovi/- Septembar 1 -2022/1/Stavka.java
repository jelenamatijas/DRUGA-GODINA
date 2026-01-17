class Stavka{
	String naziv;
	boolean vegetarijansko;
	static int id = 1;
	int cijena;
	
	Stavka(boolean v){
		naziv = "Jelo_" + id++;
		vegetarijansko = v;
		cijena = Main.rand.nextInt(10, 30);
	}
	
	@Override
	public String toString(){
		return naziv + "- Vegetarijansko: " + vegetarijansko + "- Cijena: " + cijena;
	}
}