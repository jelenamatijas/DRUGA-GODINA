public class Smorcina
{
  String s = "Smorcina";
  public Smorcina()
  {
    System.out.println("Konstruktor");
    System.out.println(s);
  }
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