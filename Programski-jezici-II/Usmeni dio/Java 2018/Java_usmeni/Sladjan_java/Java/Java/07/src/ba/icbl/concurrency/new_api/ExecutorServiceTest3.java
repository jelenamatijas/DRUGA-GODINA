package ba.icbl.concurrency.new_api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceTest3 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		ArrayList<MyCallable> taskList = new ArrayList<MyCallable>();
		for(int i=1; i<10; i++){
			taskList.add(new MyCallable("" + i));
		}
		List<Future<Result>> results = executor.invokeAll(taskList);
	    System.out.println("-----------1------------");
	    executor.shutdown();
		for (Future<Result> future : results) {
			System.out.println(future.get().getResult());
		}
	    System.out.println("-----------2------------");

	
	}

}