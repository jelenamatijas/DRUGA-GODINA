public class B1 implements BI1, BI2{
  public B1(){
    super();
  }
  
  public static void main(String[] args){
    BI1 b = new B1();
  }
  
  public final void metoda(){
    System.out.println("1");
  }
  
  public int metoda(){
    System.out.println("1");
    return 1;
  }
  
}

interface BI1{
  public abstract void metoda();
}

interface BI2{
  int metoda();
}