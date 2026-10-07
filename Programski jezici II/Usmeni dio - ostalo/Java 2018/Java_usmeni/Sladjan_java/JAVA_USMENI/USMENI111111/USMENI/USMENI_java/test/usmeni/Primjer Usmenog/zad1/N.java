class N extends Thread
{
  public static void main(String[] args) 
  {
    System.out.println("Pocetak main-a");
    NitA a=new NitA();
    try{
    a.join();
    }catch(Exception e){
      e.printStackTrace();
    }try
    {
     
      a.start();
      a.join();
      Thread h=new Thread(new NitC());
      h.start();
      N.sleep(10000);
      sleep(5000);  
      Thread.sleep(1000);
    }
    catch(Exception e)
    {}
    finally
    {
      System.out.println("finally");
    }
    
    
    
    System.out.println("kraj main-a");
  }
}


class NitA extends Thread
{
 
  public void run()
  {
   // NitB b=new NitB(); 
     NitC c=new NitC();  
     Thread t= new Thread(c);
     for(int i=0;i<20;i++)
    {
      System.out.println("NitA: "+i);
      yield();
    }
     //c.start();
     t.start();
    try
    {
      t.join();
    }
    catch(Exception e)
    {}
    
    for(int i=0;i<3;i++)
    {
      System.out.println("NitA: "+i);
    }
  }
  
}

abstract class NitB implements Runnable 
{}

class NitC extends NitB
{
  public void run()
  {
  for(int i=0;i<20;i++)
    System.out.println("NitC: "+i);
  }
}
class NitD extends Thread implements Runnable
{
  public void run()
  {}
}

abstract class T 
{
  public abstract void metoda(); 
}
