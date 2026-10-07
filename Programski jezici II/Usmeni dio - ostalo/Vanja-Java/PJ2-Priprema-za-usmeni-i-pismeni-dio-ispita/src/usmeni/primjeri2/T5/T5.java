package usmeni.primjeri2.T5;

public class T5<X extends Object> {
    private X x;

    public T5(X x) {
        this.x = x;
    }

    private double getDouble() {
        return ((Double) x);
    }

    public static void main(String args[]) {
        Double d = 10d;
        Integer i = d.intValue();
        T5<Integer> a = new T5<Integer>(1);
        System.out.print(a.getDouble());
    }
}