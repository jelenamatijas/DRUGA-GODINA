public class InstanceofTest{
  public static void main(String[] args){
    InstanceofTest [] niz = {new InstanceofTest(), new InstanceofTest()};
    if(niz[0] instanceof InstanceofTest){
      System.out.println("Ja sam InstanceOfTest");
    }
    else{
      System.out.println("Ja nisam InstanceOfTest");
    }
  }
}