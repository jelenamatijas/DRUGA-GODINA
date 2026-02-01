package src;

public class CountdownThread extends Thread{
	private Thread toRun;
	
	public CountdownThread(Thread toRun){
		this.toRun = toRun;
	}
	
	public void run(){
		Thread.sleep(20000);
		toRun.start();
	}
}