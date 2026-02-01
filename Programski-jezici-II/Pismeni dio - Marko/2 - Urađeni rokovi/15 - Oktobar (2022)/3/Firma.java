import java.util.*;

public class Firma {
	
	ArrayList<Skladiste> skladista = new ArrayList<>();
	
	public Firma() {
		skladista.add(new Skladiste());
		skladista.add(new Skladiste());
	}
	
	@Override
	public String toString() {
		return "Firma{skladista=" + skladista + "}";
	}
}