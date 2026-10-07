public class G1{
  
  public String clan = "g1";
  
  private void metoda(){
    System.out.println("metoda g1");
  }
  public static void main(String [] args){
    G3 g3 = new G3<Integer>(8);
    System.out.println(g3.clan);
    System.out.println(((G1)g3).clan);
    G1 g[] = {(G1)new G3<String>("g3"),new G2<String>("g2"),(G2)g3};
    for(int i=0;i<g.length;i++){
      g[i].metoda();
      System.out.println(g[i].clan);
    }
  }
}

class G2<T> extends G1{
  
  public T clan;
  
  public G2(){
    System.out.println("G2()");
  }
  public G2(T t){
    clan=t;
    System.out.println("G2(t)");
  }
  public void metoda(){
    System.out.println("metoda g2");
  }
}

class G3<F> extends G2<F>{
  
  public F clan;
  
  public G3(){
    System.out.println("G3 konstruktor");
  }
  public G3(F f){
    clan = f;
  }
  public void metoda(){
    System.out.println("metoda g3");
  }
}