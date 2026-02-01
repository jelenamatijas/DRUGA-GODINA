public abstract class K1
{
  String  name;
  
  public K1()
  {
    //super();
    System.out.println("Konstruktor K1");
  }
  
  
  public abstract void metoda1();
  
  public static void main(String[] args)
  {
    K3 k3 = new K3();
    k3.metoda1();
    System.out.println("====================");
    K4 k4 = new K4("ss");
  }
  
}


abstract class K2 extends K1
{
  String name;
  
  public abstract void metoda2();
  
  
  public K2()
  {
    //super();
    System.out.println("Konstruktor K2");
  }
  
}

class K3 extends K2
{
  String name;
  
  K3()
  {
    //super();
    System.out.println("Konstruktor K3");
  }
  
  public void metoda1()
  {
    System.out.println("K3 metoda1()");
  }
  
  public void metoda2()
  {
    
  };
  
  
  
}

class K4 extends K3
{
  String name;
  
  
  
  public K4(String name)
  {
    //super();
    System.out.println("Konstruktor K4");
  }
  
  public static void main(String args[])
  {
    System.out.println("Main K3");
  }
  
}
  
  
  
  
  