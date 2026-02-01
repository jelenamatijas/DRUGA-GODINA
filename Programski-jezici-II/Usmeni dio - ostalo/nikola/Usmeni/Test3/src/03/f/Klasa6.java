public class Klasa6 {
    private int x = 10;
    
    public static void main(String[] args) { 
       int y = 15;
       while (x > 2 && (y--) > 5) {
           x--;
           System.out.println(x + " " + y);
       }
    }
}