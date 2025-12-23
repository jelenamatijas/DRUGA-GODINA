package ba.icbl.concurrency.philosophers;


public class Stick {
	private boolean free;

	public Stick() {
		free = true;
	}
	public synchronized void take() {
		if (!free)
			try {
				wait();
			} catch (Exception ex) {
			}
		free = false;
	}
	public synchronized void release() {
		free = true;
		notify();
	}
}