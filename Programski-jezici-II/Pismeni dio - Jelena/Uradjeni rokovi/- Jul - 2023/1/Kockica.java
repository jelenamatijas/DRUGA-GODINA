class Kockica{
	double povrsina, obim;
	String boja;
	
	Kockica(double p, double o, String b){
		povrsina = p;
		obim = o;
		boja = b;
	}
	
	public int getSirina(){
		return 0;
	}
	
	public int getDuzina(){
		return 0;
	}
	
	public int getPoluprecnik(){
		return 0;
	}
	
	@Override
	public String toString(){
		return "Povrsina: " + povrsina + "Obim: " + obim + " Boja: " + boja;
	}
}