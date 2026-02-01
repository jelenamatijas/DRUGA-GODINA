public class Roditelj
{
  int x;
  
  public static void main(String[] args)
  {
    Roditelj[] niz = {new Nasljednik1(), new Nasljednik2()};
    ((Nasljednik2)niz[1]).metoda2();
    
    
  }
  
//  public void metoda1()
//  {
//    System.out.println("metoda1 RODITELJ");
//  }
//  
//  public void metoda2()
//  {
//    System.out.println("metoda2 RODITELJ");
//  }
  
}

class Nasljednik1 extends Roditelj
{
  public void metoda1()
  {
    System.out.println("metoda1");
  }
  
}

class Nasljednik2 extends Roditelj
{
  public void metoda2()
  {
    System.out.println("metoda2");
    new Nasljednik1(){
      public void metoda4()
      {
        System.out.println("metoda4");
      }
      }.metoda4();
      
  
  }
  
}