import java.util.*;

class MatricnaStanica extends Letjelica implements EnergetskiStitInterface, NeutralizacijaRaketeInterface{
	Oprema oprema;
	Laser laser;
	Raketa raketa;
	
	public MatricnaStanica(){
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
	public void neutralisiRaketu(){
		System.out.println(this + " aktivirao sistem za neutralizaciju rakete.");
	}
	
	@Override
	public String toString(){
		return super.toString() + " MATRICNA STANICA \n\tOprema:" + oprema + " \n\tSnaga lasera:" + laser.snaga + "\n\t(X,Y) = (" + red + "," + kolona + ")";
	}
	
}