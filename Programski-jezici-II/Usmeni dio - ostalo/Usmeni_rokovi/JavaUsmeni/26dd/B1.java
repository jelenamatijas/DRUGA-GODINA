
public abstract class B1 {
B1() {
super();
System.out.println("B1()");
}
public static void main(String[] args) {
B3 b3 = new B3();
b3.metoda();
B2 b2 = new B2();
b2 = b3;
b2.metoda();
B1 b1 = b2;
b1.metoda();
B2 test2 = new B2();
test2.metoda();
}
void metoda() { System.out.println("B1 metoda..."); }
}
class B2 extends B1 {
B2() { System.out.println("B2()"); }
protected void metoda() { System.out.println("B2 metoda..."); }
void metoda2() { System.out.println("B2 metoda..."); }
}
final class B3 extends B2 {
B3() {
super();
System.out.println("B3()");
}
public void metoda() {
System.out.println("B3 metoda...");
}
}