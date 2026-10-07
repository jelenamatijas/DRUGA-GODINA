public class C extends B
{
  C()
  {
    System.out.println("Kontruktor C()");
  }
  public void metoda()
  {
    System.out.println("Metoda iz C");
  }
  
  public static void main(String[] args)
  {
    A a = new A();
    B b = new B();
    C c = new C();
    
    A a1 =  b;
    A a2 = (A) c;
    
    try
    {
      throw new Error();
    }
    catch(Error e)
    {
      System.out.println("Izuzetak obradjen");
    }
    
  //  C c1 = (C) b;
    B b1 = (B) c;
    
    a1.metoda();
    a2.metoda();
    b1.metoda();
  }
}
class A
{
  A()
  {
    System.out.println("Kontruktor A()");
  }
  public void metoda()
  {
    System.out.println("Metoda iz A");
  }
}

class B extends A
{
  B()
  {
    System.out.println("Kontruktor B()");
  }
  public void metodaA()
  {
    System.out.println("Metoda iz B");
  }
}