

class Sonda extends Letjelica implements NeutralizacijaRaketeInterface{
	Oprema oprema;
	Laser laser;
	
	public Sonda(){
		super();
		oprema = Oprema.SENZOR;
		laser = new Laser();
	}
	
	@Override
	public void neutralisiRaketu(){
		System.out.pintln(this + " aktivirao sistem za neutralizaciju rakete.");
	}
	
	@Override
	public String toString(){
		return super.toString() + " Oprema:" + oprema + " Snaga lasera:" + laser.snaga;
	}
}