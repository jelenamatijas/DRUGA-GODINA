

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
		System.out.println(this + " aktivirao sistem za neutralizaciju rakete.");
	}
	
	@Override
	public String toString(){
		return super.toString() + " BESPILOTNA SONDA \n\tOprema:" + oprema + "\n\tSnaga lasera:" + laser.snaga + "\n\t(X,Y) = (" + red + "," + kolona + ")";
	}
}