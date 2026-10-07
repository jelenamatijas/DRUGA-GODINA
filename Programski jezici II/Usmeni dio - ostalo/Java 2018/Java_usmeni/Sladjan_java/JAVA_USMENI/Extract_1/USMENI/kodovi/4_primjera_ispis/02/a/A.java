package a;

public class A {
protected static int i = 1;
protected int j = -1;
public A() {
System.out.println("konstruktor A");
}
public int metoda() {
System.out.println(++i);
return ++j;
}
public int metoda(int i) {
System.out.println(i);
return i--;
}
}