import a.A;
public class Test { public static void main(String[] args) {
A a = new A();
a.metoda();
C c = new C();
B b = new B();
c.metoda(b);
A a2 = new A();
a2.metoda(); 
B.D d = b.new D();
C.D d2 = c.new D();
a2.metoda();
b.metoda(2);
c.metoda(3);
b.metoda();
d.metoda();
c.metoda();
d2.metoda();
c.metoda(a);
}
}
