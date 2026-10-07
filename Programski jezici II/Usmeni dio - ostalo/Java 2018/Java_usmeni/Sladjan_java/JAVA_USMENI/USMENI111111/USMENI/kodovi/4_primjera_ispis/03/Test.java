
public class Test {
public static void main(String[] args) {

C c = new C();
B b = new B();
//c.metoda(3);

b.metoda();

c.metoda("Metoda C");
B.B1 b1 = b.new B1();
b1.metoda();
b.metoda("A");
c.metoda();
//c.metoda(b);
}
}