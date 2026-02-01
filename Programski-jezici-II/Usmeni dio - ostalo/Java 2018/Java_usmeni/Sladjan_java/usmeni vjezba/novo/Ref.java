public class Ref
{
 
  {
    System.out.println("NEstaticki REF");
  }
  public static void main(String[] args)
  {
    MyOuter m = new MyOuter();
    MyOuter.MyInner in = new MyOuter.MyInner();
    System.out.println("Nije jos postavio");
    System.out.println(MyOuter.MyInner.x);
    int a = 1;
    int b = 2;
    String s = "3";
    System.out.println(a  + b  + s);
    
  }
  
}


class MyOuter 
{
  {
     System.out.println("NEstaticki SPOLJASNJA");
  }
  static{
    System.out.println("staticki SPOLJASNJA");
  }
    public static class MyInner 
    {
      static int x;
      int y;
      {
        y = 1;
        System.out.println("NESTATICKI BLOK unutrasnje klase");
      }
      static 
      {
        System.out.println("staticki UNUTRASNJA");
      }
        public static void foo() { }
    }
}