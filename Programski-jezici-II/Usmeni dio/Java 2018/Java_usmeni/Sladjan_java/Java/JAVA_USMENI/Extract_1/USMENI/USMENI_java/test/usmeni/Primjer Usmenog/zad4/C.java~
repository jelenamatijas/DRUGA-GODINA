class C extends Thread
{
  public void run(){
  
  for(int i=0;i<10;i++)
  {
    System.out.println(getName());
    try{
    if(i==5)
      sleep(1000);
    }
    catch(Exception e)
    {}
  }
  }
  
  public static void main(String[] args)
  {
    C c=new C();
    Thread t=new Thread(c);
    t.run();                       
    Thread t1=new Thread(new C());
    Thread t2=new Thread(new C());
    t1.start();                    
    t2.start();                    
  }
}