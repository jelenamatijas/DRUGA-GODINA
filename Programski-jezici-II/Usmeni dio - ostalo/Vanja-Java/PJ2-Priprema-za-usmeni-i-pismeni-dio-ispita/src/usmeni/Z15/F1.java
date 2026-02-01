package usmeni.Z15;

// F1.java

import java.util.Arrays;
import java.util.List;

class F1<T1, T2 extends Number, T3>
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
        F3 f3 = new F3<>(numbers.get(2), 15, new Integer(127));
        F2<Double, Double> test = new F2<>(2.3, 2.3);
        //F3 test1 = new F3<Integer, String, Character>(2, "test", new Character('a1')); compile error
        System.out.println(test.toPercentage());
        //System.out.println(test1.getString());
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

class F3<T2 extends Number, T3 extends Number, T4> extends F2<String, T2> {
    private T2 number;
    private T3 string;
    private T4 el;

    public F3(T2 number, T3 string, T4 el) {
        super(number.toString(), number);
        this.number = number;
        this.string = string;
        this.el = el;
    }

    public Number getNumber() {
        return number;
    }

    public T3 getString() {
        return string;
    }
}

interface FI<T1> {
    String print();
}