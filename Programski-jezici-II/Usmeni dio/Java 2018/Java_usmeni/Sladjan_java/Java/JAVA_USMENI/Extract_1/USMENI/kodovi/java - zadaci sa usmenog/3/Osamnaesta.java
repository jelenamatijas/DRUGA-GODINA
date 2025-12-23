public class Osamnaesta {
 int i=0;
 public static void main(String args[]) {
  Osamnaesta os = new Osamnaesta(); // ako nema konstruktora, nista se ne desava, sa ovim konst ispisuje 0 i 1
 }
 
 Osamnaesta() {
  top:
  while(i<2) {
   System.out.println(i);
   i++;
   continue top;
  }
 }
}