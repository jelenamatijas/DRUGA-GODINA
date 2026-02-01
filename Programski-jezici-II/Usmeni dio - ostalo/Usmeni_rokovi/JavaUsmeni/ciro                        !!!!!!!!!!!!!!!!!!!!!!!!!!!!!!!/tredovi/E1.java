public class E1 implements Runnable {
	static volatile int c = 0;
	E1() {
		System.out.println("E1");
	}
	public static void main(String[] args) {
		System.out.println("Pocetak");
		new Thread(new E1()).run();
		new Thread(new E2()).run();
		System.out.println("parallel");
		metoda();
		new Thread(new E3()).run();
		new Thread(new E4()).run();
		System.out.println("parallel 2");
		metoda();
	}
	public void run() {
		if (c<3) {
			System.out.println("E2.run " + ++c);
			return;
		}
	}
	public static void metoda() {
		Thread[] t = {new Thread(new E1()), new Thread(new E2())};
		for(Thread thr : t) {
			thr.start();
		}
		Thread e3 = new Thread(new E3(t[1]));
		Thread e4 = new Thread(new E4(e3));
		e3.start();
		e4.start();
		try {
			System.out.println(t[1]);
			t[1].join();
		} catch (InterruptedException ex) {}
		final I1 r2 = ()->{
			System.out.println("r2 run");
			for(int i = 0; i < 5; i++) {
				System.out.println(i);
			}
			try {
				e4.join();
			} catch (InterruptedException ex) {}
		};
		Thread x = new Thread(r2);
		final Runnable r1 = ()->{
			System.out.println("r1 run");
			for(int i = 5; i > 0; i--) {
				System.out.println(i);
			}
			try {
				x.join();
			} catch (InterruptedException ex) {}
		};
		Thread y = new Thread(r1);
		x.start();
		y.start();
		t[2] = new Thread(new E3());
		try {
			t[2].join();
		} catch (InterruptedException ex) {}
	}
}
class E2 extends E1 {
	public void run() {
		if (c<3) {
			System.out.println("E2.run " + ++c);
			return;
		}
	}
}
class E3 extends E4 {
	Thread e2;
	E3(){}
	E3(Thread e2) {
		this.e2 = e2;
		System.out.println("E3");
	}
	public void run() {
		System.out.println("E3.run");
		try {
			e2.join();
			System.out.println("E3E2join");
		} catch(InterruptedException ex) {}
		finally {
			System.out.println("E3 end");
		}
	}
}
class E4 extends Thread {
	Thread e3;
	E4() {
		System.out.println("E4");
	}
	E4(Thread e3) {
		System.out.println("E4(E3)");
	}
	public void run() {
		System.out.println("E4 run");
		try{
			e3.join();
			System.out.println("E4E3join");
		} catch(InterruptedException ex) {}
		finally {
			System.out.println("E4 end");
		}
	}
}
interface I1 extends Runnable {
	static final int c = 1;
}