import a.A;
public class C extends B {
private int i = 0;
protected C() {
System.out.println("konstruktor C");
System.out.println("i: " + i + " j: " + j);

}
public int metoda(A a) {
System.out.println(a.metoda());
return i;
}
public int metoda(int i) {
System.out.println(i--);
return ++j;
}
public int metoda() {
System.out.println(i);
return i++;
}
}