public class Proba2{
	public static void main(String[] args) throws Exception{
		Proba2 p2 = new Proba2();
		System.out.println("pocinje");
		new Thread(()->{
			for(int i = 0; i<5; i++)
			try{
				
				System.out.println("asa");
				Thread.sleep(500);
			}catch(InterruptedException ex){}
		}).start();
		try{
		p2.metoda1();}
		catch(ArrayIndexOutOfBoundsException nu){
			System.out.println("array");
		}catch(Exception ex){
			System.out.println("exception");
		}
		
		System.out.println("nastavilo se");
		
	}
	public void metoda() throws Exception{
		try{
			System.out.println("nesto");
		int[] niz = new int[2];
		//niz[2] = 3;
		throw new Exception();
		}
		finally{
			System.out.println("finalni");
		}
	}
	public void metoda1() throws Exception{
		metoda();
	}
}