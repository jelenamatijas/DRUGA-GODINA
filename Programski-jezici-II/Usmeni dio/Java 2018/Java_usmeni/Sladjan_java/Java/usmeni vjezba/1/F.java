public class F {
 private int i = 0;
 private long y = 0;
 
 long metoda(){
  return i + y;
 }
 F napravi(){
  return new F();
 }
 public static void main(String args[]){
  F f = new F();
  G g = new G();
  F f1 = f.napravi();
  F f2 = g.napravi();
  System.out.println(f1.metoda());
  System.out.println(f2.metoda());
 }
}

class G extends F{
 int i = 1;
 long z = 1;
 
 protected long metoda(){
  z = super.metoda();
  return i + z;
 }
 G napravi(){
  return new G();
 }
}
