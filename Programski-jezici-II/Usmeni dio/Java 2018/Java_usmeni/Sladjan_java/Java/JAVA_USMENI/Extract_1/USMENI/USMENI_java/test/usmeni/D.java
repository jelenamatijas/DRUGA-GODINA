class D
{
  A a=new A();
  static int count;
  
  D()
  {
    count++;
    System.out.println(count);
  }
  
  static {
    count=10;
    System.out.println("javno mjenje");
  }
  
  public static void main(String[] args)
  {
    D t[]=new D[5];
    for(int i=0;i<5;i++)
    {
      t[i]=new D();
    }   
  }
}