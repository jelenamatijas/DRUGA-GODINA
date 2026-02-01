import java.util.*;
import java.io.*;

class MatricnaStanica extends Letjelica implements EnergetskiStitInterface, NeutralizacijaRaketeInterface{
	Oprema oprema;
	Laser laser;
	Raketa raketa;
	
	public MatricnaStanica(){
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
	public void neutralisiRaketu(){
		System.out.pintln(this + " aktivirao sistem za neutralizaciju rakete.");
	}
	
	@Override
	public String toString(){
		return super.toString() + " Oprema:" + oprema + " Snaga lasera:" + laser.snaga + " Koordinate cilja rakete: (" + red + ","+ raketa.x + ")";
	}
	
}