public class Novo
{
  public static void main(String [] args)
  {
      ABC abc = new CBA();
      System.out.println(((ABC)abc).f());
      System.out.println(abc.f());
  }
}

class CBA extends ABC
{
  int f()
  {
    return 1;
  }
}
class ABC
{
  int f()
  {
    return -1;
  }
}