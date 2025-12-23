package ba.icbl.concurrency.sync;

public class Incrementator extends Thread {
	private Counter counter;
	private int number;
	public Incrementator(Counter counter, int number) {
		this.counter = counter;
		this.number = number;
	}
	public void run() {
		for (int i = 0; i < number; i++){
			counter.increment1();
		}
	}
	
	public static void main(String args[]){
		Counter counter = new Counter();
		Incrementator inc1 = new Incrementator(counter, 10);
		Incrementator inc2 = new Incrementator(counter, 10);
		inc1.start();
		inc2.start();
	}
}
