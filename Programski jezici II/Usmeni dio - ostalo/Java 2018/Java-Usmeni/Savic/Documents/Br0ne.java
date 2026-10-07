public class Br0ne
{
  
  public static void main(String [] args)
  {
      KKK.f();
      new KKK().m();
      //return ;
  }
}

class KKK
{
  static{
    System.out.println("KKK static.");
  }
  {
    System.out.println("KKK nestatic.");
    Thread x = new Thread("a");
  }
  Thread x = new Thread("s");
  void m()
  {
    System.out.println(x.getName());
  }
  //int x;
  static void f()
  {
      System.out.println("KKK");
      class P{
 int konstanta = 5;
      }
      P p = new P();
      System.out.println(p.konstanta);
  }
}