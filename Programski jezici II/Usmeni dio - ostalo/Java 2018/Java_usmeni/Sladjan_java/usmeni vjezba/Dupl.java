public class Dupl implements Interfejs1,Interfejs2
{
   private static  int skl;
  static Dupl d = new Dupl(){
  public void metoda()
  {
      System.out.println(skl);
  }};
  
  static class Bar{
    public void metoda()
    {
      System.out.println(skl);
    }
  }
  
  public void metoda()
  {
    System.out.println("Metoda");
    
  }
  public static void main(String[] args)
  {
  Interfejs1 i1 = new Dupl();
  i1.metoda();
  d.metoda();
  Interfejs2 i2 = new Dupl();
  i2.metoda();
  O oo = new O();
  oo.metoda();
  }
}

class O extends Dupl
{
  public void metoda()
  {
    super.d.metoda();
  }
}


interface Interfejs1
{
   void metoda();
}

interface Interfejs2
{
  void metoda();
}