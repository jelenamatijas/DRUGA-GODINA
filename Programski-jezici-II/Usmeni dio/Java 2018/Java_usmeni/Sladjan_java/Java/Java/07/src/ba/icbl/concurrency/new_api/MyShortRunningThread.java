package ba.icbl.concurrency.new_api;

class MyShortRunningThread implements Runnable {
	String name = "";
	
	public MyShortRunningThread(String name){
		this.name = name;
	}
	
	public void run() {
		System.out.println("Worker thread: " + name);
	}
}