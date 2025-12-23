public class Obj
{
  public Obj()
  {
    super();
    System.out.println("Prolazi");
  }
  
  public final int metoda()
  {
    System.out.println("Final metoda");
  }
  
  public void metoda()
  {
    System.out.println("Void metoda");
  }
  
  public static void main (String[] args)
  {
    Obj o = new Obj();
  }
  
}