import java.lang.String;



public class Sin extends Rod
{
  public int y = 0;
  
   public static void main(String[] args)
  {
    Rod r = new Rod();
    Rod temp  = new Rod();
    Sin s = new Sin();
    
    System.out.println(r.x);
    
    r.x = 7;
    temp = r;
    r = s;
    if(r instanceof Sin)
    {
      r.x = 5;
      System.out.println("Jeste instanca");
    }
    
    System.out.println(r.x + " " + temp.x);
    
  
   }
  
  
}

class Rod
{
  public int x = 0;
  
  
}


