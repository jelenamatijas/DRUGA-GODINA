class A1 implements I1
{
  {
    {
      {
    System.out.println("@@");
      }
    }
  }
  static 
  {
    {
    System.out.println("STATIC A");
    }
  }
  
  public A1()
  {
    System.out.println("A1");
  }
  
  class NA1
  {
    public  int na1 =0;
  }
  
  public void metoda1()
  {
    System.out.println("metoda1()");
    System.out.println(I1.i1.i);
    //n22.sim();
  }
  
  
  
  static class NA2 extends I1.N1
  {
    static
    {
      System.out.println("NA2 static");
    }
    
    public NA2()
    {
      System.out.println("NA2 konstr");
    }
    
  }
  
  public NA2 n22 = new NA2()
  {
    public void sim() { System.out.println("n22SIM"); }
  };
  
  
 
  
  public static void main(String[] args)
  {
    //A1 a1 = new A1();
    //A1.NA1 na1 = a1.new NA1();
    //I1.N1 ne = new I1.N1();
    //a.metoda1();
    //System.out.println("na1 " + na1.na1);
    A1.NA2 na2 = new A1.NA2();
    
    //System.out.println(I1.a);
//    I1.i1.metoda1();
//    a1.metoda1();
//    System.out.println(a instanceof I1);
    
  }
  
}


interface I1
{
  int i = 0;
  String s = "oo22";
  public void metoda1();
 
  public  A1 a = new A1()
  {
    
    
    public  int k;
   
    
  };
  
  public I1 i1 = new I1()
  {
    int i =0;
    public void metoda1()
    {
      String p = s;
      p = "111111";
      System.out.println("Metoda1() u i1" + p);
    }
    
  };
  
  
  class N1
  {
    public static int n;
    
    static{
      System.out.println("I1 N1");
      n=0;
    }
    
    public void metodaN1()
    {
      System.out.println("metodaN1: "+ n);
    }
    
  }
  
  public final static N1 n1 = new N1(){
    {
      System.out.println("##");
    }
    
  };
  
 
  
//  public static void main(String ... args)
//  {
//    
//    
//  }
  
}