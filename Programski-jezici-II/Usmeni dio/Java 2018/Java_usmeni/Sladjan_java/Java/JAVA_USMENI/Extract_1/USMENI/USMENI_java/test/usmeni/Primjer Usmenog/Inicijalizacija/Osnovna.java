class I extends Osnovna
{
  public int a,b;
  private int zbir;
  
 // D x;
  
   D y=new D();   
  static char c;
  static int broj;

  
  
  public I()
  {               
    System.out.println("Pocetak konstruktora");
    y=new D(10);
    
    zbir=a+b;
   int i;
  }
  
  public static void main(String[] args)
  {
   I i=new I();
  }
}

class D{
  
  D()
  {
    System.out.println("Objekat kreiran podrazumjevanim konstruktorom...");
  }
  
  D(int a)
  {
    
     System.out.println("Objekat kreiran konstruktorom sa jednim parametrom...");
     
  }
}
class Osnovna{

  Osnovna()
  {
    System.out.println("Osnovna");
  }

}