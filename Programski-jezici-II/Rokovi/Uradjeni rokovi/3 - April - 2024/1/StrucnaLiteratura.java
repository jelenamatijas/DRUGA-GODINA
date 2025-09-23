

class StrucnaLiteratura extends Knjiga{
	String oblast;
	
	public StrucnaLiteratura(){
		super();
		oblast = "Oblast" + Main.rand.nextInt(11);
	}
	
	@Override
	public String toString(){
		return super.toString() + " Oblast:" +oblast;
	}
}