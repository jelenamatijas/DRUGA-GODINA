public class Test8 {

 public static void main(String[] args) {
  Prvi b = new PrvaKlasa();
  Treci c = new DrugaKlasa();
  
  b.metoda1();
  c.metoda1();
  c.metoda2();
  //c.metoda3(); //ovo ne radi jer referenca c ne zna sta je metoda3()
  ((PrvaKlasa)c).metoda3();
 }

}

interface Prvi {
 void metoda1();
}

interface Drugi {
 void metoda2();
}

interface Treci extends Drugi {
 void metoda1();
}

class PrvaKlasa implements Prvi {
 public void metoda1() {
  System.out.println("Prva Klasa: metoda1()");
 }
 
 public void metoda3()
 {
   System.out.println("metodica");
 }
}

class DrugaKlasa extends PrvaKlasa implements Treci {
 public void metoda1() {
  System.out.println("Druga Klasa: metoda1()");
 }

 public void metoda2() {
  System.out.println("Druga Klasa: metoda2()");
 }
 
public void metoda3(){
  System.out.println("radi iako je protected");
 }
}

