

class SvemirskiBrod extends Letjelica implements EnergetskiStitInterface{
	Oprema oprema;
	Laser laser;
	Raketa raketa;
	
	public SvemirskiBrod(){
		super();
		oprema = Oprema.NAVIGACIONI_SISTEM;
		laser = new Laser();
		raketa = new Raketa();
	}
	
	@Override
	public void stitAktivan(){
		System.out.pintln(this + " aktivirao energetski stit.");
	}
	
	@Override
	public String toString(){
		return super.toString() + " Oprema:" + oprema + " Snaga lasera:" + laser.snaga + " Koordinate cilja rakete:(" + red + ","+ raketa.x + ")";
	} 
}