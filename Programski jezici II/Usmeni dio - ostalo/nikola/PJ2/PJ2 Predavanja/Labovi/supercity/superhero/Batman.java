package supercity.superhero;

import supercity.resident.Resident;
import supercity.gadget.Gadget;

public class Batman extends Resident implements IFly{
	private static Gadget batmobile = new Gadget("Batmobile");
	public Batman() {
		super("Bruce Wayne");
	}
}