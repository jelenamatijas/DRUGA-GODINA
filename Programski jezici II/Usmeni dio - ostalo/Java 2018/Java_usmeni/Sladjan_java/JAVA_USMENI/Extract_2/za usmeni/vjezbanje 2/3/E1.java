public class E1 extends Thread{
  private String name;
  public E1(String name){
    this.name=name;
  }
  public void run(){ 
    Runnable r=new Runnable(){ 
      public void run(){
        for(int i=0;i<5;i++){
          System.out.println(i);
        }
      }
    };
    new Thread(r).start();   
    
    synchronized(this){  
      for(int i=0;i<100;i++){
        System.out.println(i+" "+this.name);
        //System.out.println(i);
      }
    }
  }
  public static void main(String args[]){
    E1 a=new E1("A");
    E1 b=new E1("B");
    a.start();
    b.start();
  }
  
}