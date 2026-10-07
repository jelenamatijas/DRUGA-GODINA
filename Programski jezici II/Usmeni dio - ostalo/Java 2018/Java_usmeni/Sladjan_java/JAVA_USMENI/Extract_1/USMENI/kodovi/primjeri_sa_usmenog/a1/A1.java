package a1;
import a1.a3.A2;

class A1 extends A2{
  
  public static void main(String args[]){
    A1 a1 = new A1();
    A2 a2 = new A2();
    a1.metoda();
    
    int a=-1;
    int b=0;
    
    System.out.println(a+b++);
  }
  
  public void metoda(){
    super.metoda();
  }
}

