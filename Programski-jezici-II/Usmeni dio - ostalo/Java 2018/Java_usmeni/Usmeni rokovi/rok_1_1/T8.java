public class T8{
  int a = 10;
  
  private static void metoda(int a){
    a =- 1;
    System.out.println((a++));
  }
  
  public static void main(String[] args){
    T8 t = new T8();
    T8.metoda(t!=null?3:1);
  }
}