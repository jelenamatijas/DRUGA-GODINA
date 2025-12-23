public class Stringici
{
    public static void main(String [] args)
    {
      String s = new String("xyz");
      String s1 = "xyz";
      System.out.println( /*s == */s.equals(s1));//s.replace('t', 't'));
      System.out.println( /*s == */s.intern() == s1.intern());
      System.out.println(s.intern() == s1);
      s.intern();
    }
}