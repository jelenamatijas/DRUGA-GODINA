import java.util.function.*;

public class Klasa3 {
    public static void main(String[] args) {
        int x = 10;
        Supplier<Integer> temp = () -> compute(x);
        
        if (x > 15 && temp.get() < 50) {
            System.out.println("Correct");
        } else {           
            System.out.println("Incorrect");
        }
    }
    
    static int compute(int number) {
        System.out.println("Computing...");
        return 2*number + 1;
    }
}