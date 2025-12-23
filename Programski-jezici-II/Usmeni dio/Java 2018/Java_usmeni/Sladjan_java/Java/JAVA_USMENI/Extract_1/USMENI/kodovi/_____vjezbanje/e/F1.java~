public class F1{
  
public static void main(String args[]){
  
FT1<F2> ft1=new FT1<F2>();
ft1.metoda(new F2("Java"));
System.out.println(ft1.t.s);

D2 aa = new D2();
//FT1.D2 aa = ft1.new D2();
}
}


class FT1<T> implements FTI<T>{  
T t;                              
public void metoda(T t){           
 this.t=t;
 }

class D2{ D2(){System.out.println("d2");}}
   
 public T metoda(){
  return t;
  }
}


class F2{
 String s;
 F2(String s){
  this.s=s;
  }
 }

 interface FTI<G>{
  public void metoda(G t);
  public G metoda();
  }