package ba.icbl.concurrency.philosophers;


public class Philosopher2 extends Thread {

	public Philosopher2(int index, Stick left, Stick right, String type,
			int numberOfIterations) {
		this.index = index;
		if(type.equals("L")){
			this.first = left;
			this.second = right;
		}else{
			this.first = right;
			this.second = left;
		}
		this.numberOfIterations = numberOfIterations;
	}

	public void run() {
		while (numberOfIterations > 0) {
			first.take();
			try {
				sleep(1000);
			} catch (Exception e) {
			}

			second.take();

			System.out.println("[" + index + "] eating...");
			try {
				Thread.sleep(200);
			} catch (Exception ex) {
			}

			first.release();
			second.release();
			numberOfIterations--;
		}
		System.out.println("[" + index + "] finished...");
	}

	private int index;
	private Stick first;
	private Stick second;
	private int numberOfIterations;
}
