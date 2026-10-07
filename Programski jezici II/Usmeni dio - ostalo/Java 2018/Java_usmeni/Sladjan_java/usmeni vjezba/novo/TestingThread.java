public class TestingThread extends Thread
{
  public static Object lock = new Object();
  
  public void run()
  {
    try
    {
      wait(20);
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
  }
 
  public static void main(String[] args)
  {
    TestingThread t = new TestingThread();
    t.start();
  }
  
  
  
}