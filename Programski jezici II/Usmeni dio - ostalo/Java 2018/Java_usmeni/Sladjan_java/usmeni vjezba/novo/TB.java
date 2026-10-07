public class TB implements T1,T2
{
  public int metoda()
  {
    System.out.println("VOID metoda");
  }
  
  public int metoda()
  {
    return 2;
  }
  
}



public interface T1
{
  public void metoda();
}

public interface T2
{
  public int metoda();
}