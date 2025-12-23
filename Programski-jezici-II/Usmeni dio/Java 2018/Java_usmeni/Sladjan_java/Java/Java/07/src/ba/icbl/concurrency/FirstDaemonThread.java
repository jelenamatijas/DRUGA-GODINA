package ba.icbl.concurrency;


public class FirstDaemonThread extends Thread {

	public FirstDaemonThread(String name){
		setName(name);
		setDaemon(true);
	}
	public void run(){
		System.out.println("Start niti " + getName());
		for(int i=0; i<100; i++)
			System.out.println("nit: " + getName());
		System.out.println("Kraj niti " + getName());
	}
	
	public static void main(String[] args) {
		System.out.println("Pocetak programa");
		FirstDaemonThread thread1 = new FirstDaemonThread("1");
		thread1.start();
		FirstDaemonThread thread2 = new FirstDaemonThread("2");
		thread2.start();
		FirstDaemonThread thread3 = new FirstDaemonThread("3");
		thread3.start();
		System.out.println("Kraj programa");
	}
}
