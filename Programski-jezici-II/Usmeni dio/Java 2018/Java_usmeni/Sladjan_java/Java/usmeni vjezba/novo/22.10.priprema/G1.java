public class G1<T> implements GI
{
  public void nekaMetoda()
  {
    System.out.println();
  }
  
  
  public static void main(String ... Args)
  {
  }
  
}




interface GI<T>
{
  public static final T t;
  
  public void nekaMetoda();
  
}

