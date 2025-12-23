package ba.icbl.concurrency;


import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new PrintTask(), 0, 2000);
	}
}

class PrintTask extends TimerTask {
	public void run() {
		System.out.println("PrintTask ispis...");
	}
}