public class Staticka {
 static {
  int x = 5;
 }
 
 static int x,y;
 
 public static void main(String [] args) {
  System.out.println("x="+x);
  x--;
  System.out.println("x="+x);
  metoda();
  System.out.println("x="+x + "   y=" + y);
  System.out.println("x + y + ++x = "+ (x + y + ++x)); // (1  + 0 + ++1)   x je sad = 2
  System.out.println("++staticka.x=" +(++Staticka.x));  //++2 = 3
  System.out.println(y);
 }
 
 public static void metoda() {
  y = x++ + ++x;  // x + ++x; x++
 }
} 