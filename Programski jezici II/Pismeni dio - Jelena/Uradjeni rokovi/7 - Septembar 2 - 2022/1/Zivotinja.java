class Zivotinja extends Ratnik{
	Zivotinja(String ime, double snaga, int x, int y){
		super(ime, snaga,x,y);
	}
	
	@Override
	int getNewX(){
		if(x == 0) return 0; 
		return Main.rand.nextInt(0, x);
	}
	
	@Override
	int getNewY(){ 
		return Main.rand.nextInt(0, Main.KOLONE);
	}
	
	@Override
	boolean krajReda(int newX){
		if(newX <=0 ){
			return true;
		}
		return false;
	}
}