

class Beletristika extends Knjiga{
	String zanr;
	
	public Beletristika(){
		super();
		zanr = "Zanr" + Main.rand.nextInt(10);
	}
	
	@Override
	public String toString(){
		return super.toString() + " Zanr:" +zanr;
	}
}