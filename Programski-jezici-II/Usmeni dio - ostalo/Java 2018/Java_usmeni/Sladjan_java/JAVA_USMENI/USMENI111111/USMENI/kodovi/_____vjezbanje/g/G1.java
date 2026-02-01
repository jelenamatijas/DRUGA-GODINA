public class G1{
  int i;
 // G1(){System.out.println("G1");}
  
  {
    System.out.println(i=1);
     //System.out.println("d");
  }
  
  protected void test(){
    System.out.println(i);
  }
  protected void metoda() throws Exception{
    test();
  }
  public static void main(String[] args)throws Exception{
    G1 g1=new G1(),g2=new G3();
    g1.metoda();
    g2.metoda();
  }
}


abstract class G2 extends G1{
  abstract protected void metoda()throws Exception;
}

final class G3 extends G2{
  
  public void metoda() throws Exception{
    i += 10.51f;
    test();
  }
}