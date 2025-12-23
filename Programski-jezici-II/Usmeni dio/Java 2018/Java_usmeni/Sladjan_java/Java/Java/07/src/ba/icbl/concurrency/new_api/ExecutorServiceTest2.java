package ba.icbl.concurrency.new_api;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest2 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		for(int i=1; i<10; i++){
			executor.execute(new Thread(new MyThread("" + i)));
		}
	   
	    executor.shutdown();
	    System.out.println("-----------------------");
	    executor.awaitTermination(15, TimeUnit.SECONDS);
	    System.out.println("All tasks are finished!");

	
	}

}