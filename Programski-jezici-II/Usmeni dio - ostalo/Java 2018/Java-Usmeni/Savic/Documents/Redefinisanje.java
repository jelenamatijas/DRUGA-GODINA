import java.io.*;


public class Redefinisanje extends Osnovna
{
   public int string = 5;
   int str = 4;
   int y = 7;
   static int f()
   {
      return 4;
   }
   static int x(A a, B b, C c) throws ArrayIndexOutOfBoundsException 
   {
      return 0x7fffffff;
   }
   static Object k()
   {
      return new Object();
   }
   public static void main(String [] args) throws Exception
   {
      Redefinisanje a = new Redefinisanje();
      Osnovna o = new Osnovna();
      Osnovna p = a;
      System.out.println(a.v());
      System.out.println(p.f() + " " + a.f());
      System.out.println(p.y + " " + a.y);
      System.out.println(p.k(3) + " " + a.k());
      System.out.println(a.x(new A(), new B(), new C()));
      System.out.println(p.x(new A(), new B(), new C()));
      byte b [] = new byte[40];
      try{
 b[41] = 5;
      }catch(ArrayIndexOutOfBoundsException ex)
      {
   System.out.println("UHVACEN");
      }
   }
}


class Osnovna
{
  public String string = "AAA";
  int y = 6;
  static String str = "BBB";
  static int f()
  {
    return 5;
  }
  static int v()
  {
    return 5;
  }
  static int x(A a, B b, C c) throws IOException, InterruptedException
  {
    return 0xff;
  }
  static Object k(int d)
  {
      return new String(new byte []{48, 49, 50, 51});
  }
}

class A
{

}


class B
{

}

class C
{

}