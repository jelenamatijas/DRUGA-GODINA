package ba.icbl.concurrency.new_api;

import java.util.concurrent.Callable;

class MyCallable implements Callable<Result> {
	private static int i=0;
	String name = "";
	
	public MyCallable(String name){
		this.name = name;
	}

	@Override
	public Result call() throws Exception {
        for (int i=0;i<=100;i+=20) {
            System.out.println("Worker thread: " + name + ", percent complete: " + i );
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            System.out.println("========== Worker thread: " + name + ", COMPLETED!!! ==========");
        }
		return new Result(++i);
	}

}

class Result{
	private int result;
	public Result(int res) {
		result = res;
	}
	public int getResult() {
		return result;
	}
}