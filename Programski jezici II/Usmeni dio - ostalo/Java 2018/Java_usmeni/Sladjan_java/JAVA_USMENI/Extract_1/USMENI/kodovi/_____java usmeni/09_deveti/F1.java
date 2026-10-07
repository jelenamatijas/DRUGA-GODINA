public class F1{
  public static void main(String[] args)throws Exception{
    System.out.println("start");
    F2 x1=new F3(1);
    F2 x2=new F3(2);
    Thread t2=new Thread(x1);
    Thread t3=new Thread(x2);
    F2 x3=new F2();
    x3.start();
    x3.join();
    t2.start();
    t3.start();
    System.out.println("end");
  }
}

class F2 extends Thread{
  int id;
  
  public F2(){
    this(3);
  }
  
  F2(int id){
    System.out.println("F2()");
    this.id=id;
  }
  
  public void run(){
    for(int i=1;i<6;i++){
      System.out.println(id+": "+i);
    }
  }
}

class F3 extends F2 implements Runnable{
  F3(int id){
    super(id);
    System.out.println("F3()");
  }
}
