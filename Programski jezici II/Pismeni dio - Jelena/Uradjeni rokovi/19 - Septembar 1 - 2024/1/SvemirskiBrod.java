

class SvemirskiBrod extends Letjelica implements EnergetskiStitInterface{
	Oprema oprema;
	Laser laser;
	Raketa raketa;
	
	public SvemirskiBrod(){
		super();
		oprema = Oprema.NAVIGACIJSKI_SISTEM;
		laser = new Laser();
		raketa = new Raketa();
	}
	
	@Override
	public void stitAktivan(){
		System.out.println(this + " aktivirao energetski stit.");
	}
	
	@Override
	public String toString(){
		return super.toString() + " SVEMIRSKI BROD \n\tOprema:" + oprema + "\n\tSnaga lasera:" + laser.snaga + "\n\t(X,Y) = (" + red + "," + kolona + ")";
	} 
}