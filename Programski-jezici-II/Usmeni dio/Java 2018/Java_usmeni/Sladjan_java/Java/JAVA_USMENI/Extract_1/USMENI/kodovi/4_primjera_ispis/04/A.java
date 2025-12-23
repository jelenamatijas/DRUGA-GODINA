
public abstract class A {

 A() {
System.out.println("konstruktor A");
}
public abstract String metoda();

public String  metoda(String a) {
System.out.println("Metoda A2");
return "Metoda A2";
}
protected void finalize() throws Throwable
{
 System.out.println("finalize");
}
}