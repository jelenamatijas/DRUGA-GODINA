package ba.icbl.concurrency.new_api;

class MyThread implements Runnable {
	String name = "";

	public MyThread(String name) {
		this.name = name;
	}

	public void run() {
		for (int i = 0; i <= 100; i += 20) {
			System.out.println("Worker thread: " + name
					+ ", percent complete: " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}
		System.out.println("========== Worker thread: " + name + ", COMPLETED!!! ==========");
	}

}