class MyThread extends Thread { //? public
	public void run(){
		try{sleep(1000);}catch(Exception ex){}
		System.out.println("run - ime niti: " + Thread.currentThread().getName());
	}
	public static void main(String args[])throws Exception{
		Thread myThread = new MyThread();
		myThread.start();
		Thread nit = new Thread(){
	
			public void run(){
			try{sleep(2000);}catch(Exception ex){}
			System.out.println("Ja sam prvi :D ");}
		};
		nit.start();
		nit.join();
		System.out.println("main - ime niti: " + Thread.currentThread().getName());
	}
}