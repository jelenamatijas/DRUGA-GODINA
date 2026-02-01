import java.util.*;

public class Klasa5 {
    public static void main(String a[]) {
        List<Integer> list = 
            Arrays.asList(1, 6, 3, 4, 8, 6, 10, 8, 10, 10);
        Set<Integer> set = new HashSet<>(list);
        System.out.println(list);
        System.out.println(set);
    }
}