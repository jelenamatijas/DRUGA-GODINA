package net.etfbl.oop.package5;

class Letjelica {
}

class MotornaLetjelica extends Letjelica {
}

class Avion extends MotornaLetjelica {
}

class LetjelicaBezMotora extends Letjelica {
}

class Zmaj extends LetjelicaBezMotora {
}

public class Letjelice {
	public static void main(String[] args) {
		Letjelica letjelica = new Letjelica(); // 1
//		boolean rezultat = letjelica instanceof String;//2
		System.out.println(letjelica instanceof LetjelicaBezMotora); // 3
		System.out.println(letjelica instanceof Avion); // 4
//		letjelica = new Zmaj(); // 5
		System.out.println(letjelica instanceof LetjelicaBezMotora); // 6
		System.out.println(letjelica instanceof Letjelica); // 7
		System.out.println(letjelica instanceof MotornaLetjelica); // 8
		System.out.println(letjelica instanceof Avion); // 9
		Zmaj zmaj = new Zmaj(); // 10
		System.out.println(zmaj instanceof LetjelicaBezMotora); // 11
		System.out.println(zmaj instanceof Letjelica); // 12
//		System.out.println(zmaj instanceof MotornaLetjelica); // 13
//		System.out.println(zmaj instanceof Avion); // 14
	}
}
