public abstract class B1{
B1()
{
  super();
  System.out.println("B1()");
}
public static void main(String[] args)
{
  B3 b3=new B3();
  b3.metoda();
  B2 b2=b3;
  b2.metoda();
 // B2 b22=new B2(); //i abstract iskljuciti
//  B1 b1=b22;   //izlaz:B1 B2 B3 B3met B3met B1 B2 B2 met
  B1 b1=b2;
  b1.metoda();
  
}
void metoda(){
  System.out.println("B1 metoda...");
}
}

abstract class B2 extends B1{
  B2()
  {
    super();
    System.out.println("B2()");
  }
  public void metoda(){
  System.out.println("B2 metoda...");
  }
  void metoda2()
  {
    System.out.println("B2 metoda...");
  }
  
}
class B3 extends B2{
B3()
{
  super();
  System.out.println("B3()");
}
public void metoda()
{
  System.out.println("B3 metoda...");
}
}