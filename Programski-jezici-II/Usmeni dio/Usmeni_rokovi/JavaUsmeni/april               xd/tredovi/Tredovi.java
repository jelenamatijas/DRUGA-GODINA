public class Tredovi{
	public static void main(String[] args){
		//new Thread(new T1()).start();
		
		T1 t = new T1();
		T1[] niz = new T1[2];
		t.setDaemon(true);
		t.start();
		try{
			Thread.sleep(1000);
		}catch(InterruptedException ex){
			
		}
		System.out.println("hehe");
		
	}
}
class T1 extends Thread{
	public void run(){
		while(true){
			try{
				sleep(500);
				System.out.println("run T1");
			}catch(InterruptedException ie){}
		
		}
	}
}