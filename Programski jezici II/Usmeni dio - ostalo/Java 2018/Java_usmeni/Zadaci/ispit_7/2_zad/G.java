public class G{
  static int x = 3;
  
  public static void main(String[] args){
    new G();
  }
  
  G(){
    G(2);
  }
  
  G(int x){
    System.out.println(x);
  }
  
  public void G(int a){
    System.out.println(a);
  }
}