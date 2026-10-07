
public class Test {
public static void main(String[] args) {
B b = new B();
C c = new C();



b.metoda();
b.metoda("a");
c.metoda();
c.metoda("c");
c.metoda(b);
}
}