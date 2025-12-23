import java.util.*;
public class F1<T1, T2 extends Number, T3>
extends F2<T1, T2> implements FI<T1> {
T3 t3;
public F1(T1 t1, T2 t2, T3 t3) {
super(t1, t2);
this.t3 = t3;
}
public String print() {
return String.format("%s - %s - %s", t1, number, t3);
}
public static void main(String[] args) {
List<Integer> numbers = Arrays.asList(1, 2, 3);
FI<String> f1 = new F1<>("Java", 10, numbers);
F2 f2 = new F2<List<Integer>, Double>(numbers, 1.5);
F3 f3 = new F3<>(numbers.get(2));
System.out.println(f1.print());
System.out.println(f2.toPercentage());
System.out.println(f3.getNumber());
}
}
class F2<T1, T2 extends Number> {
T1 t1;
Number number;
public F2(T1 t1, T2 number) {
this.t1 = t1;
this.number = number;
}
public String toPercentage() {
return (number.doubleValue() * 100) + "%";
}
}
class F3<T2 extends Number> extends F2<String, T2> {
private T2 number;
public F3(T2 number) {
super(number.toString(), number);
this.number = number;
}
public Number getNumber() {
return number;
}
}
interface FI<T1> {
String print();
}