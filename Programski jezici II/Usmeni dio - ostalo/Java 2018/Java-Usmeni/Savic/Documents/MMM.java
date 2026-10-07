public class MMM
{
  public static void main(String [] args)
  {
     AB ab = new AB();
     BA ba = new BA();
     if (ab.new Inner().f() == 0);
     System.out.println(new AB.Klasa().konstanta);
     AB.Klasa k = new AB.Klasa();
     AB.Klasa c = new AB.Klasa();
     AB.Inner n = ab.new Inner();
  }
}
class AB
{
  private int x = 5;
  static{
    System.out.println("AB static.");
  }
  {
    System.out.println("AB nestatic.");
  }
  Inner inner;
  public AB()
  {
   inner = new Inner();
  }
  protected static class Klasa
  {
    int konstanta;
    static void pozovi()
    {
      System.out.println("pozovi");
    }
    static{
      System.out.println("Static klasa");
    }
    {
      System.out.println("KRALJU");
      konstanta = ++konstanta;
    }
  }
  protected class Inner
  {
     /*static{
      System.out.println("Inner static.");
    }*/
   /* static int deset;
    static private void g()
    {
      System.out.println("G");
    }*/
    private int x = 3;
    int f()
    {
      System.out.println(x);
      System.out.println(AB.this.x);
      return 1;
    }
    {
      System.out.println("Inner nestatic.");
    }  
  }
  public static void main(String [] args)
  {
      BA ba = new BA();
      AB ab = new AB();
      //ba.new Inner().f();
      //ab.this.f();
     
  }
}

class BA extends AB
{
  static{
    System.out.println("BA static.");
  }
  {
    System.out.println("BA nestatic.");
  }
}

