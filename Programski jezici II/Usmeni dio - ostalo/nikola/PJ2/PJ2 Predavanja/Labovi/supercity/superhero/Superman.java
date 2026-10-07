package supercity.superhero;

import supercity.resident.Resident;

public class Superman extends Resident implements ISuperpowers, IFly {
	public Superman() {
		super("Clark Kent");
	}
}