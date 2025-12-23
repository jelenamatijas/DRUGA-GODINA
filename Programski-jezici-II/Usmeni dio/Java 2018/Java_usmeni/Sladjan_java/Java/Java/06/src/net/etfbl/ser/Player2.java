package net.etfbl.ser;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Player2 implements Externalizable {
	Player2() {
		System.out.println("Player2 Constructor");
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("Player2.writeExternal");
	}

	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		System.out.println("Player2.readExternal");
	}
}

