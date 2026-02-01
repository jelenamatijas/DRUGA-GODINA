public class F{
 private int i=0;
 private long y=0;
 
 long metoda(){
  return i+y;
 }
 F napravi(){
  return new F();
 }
 public static void main(String args[]){
  F f=new F();
  G g=new G();
  F f1=f.napravi();
  F f2=g.napravi(); //preko g pozivamo metodu,pa podtip f pokazuje na supertip G
  System.out.println(f1.metoda());
  System.out.println(f2.metoda());  //aktivira se polimorfizam, poziva se metoda() iz G
 }
}