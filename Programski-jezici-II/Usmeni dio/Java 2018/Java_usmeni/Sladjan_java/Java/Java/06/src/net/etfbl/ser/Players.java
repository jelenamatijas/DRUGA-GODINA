package net.etfbl.ser;

import java.io.*;

public class Players {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		System.out.println("Constructing objects:");
		Player1 b1 = new Player1();
		Player2 b2 = new Player2();
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Players.out"));
		System.out.println("Saving objects:");
		o.writeObject(b1);
		o.writeObject(b2);
		o.close();
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("Players.out"));
		System.out.println("Recovering b1:");
		b1 = (Player1) in.readObject();
		System.out.println("Recovering b2:");
		b2 = (Player2) in.readObject();
	}
}