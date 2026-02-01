import a.A;
public class B extends A {
int j = 3;
B() {
System.out.println("konstruktor B");
System.out.println("i: " + i + " j: " + j);
} 
public int metoda(int i) {
System.out.println(i++);
return j--;
}
class D{
D(){
System.out.println("konstruktor D");
}
public int metoda() {
System.out.println(i++);
return i;
}
}
}