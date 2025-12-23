package net.etfbl.oop.package1;

public class Test {

	public static void main(String[] args) {
		W niz[] = {new W1(), new W1(), new W2()};
		for (W w : niz) {
			w.metoda();
		}
	}

}

abstract class W{
	public void metoda(){
		
	}; 
}

class W1 extends W{
	
}
class W2 extends W{
	
}