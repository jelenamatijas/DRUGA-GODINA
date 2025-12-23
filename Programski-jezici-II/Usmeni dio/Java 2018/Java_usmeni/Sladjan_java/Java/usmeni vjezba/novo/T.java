public class T extends Thread
{
  public T ()
  {
    start();
  }
  
  public void run()
  {
    int i = 0;
    String k = "FALSE";
    Runnable r = new Runnable()
    {
      public void run()
      {
        i = 1;
      }
    };
    new Thread(r).start();
    
    synchronized(k)
    {
      System.out.println(k);
    }
    
  }
  
}