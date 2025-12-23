class A
{
  public static void main(String[] args) throws ClassNotFoundException , InstantiationException  
  {
    A a=new A();
    B b=new B();

    a.metoda();
    
    try{
    b.metoda2();                    
    System.out.println("Try kod...");
    }
    catch(Exception e)
    {
     System.out.println("Izuzetak Obradjen...");
    }
    finally
    {
      System.out.println("Finally main");
    }
    
    System.out.println("main...");
    
  }
  public void metoda() throws ClassNotFoundException , InstantiationException                                        
  {                                          
    System.out.println("metoda A"); 
  //  throw new ClassNotFoundException();     
  }                                         
}                                           
class B extends A    
{
  public void metoda2()   throws Exception   
  {                                         
    System.out.println("Metoda B");         
   // int b=5/0;
    try{                                
      int a=15/7;
    }
    catch(Exception e)
    {
    }
    System.out.println("TIca");
  }
  
  
  
}
