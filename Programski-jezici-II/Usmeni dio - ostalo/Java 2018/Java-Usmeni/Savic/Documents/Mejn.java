interface Vozibilno
{
  void vozi();
}

public class Mejn
{
  Printaj o;
  public static void main(String [] args)
  {
 Mejn mejn = new Mejn();
 mejn.f();
  }
  public void f()
  {
       o = new Printaj(5){
 public void f()
 {
   System.out.println("rambo vozi kamijon" + y);
   Mejn.this.o = new Printaj(2);
   System.out.println(y);
 }
 };
 o.f();
 System.out.println(o.y);
  }
}

class Printaj
{
  int y;
  static{
    System.out.println("SIPU RACKU");
  }
  public Printaj(int y)
  {
    this.y = y;
    System.out.println("MAJKA SRBIJA");
  }
  public void f()
  {
  
  }
}