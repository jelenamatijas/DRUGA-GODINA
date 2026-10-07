public class Smorcina
{
  public Smorcina
  {
    System.out.println("Konstruktor");
    s = "BLABLA";
    System.out.println(s);
  }
  String s = "Smorcina";
  static{
    System.out.println("STATIC");
  }
  {
    System.out.println("NESTATIC" + " " + s);
  }
  public static void main(String [] args)
  {
    new Smorcina();
  }
}