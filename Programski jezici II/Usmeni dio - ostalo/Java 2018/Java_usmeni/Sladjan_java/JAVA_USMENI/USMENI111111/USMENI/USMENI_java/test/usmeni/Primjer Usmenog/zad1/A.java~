class A extends B
{
  public static void main(String[] args)
  {
    A a=new A();
    B b=new B();
    a.metoda();
  }
  public void metoda()
  {
    this.metoda();
    //super.metoda();
  }
}

class B extends C
{
  public B()
  {
    System.out.println("B()");
  }
  public void metoda()
  {
  //  this.metoda();              
    super.metoda();
    System.out.println(a++);
  }
}

class C{
  double a;
  int b;
  float c;
  
  public C()
  {
    System.out.println("C()");
    a=c=b=1;            
  }
  
  public void metoda ()
  {
    System.out.println(a+b+c);
  }
}