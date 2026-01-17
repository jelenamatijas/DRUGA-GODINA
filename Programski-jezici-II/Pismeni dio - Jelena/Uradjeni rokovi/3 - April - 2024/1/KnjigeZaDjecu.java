

class KnjigeZaDjecu extends Knjiga{
	int starostDjeteta;
	
	public KnjigeZaDjecu(){
		super();
		starostDjeteta = Main.rand.nextInt(5, 13);
	}
	
	@Override
	public String toString(){
		return super.toString() + " Preporucena starost djeteta:" +starostDjeteta;
	}
}