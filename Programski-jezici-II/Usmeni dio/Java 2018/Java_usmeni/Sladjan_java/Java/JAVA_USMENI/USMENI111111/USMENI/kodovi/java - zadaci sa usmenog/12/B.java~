public class B {
 int x=0, y=0;
 
// B() { x=0; y=0; }  // fali ovakav konstruktor, jer se C() ne moze instancirati
 
 B(int a, int b){
  x=a;
  y=b;
 }
 protected int zbir(){
  return x+y;
 }
 protected int razlika(){
  return x-y;
 }
 public static void main(String args[]){
  B b=new B(1,2);
  C c=new C();
  System.out.println(b.razlika());
  System.out.println(c.razlika());
 }
}