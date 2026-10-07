class Main
{
  public static void metoda()
  {
    System.out.println("Staticka metoda");
  }
  public static void main(String[] args)
  {
    Main.metoda();  
    F1 f=new F1();
    Main.metoda();  
    
   
    
    F a=new F();
    Thread x=new Thread(a);
    a.start();
    f.start();
    f.getThread(a);
  }
}

class F extends Thread
{
  Thread a;
  public void run() 
  {
    for(int i=0;i<10;i++)
    {
      System.out.println("Thread run");
      if(i==5)
      {try{
       a.join();
      }catch(Exception e){
        e.printStackTrace();
      }
    }
  }
  }
   void getThread(Thread a)
  {
    this.a=a;
  }
 
}

class F1 extends F implements Runnable
{
  public static void metoda()
  {
    System.out.println("Staticka metoda F1");
  }
  public void run()
  {
    for(int i=0;i<3;i++)
    {
      System.out.println("Runnable run");
    }
  }
  
  public void metoda1()
  {
    System.out.println("Metoda klase F1");
  }
}