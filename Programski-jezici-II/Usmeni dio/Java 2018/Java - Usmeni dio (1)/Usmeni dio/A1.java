public class A1 extends A{
  public int x[]=new int[2500000];
  public double y[]=new double[2500000];
  public A1 a;
  public int id;
  
  public static int counter;
  public A1(A1 a){
  id=counter++;  
  this.a=a;
  }
  
  public static void main(String[] args){
    A1 a=new A1(null);
    A1 a2=new A1(a);
    A1 a3=new A1(a2);
    A1 a4=new A1(a3);
    
    A1 niz[][][]=new A1[2][3][2];
    
    for (int i=0;i<2;++i)
    for (int j=0;j<3;++j)
      if (i!=j)
      for (int k=0;k<2;++k){
      A1 temp = A1.counter%2==0 ? new A1(a2) : new A1(a3);
      if (temp.id%2==0)
        niz[i][j][k]=temp;
    }
    System.gc();  
  }
  
}

class A{
  public int x[]=new int[2500000];
  public double y[]=new double[2500000];
}
    
  
  