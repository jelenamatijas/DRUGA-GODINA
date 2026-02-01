class Covjek extends Ratnik implements CovjekInterface{
	boolean imaAmajliju;
	boolean dobar;
	
	Covjek(String ime, double snaga, boolean amajlija, boolean dobar, int x, int y){
		super(ime, snaga,x,y);
		imaAmajliju = amajlija;
		this.dobar = dobar;
	}
	
	public boolean getDobar(){return dobar;}
	
	public boolean getImaAmajliju(){return imaAmajliju;}
	
	public double getSnaga(){return snaga;}
	
	@Override
	boolean krajReda(int newX){
		if(newX >= Main.REDOVI){
			return true;
		}
		return false;
	}
	
	@Override
	int getNewX(){
		if(x+1 >= Main.REDOVI) return Main.REDOVI; 
		return Main.rand.nextInt(x+1, Main.REDOVI);
	}
	
	@Override
	int getNewY(){ 
		return Main.rand.nextInt(0, Main.KOLONE);
	}
	
	@Override
	public String toString(){
		return "Covjek " + ime + ".";
	}
}