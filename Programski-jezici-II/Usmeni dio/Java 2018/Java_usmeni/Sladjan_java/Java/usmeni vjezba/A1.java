public class A1{
  {
    System.out.println("nestaticki u A1");
  }
  static {
    System.out.println("staticki u A1");
    new A1(null);
  }
  public A1(){
    System.out.println("A1()");
  }
  public A1(Object o){
    System.out.println("A1(o)");
    new A1();
  }
  public static void main(String [] args){
    A1 a = new A1();
    A1 a1 = new A1(null);
    A3 a3 = new A3(new A2(null));
  }
}

class A2 extends A1{
  static{
    System.out.println("staticki u A2");
  }
  public A2(Object o){
    System.out.println("A2(o)");
  }
}

class A3 extends A2{
  static{
      System.out.println("staticki u A3");
    }
  public A3(){
    this(null);
    System.out.println("A3()");
  }
  public A3(Object o){
    super(null);
    System.out.println("A3(o)");
  }
}