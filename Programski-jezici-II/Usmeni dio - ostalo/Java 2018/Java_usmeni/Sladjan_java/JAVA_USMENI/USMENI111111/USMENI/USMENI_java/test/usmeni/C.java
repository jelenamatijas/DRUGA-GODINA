class C
{
  private static C objekat;
  private static Object b="Aco";
  
  public C()
  {
    System.out.println("Konstruktor");
   System.out.println( new KlasaUmetodi().saberi());
  }
  
    class KlasaUmetodi
    {
      private int x;
      public int y;
      
      KlasaUmetodi()
      {
        x=1;
        y=2;
      }
      
     int saberi()
     {
       return x+y;
     }
    }
  
  void metoda()
  {
    
    new KlasaUmetodi(); 
  }
  
  public static void main(String[] args)
  {
 
  //  int broj3=KlasaUmetodi.saberi();
    
    System.out.println("main");
    C a=new C();
    System.out.println(b);
  }
   static{
    System.out.println("drugi");
  }
  static{
    System.out.println("staticki blog");
  }
  
 
}