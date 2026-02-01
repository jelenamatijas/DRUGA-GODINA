class C
{
  public static void main(int[] e)  
  {
  }
  public void poziv(int a,int b)
  {}
  
  public static void main(String[] args)
  {
    
    C c=new C();
    c.poziv(2,1);   
    try{
       c.metoda1();
    }
      catch(ArithmeticException e)
    {
      System.out.println("Obradjena gresa u  racunu main");
    }
    finally
    {
      System.out.println("Finally main");
    }
   
     System.out.println("main...");
  }
  
  
  void metoda1()
  {
    System.out.println("metoda 1...");
    try{
    metoda2();
    }
    catch(ArithmeticException e)
    {
      System.out.println("Obradjena gresa u racunu metoda 2");
    }
    finally
    {
      System.out.println("Finally 1");
    }
    
   
  }
   
  void metoda2()
  { 
    System.out.println("Metoda 2");
    try{         
    metoda3();      
    }
    catch(Exception e)
    {
      System.out.println("Obradjen u metodi2");
    }
    finally
    {
      System.out.println("Finally 2");
    }
    System.out.println("Nastavak metode2 nakon obrade izuzetka MyException");
    int i=6/0;     
    System.out.println("Kraj metode2");  
  }                                      
  void metoda3() throws MyException
  {
    throw new MyException();
  }
}