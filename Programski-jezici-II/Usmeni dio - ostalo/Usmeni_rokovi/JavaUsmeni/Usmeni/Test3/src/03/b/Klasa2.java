public class Klasa2 {
    public static void main() {
        new Thread(() -> print("Hello")).run();
        System.out.println("World!");
    }
    
    static void print(String str) {
        System.out.print(str);
    }
}