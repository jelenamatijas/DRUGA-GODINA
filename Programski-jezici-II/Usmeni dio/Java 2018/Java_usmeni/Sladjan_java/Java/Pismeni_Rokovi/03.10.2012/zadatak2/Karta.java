import java.util.Random;
import java.lang.String;


public class Karta
{
  public int value;
  
  public Karta()
  {
    Random rand = new Random();
    value = rand.nextInt(13) + 1;
  }
  
  
}