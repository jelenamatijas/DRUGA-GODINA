public class ThreadE extends Thread{
  private String name;
  public ThreadE(String name){
    this.name = name;
  }
  
  public void run(){
    new Runnable(){
      public void run(){
        for(int i=0; i<10; i++){
          System.out.println(name + "1: " + i);
        }
      }
    }.start();
    
    for(int i=0; i<10; i++){
      System.out.println(name + "2: " + i);
    }
  }
  
  public static void main(String[] args){
    ThreadE a = new ThreadE("A");
    ThreadE b = new ThreadE("B");
    a.start();
    b.start();
  }
}