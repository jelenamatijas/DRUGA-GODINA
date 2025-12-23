public class Test{
  static int a = 3;
  public static void main(String [] args){
    new Test().go(a);
    System.out.println(a);
  }
  
  void go(int b){
    b = 1;
  }
}

