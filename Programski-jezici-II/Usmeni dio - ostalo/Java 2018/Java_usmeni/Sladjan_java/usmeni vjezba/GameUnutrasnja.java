import java.lang.String;

public class GameUnutrasnja {
 static String s = "-";
 String s2 = "s2";
GameUnutrasnja(String arg) { s += arg; }
 
 class Go extends GameUnutrasnja {
   { s += "i "; 
   s += s2;
 }
 Go() { super(new GameUnutrasnja("a").s2);
   System.out.println("test: " + s + " " +  s2);
 }             //greska ne moze //pozvati sa parametrom s2 nadklase Game prvo se mora //ona konstruisati
 
 }
 public static void main(String[] args) {
 GameUnutrasnja g = new GameUnutrasnja("s");
 GameUnutrasnja.Go f = g.new Go();
 System.out.println(s);
 }
 static { s += "sb "; }
 
}