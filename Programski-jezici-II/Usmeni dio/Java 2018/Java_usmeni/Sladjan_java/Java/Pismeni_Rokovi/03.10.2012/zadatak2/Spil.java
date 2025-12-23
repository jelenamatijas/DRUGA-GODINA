
public class Spil
{
  public static Karta[] spil = new Karta[52];
  public static Object lock;
  public static String lo;
    
    static{
      lock = new Object();
      lo = "";
    }
  
  public Spil()
  {
    for(int i=0;i<52;i++)
    {
      spil[i] = new Karta();
    }
    
  }
  
}