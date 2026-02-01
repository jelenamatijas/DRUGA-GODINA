class Boxing1 extends Boxing
{
  public String s;
  public static void main(String[] args) throws Exception
  {
    System.out.println("Boxing1 main");
   
    
      System.out.println("1");
      
      try
      {
        
         Boxing.main(null);
      }

      finally
      {
        System.out.println("FINALLY");
      }
      System.out.println("END");
    
    
    
  }
  
  public  void metoda2()
  {
    Boxing b = new Boxing("SO")
    {
      
      public void bmetoda()
      {
        System.out.println("BMETODA: " + s);
      }
    };
   
  }
  
  
  public static void metoda1() throws EC1,RuntimeException
  {
    System.out.println("metoda1");
    EC1 e = new EC1();
    throw e;
    //e.metoda();
    
    
  }
  
}

class EC1 extends Exception
{
  public EC1()
  {
    super();
    
  }
  
  public void metoda() throws RuntimeException
  {
    System.out.println("EXmetoda");
    throw new RuntimeException();
  }
  
}



public class Boxing
{
  public Boxing() {};
  public Boxing(String s){};
  public static void main(String[] args) throws Exception
  {
    Boolean a = new Boolean("TRue");
    Boolean b = new Boolean("true");
    
    System.out.println(a);
    System.out.println(b);
    System.out.println(a == b);
    System.out.println(a.equals(b));
    a=b;
    System.out.println(a==b);
    //while(a) System.out.println("####");
    throw new EC1();
    
  }
  
}


