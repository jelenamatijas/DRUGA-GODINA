public class Frodo extends Hobbit{
 public static void main(String[] args) {
   Frodo f = new Frodo();
 int myGold = 7;
 System.out.println(f.countGold(myGold, 6));
 }
 }
 class Hobbit {
 int countGold(int x, int y) { return x + y; }
 }