public class Klasa1 {
 int i = 0;

 public static void main(String argv[]) {
 }

 Klasa1() {
  top: while (i < 2) {
   System.out.println(i);
   i++;
   continue top;
  }
 }
}
