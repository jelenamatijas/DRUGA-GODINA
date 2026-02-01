 public class Go extends Game {
 Go() { super("pero"); }//ne moze s2
 { s += "i "; }
 public static void main(String[] args) {
 new Go();
 System.out.println(s);
 }
 static { s += "sb "; }
 }