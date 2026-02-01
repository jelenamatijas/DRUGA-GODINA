package usmeni.test2.z3.a;

public class Klasa1 {
    public static void main(String[] args) {
        int i = 0;
        int j = 10;
        int k = 0;
        while (i++ < 5 || --j > 3) {
            k += i + j;
        }
        System.out.println(k);
    }
}