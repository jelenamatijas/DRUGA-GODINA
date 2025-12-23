package ba.icbl.concurrency.philosophers;


public class DiningPhilosophers {

	private static int ITER = 10;
	private static int N = 5;

	public static void main(String[] args) {
		Stick[] sticks = new Stick[N];
		Philosopher[] philosophers = new Philosopher[N];
		//Philosopher2[] philosophers = new Philosopher2[N];
		for (int i = 0; i < N; i++)
			sticks[i] = new Stick();
		for (int i = 0; i < N; i++) {
			philosophers[i] = new Philosopher(i+1, sticks[(i-1+N) % N], sticks[i],ITER);
			//philosophers[i] = new Philosopher2(i + 1, sticks[(i - 1 + N) % N],	sticks[i], (i + 1) % 2 == 1 ? "L" : "R", ITER);
			philosophers[i].start();
		}

	}

}
