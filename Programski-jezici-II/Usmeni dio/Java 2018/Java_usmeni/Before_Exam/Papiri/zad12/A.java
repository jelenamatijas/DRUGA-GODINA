public class A{
  static{
    int a = 5;
  }
  static int a,b;
  
  public static void main(String[] args){
    a--;
    metoda();
    System.out.println(a + b + ++a);
    System.out.println(++A.a);
    System.out.println(a);
  }  
  
  public static void metoda(){
    b = a++ + ++a;
  }  
}