class X extends B {

 public static void main(String ka[]){
  X x=new X();
  A a=new A();
  a=(A)x;
  System.out.println(a.metoda());   //metoda metoda() je private u klasi A, dakle nedostupna u X
 }
}