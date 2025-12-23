interface I{}

public class B implements I{
  static String s ;
  public static void main(String args[]){
    B b = new B();
    I i = new B();
    if(b instanceof I){
      System.out.println("Ja sam interfejs");
    }else{
       System.out.println("Ja nisam interfejs");
    }
   
    if(s instanceof String){
      System.out.println("Ja sam String");
    }else{
       System.out.println("Ja nisam String");
    }
    
    if(s instanceof Object){
      System.out.println("Ja sam Objekt");
    }else{
       System.out.println("Ja nisam Objekt");
    }
  }
}