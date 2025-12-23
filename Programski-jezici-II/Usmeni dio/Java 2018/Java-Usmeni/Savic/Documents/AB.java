public class MMM
{
  public static void main(String [] args)
  {
     AB ab = new AB();
     BA ba = new BA();
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
  private class Inner
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
    void f()
    {
      System.out.println(x);
      System.out.println(AB.this.x);
    }
    {
      System.out.println("Inner nestatic.");
    }	 
  }
  public static void main(String [] args)
  {
      BA ba = new BA();
      AB ab = new AB();
      ab.new AB();
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

