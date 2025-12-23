package ba.icbl.concurrency;


public class ThreadTest {
	public static void main(String[] args) {
		System.out.println("Pocetak izvrsavanja osnovne niti");
		Runnable runnable = new MyThread("1");
		Thread thread = new Thread(runnable);
		Runnable runnable2 = new MyThread("2");
		Thread thread2 = new Thread(runnable2);
		Runnable runnable3 = new MyThread("3");
		Thread thread3 = new Thread(runnable3);
		thread.start();
		thread2.start();
		thread3.start();
		System.out.println("Kraj izvrsavanja osnovne niti");
	}
}

class MyThread implements Runnable {
	String name = "";
	
	public MyThread(String name){
		this.name = name;
	}
	
	public void run() {
		System.out.println("Pocetak izvrsavanja niti " + name);
		for(int i=0; i<500; i++)
			System.out.println("Nit " + name + ": " + i);
		System.out.println("Kraj izvrsavanja niti " + name);
	}

}