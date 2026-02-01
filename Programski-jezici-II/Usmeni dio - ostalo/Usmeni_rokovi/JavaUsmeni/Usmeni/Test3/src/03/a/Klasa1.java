import java.util.*;

public class Klasa1 {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        System.out.println(
            numbers.stream()
                   .filter(e -> e % 2 == 0)
                   .findFirst()
                   .orElse(0));
    }
}