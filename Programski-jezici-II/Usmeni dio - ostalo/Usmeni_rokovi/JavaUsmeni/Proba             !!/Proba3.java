public class Proba3{
	public static void main(String[] args){
		Thread t1 = new Thread(()->{
			for(int i = 0; i<5; i++)
			try{
				Thread.sleep(500);
				System.out.println("asa");
			}catch(InterruptedException ex){}
		});
		Thread t2 = new Thread(t1);
		t2.start();
		
		try{
			t1.join();
		}catch(Exception ex){
			System.out.println("cudno");
		}
		System.out.println("ispisano");
		
	}
}