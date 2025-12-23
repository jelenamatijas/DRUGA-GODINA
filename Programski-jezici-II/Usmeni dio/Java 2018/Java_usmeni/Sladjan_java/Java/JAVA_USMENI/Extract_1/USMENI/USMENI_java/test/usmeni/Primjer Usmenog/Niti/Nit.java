class Nit implements Runnable{
  String st="Yes";
  
  public void run()
  {
    this.st="No";
    System.out.println("tjhjkn");
  }
  
  public static void main(String[] args)
  {
    Nit t=new Nit();
    new Thread(t).start();
    for(int i=0;i<10;i++)
    {
      System.out.println(t.st);
    }
  }
}