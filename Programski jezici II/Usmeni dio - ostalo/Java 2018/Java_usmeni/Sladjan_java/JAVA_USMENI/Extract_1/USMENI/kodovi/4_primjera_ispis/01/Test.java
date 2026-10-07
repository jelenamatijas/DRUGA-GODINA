import a.A;
public class Test {
public static void main(String[] args) {
A a = new A();
a.metoda();
C c = new C();
B b = new B();
c.metoda(b);
A a2 = new A();
a2.metoda();
B.D d = b.new D();
a2.metoda();
c.metoda(3);
b.metoda(2);
c.metoda();
c.metoda();
b.metoda();
d.metoda();
c.metoda(a);
}
}
