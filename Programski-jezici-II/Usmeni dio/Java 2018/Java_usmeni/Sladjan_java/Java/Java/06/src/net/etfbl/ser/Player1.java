package net.etfbl.ser;

import java.io.*;

class Player1 implements Externalizable {
	public Player1() {
		System.out.println("Player1 Constructor");
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("Player1.writeExternal");
	}

	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		System.out.println("Player1.readExternal");
	}
}

