class MyOuter 
{
  static{
    System.out.println("staticki SPOLJASNJA");
  }
    public static class MyInner 
    {
      static int x;
      static 
      {
        System.out.println("staticki UNUTRASNJA");
      }
        public static void foo() { }
    }
    public static void main(String[] argss)
    {
      MyOuter mo = new MyOuter();
      MyOuter.MyInner in = new MyOuter.MyInner();
    }
}