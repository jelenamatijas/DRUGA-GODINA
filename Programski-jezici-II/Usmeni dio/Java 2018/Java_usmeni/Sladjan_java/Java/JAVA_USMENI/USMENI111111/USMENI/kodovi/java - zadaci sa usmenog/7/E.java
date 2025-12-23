public class E {
 public static void main(String args[]) {
  F f1=new F("Jedan");
  f1.run();  //poziv metode run, nema konkurentnog jer nema start()
  //f1.start();
  F f2=new F("Dva");
  f2.run();   //poziv metode run, nema konkurentnog jer nema start()
  //f2.start();
 }
}