package ba.icbl.concurrency.new_api;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest4 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Date start = new Date();
		ExecutorService executor = Executors.newFixedThreadPool(10000);
		for(int i=1; i<10000; i++){
			executor.execute(new Thread(new MyShortRunningThread("" + i)));
		}
	    executor.shutdown();
	    executor.awaitTermination(100, TimeUnit.SECONDS);
		Date end = new Date();
		

		Date start2 = new Date();
		ExecutorService executor2 = Executors.newCachedThreadPool();
		for(int i=1; i<10000; i++){
			executor2.execute(new Thread(new MyShortRunningThread("" + i)));
		}
	    executor2.shutdown();
	    executor2.awaitTermination(100, TimeUnit.SECONDS);
		Date end2 = new Date();
		System.out.println("FixedThreadPool: " + (end.getTime() - start.getTime()));
		System.out.println("CachedThreadPool: " + (end2.getTime() - start2.getTime()));

	
	}

}