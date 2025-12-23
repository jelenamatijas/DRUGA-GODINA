package ba.icbl.concurrency.new_api;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceTestNoShutdown {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		for(int i=1; i<10; i++){
			executor.submit(new Thread(new MyThread("" + i)));
		}
	}
}