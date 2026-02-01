import java.util.stream.Stream;

public class Klasa4 {
    public static void main(String[] args) {
        Stream.iterate(1, e -> e * 2)
              .filter(Klasa4::isGT10)
              .limit(50);
    }
    
    public static boolean isGT10(int number) {
        System.out.println("isGT10 " + number);
        return number > 10;
    }
}