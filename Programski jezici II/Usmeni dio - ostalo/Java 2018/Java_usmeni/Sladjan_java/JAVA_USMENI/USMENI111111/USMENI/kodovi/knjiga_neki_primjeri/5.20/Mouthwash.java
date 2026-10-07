 public class Mouthwash {
 static int x = 1;
 public static void main(String[] args) {
 int x = 2;
 for(int i=0; i< 3; i++) {
 if(i==1) System.out.print(x + " ");
 }
 go();
 System.out.print(x + " "/*+ i */ );
 }
  static void go() { int x = 3; }
 }