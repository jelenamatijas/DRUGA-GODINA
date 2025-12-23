package ba.icbl.concurrency;


public class FirstThread extends Thread {

	public FirstThread(String name){
		setName(name);
	}
	public void run(){
		for(int i=0; i<100; i++)
			System.out.println("nit: " + getName());
		System.out.println("Kraj niti " + getName());
	}
	
	public static void main(String[] args) {
		System.out.println("Pocetak programa");
		FirstThread thread1 = new FirstThread("1");
		thread1.start();
		FirstThread thread2 = new FirstThread("2");
		thread2.start();
		FirstThread thread3 = new FirstThread("3");
		thread3.start();
		System.out.println("Kraj programa");
	}
}
