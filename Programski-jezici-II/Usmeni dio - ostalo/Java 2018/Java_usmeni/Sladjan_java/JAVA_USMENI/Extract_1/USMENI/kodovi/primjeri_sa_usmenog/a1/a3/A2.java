package a1.a3;

public class A2 extends A3{
  public A2(){
    System.out.println("A2()");
  }
  
  public void metoda(){
    //this.metoda();
    super.metoda();
    System.out.println(a++);
  }
}

class A3 {
  double a;
  int b;
  float c;
  
  public A3(){
    System.out.println("A3()");
    a=c=b=1;
  }
  
  public void metoda(){
    System.out.println(a+b++);
  }
}