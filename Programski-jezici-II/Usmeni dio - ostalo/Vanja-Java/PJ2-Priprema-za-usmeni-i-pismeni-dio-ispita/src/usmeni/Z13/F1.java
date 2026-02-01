package usmeni.Z13;

// F1.java

class F1<T1, T2> implements FI<T2> {
    T2 t2;
    T1 t1;

    public static void main(String args[]) {
        F1<? extends Object, Integer> f1 = new F1<String, Integer>();
        FI<Double> f2 = new F1<>();
        F2<Integer, F1> f3 = new F2<>();
        F3<Object, String> f4 = new F3<>(1.5, f3);

        f1.print(f4.x);
        //f1.setFirst("String");
        f1.t2 = 3;
        f3.s = "" + f1.t2;
        System.out.println(f3.getModified(2));
        System.out.println(f4.getSuperModified("" + f1.t2));
    }

    public void setFirst(T1 t1) {
        F2<Double, String> tmp = new F2<>();
        System.out.println(tmp.getModified(3));
        this.t1 = t1;
    }

    @Override
    public void print(T2 t) {
        //System.out.println(String.format("%f", t));
        System.out.println(t);
    }
}

class F2<T1 extends Number, T2> {
    String s;

    public F2() {
        s = "a1";
    }

    public char getModified(int i) {
        return (char) (s.charAt(0) + i);
    }
}

class F3<T, T2> extends F2<Integer, F3> {
    int x;

    public F3(double d, F2 f) {
        x += f.getModified((int) d);
    }

    public String getSuperModified(String num) {
        return "" + new Double(new Integer(x + num) * 0.1);
    }
}

interface FI<T> {
    void print(T t);
}