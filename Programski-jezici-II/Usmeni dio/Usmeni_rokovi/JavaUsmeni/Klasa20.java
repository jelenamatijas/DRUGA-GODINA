public class Klasa20 extends Thread{
	public Klasa20() {
		System.out.println("Klasa20()");
	}
	public static void main(String x[]) {
		new Klasa20().start();
	}
	public void run() {
		System.out.println("first");
		Thread2 niz[] = {new Thread3(1), new Thread2(), new Thread3(3) };
		for (Thread2 e : niz) {
			if (e instanceof Thread3)
				new Thread(e).start();
			else{
				try {
					e.start();
					e.join();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		System.out.println("last");
	}
}
class Thread2 extends Thread {
	static int c = 10;
	int id;
	public Thread2() {
		this(0);
	}
	Thread2(int id) {
		System.out.println("Thread2()");
		this.id = (id>0)?id:c++;
	}
	public void run() {
	for (int i = 1; i < 6; i++) {
		try {
			sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread2 - " + id + ": " + i);
	}
	}
}
class Thread3 extends Thread2 implements Runnable {
	Thread3(int id) {
		super(id);
		System.out.println("Thread3()");
	}
}