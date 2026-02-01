package supercity.badguy;

import supercity.resident.Resident;
import java.util.Random;

public class BadGuy extends Resident {
	private boolean cryptonite;
	public BadGuy(String name) {
		super(name);
		cryptonite = new Random().nextBoolean();
	}
	
	public boolean hasCryptonite() {
		return cryptonite;
	}
}