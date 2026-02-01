package supercity.superhero;

import supercity.resident.Resident;

public class Supergirl extends Resident implements ISuperpowers, IFly {
	public Supergirl() {
		super("Kara Zor-El");
	}
}