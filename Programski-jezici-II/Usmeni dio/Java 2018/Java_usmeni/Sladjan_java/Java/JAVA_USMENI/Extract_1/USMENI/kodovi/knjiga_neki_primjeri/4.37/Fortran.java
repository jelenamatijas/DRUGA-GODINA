 public class Fortran {
 static int bump(int i) { return i + 2; }
public static void main(String[] args) {
 for(int x = 0; x < 5; bump(x))
System.out.print(x + " ");
 } }