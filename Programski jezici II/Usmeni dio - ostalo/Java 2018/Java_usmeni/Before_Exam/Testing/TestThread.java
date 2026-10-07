public class TestThread extends Thread{
  public TestThread(String name){
    super(name);
  }
  
  public static void main(String[] args){
    TestThread t = new TestThread("A");
    TestThread t2 = new TestThread("B");
    TestThread t3 = new TestThread("C");
    try{
      t.start();
      t2.start();
      t3.start();
      t.join();
      System.out.println("T JOIN");
      //t2.join();
      System.out.println("T2 JOIN");
      //t3.join();
      System.out.println("T3 JOIN");
    } catch(Exception ex){
      ex.printStackTrace();
    }
  }
  
  public void run(){
    for(int i=0; i<20; i++)
      System.out.println("Run: " + this.getName());
  }
}