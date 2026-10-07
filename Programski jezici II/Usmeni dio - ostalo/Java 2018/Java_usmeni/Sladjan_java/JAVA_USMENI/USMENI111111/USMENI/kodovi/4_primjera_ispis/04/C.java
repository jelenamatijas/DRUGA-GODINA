
public class C extends B {

 C() {
System.out.println("konstruktor C");
}
public String metoda(A a) {
System.out.println(a.metoda());
return "Metoda C";
}
public String metoda(int c) {
System.out.println(c);
return "Metoda C";
}
public String  metoda(String a) {
System.out.println("Metoda C");
return "Metoda C";
}

}