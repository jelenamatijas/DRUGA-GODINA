public class D111 extends Thread implements Runnable{
  private int id;
  Thread t = new Thread();
  
  D111(int id){
    this.id = id;
    System.out.println("D"+id);
    t.run();
  }
  
  public void metoda(){
    start();
  }
  
  public void run(){
    for(int i=0; i<10; i++){
      id+=1;
      if(id%10==0)
        System.out.println(i);
    }
    System.out.println("run" +id);
  }
  
  public static void main(String args[]){
    D111 d1 = new D111(10);
    D111 d2 = new D111(20);
    
    Thread t = new Thread(d2);
    t.start();
    System.out.println("main 1");
    d1.metoda();
    System.out.println("main 2");
  }
}
    