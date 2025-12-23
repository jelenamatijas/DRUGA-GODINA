class K
{
  int a;
  int b;
  K()
  {
  
    this(1);  
    System.out.println("Podrazumjenvani K");
  }
  
  K(int a)
  {
    this(a,a);
    System.out.println("Sa jednim parametrom K");
  }
  K(int a,int b)
  {
    super();
    this.a=a;
    this.b=b;
    System.out.println("Sa dva parametra K");
  }
  
  
  
  public static void main(String a[])
  {
    K K=new K();
      
     D D=new D(3,3,3);
    System.out.println(D.a);
    System.out.println("--------------");
    F f=new F();
  }
}

class D 
{
  int a;
  int b;
  int c;
  D(int a,int b,int c)
  {
    this(a);
    System.out.println("Sa tri parametra D");
    this.a=100;
  }
  
  D(int a)
  {
    this.a=a;
    System.out.println("Sa jednim parametrom D: a="+a);
  }
}

class F extends D
{
  K k=new K();
  F()
  {
    super(1);
    System.out.println("F");
  }
}
  
