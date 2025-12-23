// HEAP SIZE: 100 MB (ili proizvoljno)

public class A
{
  int state;
  double[][][] dbl1 = new double[10][100][1000];
  long [][][] lng1 = new long[5][20][10000];
  private B b;
  private C c;
  
  public static void main(String... args)
  {
    A a = new A();
    A a1 = new A();
    B b1 = new B(a1);
    B b2 = new B(a);
    B b3 = new B(new A());
    int count = 0;
    
    a = null;
    b1 = null;
    A a2 = new A();
    a2.b = b1;
    
    A[] arr1 = new A[50];
    B[] arr2 = new B[50];
    C[] arr3 = new C[50];
    b2 = null;
    
    System.gc(); // 1
    count++; 
    
    b1 = null;
    A a3 = new A();
    b1 = new B(b1);
    b2 = new B(b1);
    a2.b = null;
    
    System.gc();// 2
    count++; 
    
    C c1 = new C(a3,b1);
    C c2 = new C(a2,b2);
    A[] arr4 = new A[50];
    B[] arr5 = new B[50];
    C[] arr6 = new C[50];
    
    a1 = null;
    a2 = null;
    a3 = null;
    
    a2 = new C(c1,c2);
    
    System.gc();//3
    
    A a4 = new A();
    A a5 = new A();
    A a6 = new A();
    A a7 = new A();
    
    System.gc();//4
  }
}

class B extends A
{
  byte bitFlag = (byte) 0b11010011;
  double[] dbl2[] = new double[10][100];
  long [] lng2[] = new long[5][20];
  private A a;
  private C c;
  ;
  B(A a)
  {
    this.a = a;
  }
}

class C extends B
{
  double[] dbl3 = new double[1000000];
  long [] lng3 = new long[5];
  private A a;
  private B b;
  
  C(A a, B b)
  {
    super(a);
    this.b = b;
  }
}