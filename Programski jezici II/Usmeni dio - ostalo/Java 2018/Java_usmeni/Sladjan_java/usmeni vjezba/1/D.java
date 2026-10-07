public class D implements I3{
 public void metoda3() { 
  System.out.println(3);
 }
 public void metoda() {
  System.out.println(1);
 }
 public void metoda2() {
  System.out.println(2);
 }
 public static void main(String args[]){
  D d = new D();
  E e = new E();
  d.metoda();
  e.metoda();
 }
}

class E implements I{
 public void metoda() {
  System.out.println(11);
 }
}

interface I{
 void metoda();
}

abstract interface I2{
 abstract void metoda2();
}

interface I3 extends I, I2{
 void metoda3();
}
