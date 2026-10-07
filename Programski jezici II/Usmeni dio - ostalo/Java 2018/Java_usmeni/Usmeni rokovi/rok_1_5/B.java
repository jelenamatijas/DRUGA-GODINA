interface I{}

public class B implements I{
  static String s;
  public static void main(String args[]){
    B b = new B();
    
    if(b instanceof I){
      System.out.println("Ja sam interface");
    }
    else{
      System.out.println("Ja nisam interface");
    }
    if(s instanceof String){
      System.out.println("Ja sam String");
    }
    else{
      System.out.println("Ja nisam String");
    }
    if(s instanceof Object){
      System.out.println("Ja sam Object");
    }
    else{
      System.out.println("Ja nisam Object");
    }
  }
}