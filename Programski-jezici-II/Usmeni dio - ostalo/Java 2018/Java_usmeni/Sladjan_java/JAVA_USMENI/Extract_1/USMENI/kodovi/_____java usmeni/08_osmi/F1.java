public class F1{
  public static void main(String[] args){
    System.out.println("start");
    F2 F2=new F2(1);
    F2 F3=new F3(2);
    Thread t=new Thread(F3);
    F2.start();
    t.start();
    
    System.out.println("end");
  }
}

class F2 extends Thread{
  int id;
  
  F2(int id){
    System.out.println("F2()");
    this.id=id;
  }
}

class F3 extends F2 implements Runnable{
  F3(int id){
    super(id);
    System.out.println("F3()");
  }
  
  public void run(){
    for(int i=1;i<6;i++){
      //if (i == 60) try {sleep(1000);} catch(Exception a){}
      System.out.println(id+": "+i);
    }
  }
}