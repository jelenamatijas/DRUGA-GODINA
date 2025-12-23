public class F11{
  public static void main(String args[]){
    FT1<F2> ft1 = new FT1<F2>();
    ft1.metoda(new F2("Java"));
    System.out.println(ft1.t.s);
    
  }
}

class FT1<T> implements FTI<G>{
  T t;
  public void metoda(T t){
    this.t = t;
  }
  
  public T metoda(){
    return t;
  }
}

class F2{
  String s; 
  F2(String s){
    this.s = s;
  }
}

