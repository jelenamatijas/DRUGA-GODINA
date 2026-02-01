class O
{
  static void metodaStat()
  {
    System.out.println("Staticka");
  }
  O()
  {
    System.out.println("Podrazumjevani iz O");
  }
  private void metoda()   
  {
    System.out.println("Metoda O");
  }
 void metoda1()
 {
   System.out.println("Metoda1 O");
 }
 public void metoda2()
 {
   System.out.println("Metoda2 0");
 }
 
 public static void main(String[] args)
 {
   
   O o=new L();
  
   o.metoda();
   o.metoda1();
   K k=new K()
   {
     O o=new O();
     void metoda3()
     {
       System.out.println("Anonima klasa:metoda 3");
     }
     void metoda4()
     {
       System.out.println("Anonimna klasa:metoda 3");
     }
     void metoda6()
     {
       System.out.println("Anoniman klasa:metoda 5");
     }
     
   };
   O z=new O();
   L l=new L();
   O u=(O)l;
   l=(L)z;    
   k.metoda3();
   k.metoda4();
   k.metoda5();
  // k.metoda6();
   u.metoda(); 
   O.metodaStat();
 }
 
}

class L extends O
{
  public void metoda()
  {
    System.out.println("Metoda L");
  }
  void metoda1() 
  {
    System.out.println("Metoda1 L");
  }
 public void metoda2()     
  {
    System.out.println("Metoda2 L");
  }
 
  
}

abstract class K
{
  abstract void metoda3();
  abstract void metoda4();
  public void metoda5()
  {
    System.out.println("Metoda klase K");
  }
}