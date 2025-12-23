package supercity.superhero;

import supercity.resident.Resident;
import supercity.gadget.Gadget;

public class Green_Arrow extends Resident implements IFly {
	private static Gadget arrow = new Gadget("Arrow");
	public Green_Arrow() {
		super("Oliver Queen");
	}
}