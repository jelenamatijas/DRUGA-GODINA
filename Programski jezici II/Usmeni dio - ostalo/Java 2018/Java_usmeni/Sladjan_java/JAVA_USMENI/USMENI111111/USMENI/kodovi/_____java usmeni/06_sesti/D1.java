public class D1 extends Thread implements Runnable{
  private int id;
  Thread t=new Thread();
  
  D1(int id){
    this.id=id;
    System.out.println("D"+id);
    t.run();
  }
  
  public void metoda(){
    start();
  }
  
  public void run(){
    for(int i=0;i<10;i++){
      id+=1;
      if(i % 10==0)
        System.out.println(i);
    }
    System.out.println("run "+id);
  }
  
  public static void main(String[] args){
    D1 d1=new D1(10);
    D1 d2=new D1(20);
    Thread t=new Thread(d2);
    t.start();
    System.out.println("main 1");
    d1.metoda();
    System.out.println("main 2");
  }
}