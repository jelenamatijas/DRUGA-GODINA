public class A{
public static void main(String[] args) {
String a = "newspaper";
a = a.substring(5,7);
System.out.println(a);
char b = a.charAt(1);
System.out.println(b);
a = a + b;
System.out.println(a);
C c = new C();
c.ispis();
c.metoda();
}
}
abstract class B extends A {
protected abstract void metoda();
protected String ispis(){ return "ispis"; }
}
class C extends B{
private void metoda(){ System.out.println("C"); }
}