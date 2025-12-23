import java.util.stream.*;

public class Klasa4 {
    public static void main(String[] args) {
        Stream.iterate(1, e -> e + 1)
              .filter(e -> isEven(e))
              .limit(10)
              .forEach(System.out::println);
    }
    public static boolean isEven(int num) {
        return num % 2 == 0;
    }
}