package ba.icbl.concurrency.philosophers;


public class Philosopher extends Thread {

	public Philosopher(int index, Stick left, Stick right,
			int numberOfIterations) {
		this.index = index;
		this.left = left;
		this.right = right;
		this.numberOfIterations = numberOfIterations;
	}

	public void run() {
		while (numberOfIterations > 0) {
			left.take();
//			try {
//				sleep(1);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			right.take();
			System.out.println("[" + index + "] eating...");
			try {
				Thread.sleep(200);
			} catch (Exception ex) {}
			left.release();
			right.release();
			numberOfIterations--;
		}
		System.out.println("[" + index + "] finished...");
	}
	private int index;
	private Stick left;
	private Stick right;
	private int numberOfIterations;
}
