// Peta.java
import java.io.*;
class Dvanaesta{
 public static void main(String [] args) {
  Peta e = new Peta();
  PrviI i = new Peta();
  DrugiI i2 = e;
  try(BufferedReader in = new BufferedReader(new FileReader("t.txt")))
  {
    
  
  }
  finally{
  }
 }
}

interface PrviI extends DrugiI, TreciI {
 void metoda()throws Exception;
}
interface DrugiI {
 
 void metoda();
}
interface TreciI {
 void metodaI3();
}

class Peta implements PrviI, TreciI {
  int k =0;
 public void metoda() throws Exception {
  System.out.println("metoda Peta");
 }
 public void metodaI3() {
  System.out.println("metoda I3 Peta");
 }
}


