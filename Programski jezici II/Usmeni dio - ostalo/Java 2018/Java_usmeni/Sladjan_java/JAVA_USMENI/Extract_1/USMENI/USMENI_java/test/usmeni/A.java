class A
{
  {
     a=8;
    b=7;
    stat=25;
  }
  int a;
  float b;
  static int stat;
  
  static {
     stat=3;
  }
  
  {
   int a=5;
    int b=9;
  }
  {
    a=11;
    b=22;
    
  }
  
  
  static int prom;
  
  A()
  {
    System.out.println(a+" "+b);
    a=1;
    b=0;
    System.out.println(a+" "+b);
  }
  
  public static void main(String [] args)
  {
    A a=new A();
    System.out.println(stat);
    System.out.println(prom);
  }
}

class B
{
}