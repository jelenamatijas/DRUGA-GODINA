package ba.icbl.concurrency;


public class ThreadTest2 {
	public static void main(String[] args) {
		Runnable runnable = new MyThread2("1", 10000);
		Thread thread = new Thread(runnable);
		Runnable runnable2 = new MyThread2("2", 100000);
		Thread thread2 = new Thread(runnable2);
		Runnable runnable3 = new MyThread2("3", 100000);
		Thread thread3 = new Thread(runnable3);
		thread.start();
		thread2.start();
		thread3.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Kraj izvrsavanja osnovne niti");
	}
}

class MyThread2 implements Runnable {
	String name = "";
	int numberOfExec; 
	
	public MyThread2(String name, int numberOfExec){
		this.name = name;
		this.numberOfExec = numberOfExec;
	}
	
	public void run() {
		System.out.println("Pocetak izvrsavanja niti " + name);
		for(int i=0; i<numberOfExec; i++){
			if(i % 1000 == 0){
				System.out.println("Nit " + name + ": " + i/1000);
				Thread.yield();
			}
		}
		System.out.println("Kraj izvrsavanja niti " + name);
	}
}