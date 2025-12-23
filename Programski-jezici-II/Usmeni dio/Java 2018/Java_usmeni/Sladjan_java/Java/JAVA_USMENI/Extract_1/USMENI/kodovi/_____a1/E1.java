public class E1{
  static public void main(String args[])throws Exception{
    System.out.println("main 1");
    E3 e3= new E3();
    E2 e2= new E2(e3);
    e2.start();
    for(int i=0;i<10;i++){
     if(i==5)                //i ovako i bez sleep ove 3 niti naizmjenicno rade, ali
       Thread.sleep(3000);   // E3 run... i E2 run... se moraju jedna za drugom ispisati, nebirno kaja prva
     System.out.println(i);
    }
  }
}

class E2 extends Thread{
  E3 e3;
  public E2(E3 e3){
    this.e3=e3;
    System.out.println("E2");
  }
  public void run(){
    try{
    for(int i=1;i<20;i++){
      if(i==4)
      Thread.sleep(5000);
      System.out.println("E2 run....");
    }
    }catch(Exception e){
      
    }
  }
  
  public synchronized void start(){
    super.start();
    new Thread(e3).start();
  }
}

class E3 implements Runnable{
  public E3(){
    System.out.println("E3");
  }
  public void run(){
    try{
    for(int i=1;i<10;i++){
      if(i==1)
      Thread.sleep(2000);
      System.out.println("E3 run....");
    }
    }catch(Exception e){
      
    }
  }
}