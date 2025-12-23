package ba.icbl.concurrency;


public class CoresTest {
	
	public static void main(String[] args) {
		testThreads();
	}
	
	private static void testThreads(){
		for(int i=0;i<10000;i++)
			new FirstThread(""+i).start();
	}

	private static void testThread(){
		for(int i=0;i<1_000_000;i++)
			System.out.println("main: " + i);
	}
}
