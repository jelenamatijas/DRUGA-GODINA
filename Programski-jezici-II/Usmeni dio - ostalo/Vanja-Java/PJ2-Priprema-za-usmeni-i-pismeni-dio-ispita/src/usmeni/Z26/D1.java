package usmeni.Z26;

class D1 extends D11 {
    static D1 instance;
    String rijec;

    private D1() {
        System.out.println("D1");
    }

    public static void main(String arr[]) {
        D1 d1 = D1.getInstance();
        D1 d2 = D1.getInstance();
        d1.rijec = "hello";
        d2.rijec = new String("HELLO");
        String hello = "HeLl0";
        String hi = new String("hello");
        String hihello = d1.rijec;
        System.out.println(d1.rijec == d2.rijec);
        System.out.println(d1.rijec == hello);
        System.out.println(hello == hi);
        System.out.println(hihello.equals(d1.rijec));
        System.out.println(hihello == d1.rijec);
        System.out.println(d1.rijec.equalsIgnoreCase(d2.rijec));
        System.out.println(d1.compareTo(d2));
    }

    public static D1 getInstance() {
        if (instance == null)
            instance = new D1();
        return instance;
    }


}

class D11 implements IComp {
    public static int i;

    public int compareTo(D11 other) {
        return this.i == other.i ? 0 : 1_000_000;
    }
}

interface IComp {
    default int compareTo(Object other) {
        return 0;
    }
}