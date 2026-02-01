public class AnoTesting extends Thread
{
  private String name;
  
  public AnoTesting(String name)
  {
    this.name = name;
  }
  
 public  Runnable p = new Runnable()
  {
    public void run()
    {
      metoda();
    }
    public void metoda()
    {
      System.out.println("ISPIS");
    }
  };
  
  public void run()
  {
    Runnable r = new Runnable()
    {
      public void run()
      {
        for(int i = 0; i<5; i++) System.out.println(name + "2: " + i);
      }
    };
    new Thread(r).start();
    for(int i = 0; i<5; i++) System.out.println(name + "1: " + i);
  }
  
  public static void main(String[] args)
  {
    AnoTesting a = new AnoTesting("A");
    AnoTesting b = new AnoTesting("B");
    
    a.start();
    b.start();
    a.p.run();
  }
  
  
}