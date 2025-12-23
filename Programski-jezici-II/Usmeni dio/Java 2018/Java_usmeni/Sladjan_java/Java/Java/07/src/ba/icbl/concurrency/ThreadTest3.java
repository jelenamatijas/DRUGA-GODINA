package ba.icbl.concurrency;


public class ThreadTest3 {
	public static void main(String[] args) {
		Runnable runnable = new MyThread3("1", 10000);
		Thread thread = new Thread(runnable);
		Runnable runnable2 = new MyThread4("2");
		Thread thread2 = new Thread(runnable2);
		Runnable runnable3 = new MyThread4("3");
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

class MyThread3 implements Runnable {
	String name = "";
	int numberOfExec; 
	
	public MyThread3(String name, int numberOfExec){
		this.name = name;
		this.numberOfExec = numberOfExec;
	}
	
	public void run() {
		System.out.println("Pocetak izvrsavanja niti " + name);
		try{
			System.in.read();
			for(int i=0; i<numberOfExec; i++){
				if(i % 1000 == 0){
					System.out.println("Nit " + name + ": " + i/1000);
	//				Thread.yield();
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		System.out.println("Kraj izvrsavanja niti " + name);
	}
}

class MyThread4 implements Runnable {
	String name = "";
	
	public MyThread4(String name){
		this.name = name;
	}
	
	public void run() {
		System.out.println("Pocetak izvrsavanja niti " + name);
		for(int i=0; i<500; i++)
			System.out.println("Nit " + name + ": " + i);
		System.out.println("Kraj izvrsavanja niti " + name);
	}

}