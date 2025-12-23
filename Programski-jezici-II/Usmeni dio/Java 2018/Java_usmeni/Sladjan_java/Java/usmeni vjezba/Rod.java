import java.lang.String;


public class Rod
{
  public int x = 0;
  
  
  public static void main(String[] args)
  {
    Rod r = new Rod();
    Sin s = new Sin();
    
    r=s;
    r.y = 5;
  
}

class Sin extends Rod
{
  public int y = 0;
}


}


