package usmeni.Z34;

// B1.java

class B1 implements BI1 {

    String j = "j";

    public B1() {
        super();
        System.out.println("B1()");
    }

    public void print(String str) {
        System.out.println(str.isEmpty() ? "" : str.charAt(0));
    }

    public void metodaG() {
        System.out.println("B3 metodaG");
    }

    public static void main(String... argv) {
        B1 b1 = new B1();
        B1 b2 = new B2();
        b1.print("Nemanja");
        b2.print("Nist");
        b1.metodaG();
        b2.metodaG();
        B1 niz[] = {new B1(), (B1) new B2(), (B1) new B3("j")};
        for (var el : niz) {
            System.out.println(el);
        }
    }

    @Override
    public String toString() {
        return "B1";
    }
}

class B2 extends B1 {


    String str = "";

    public B2() {
        System.out.println("B2()");
    }

    public void metodaG() {
        System.out.println("B2 metodaG");
    }

    public void close() {
        System.out.println("B2 closed...");
    }

    public void print(String str) {
        str = str + 'a';
        System.out.println(str);
    }

    @Override
    public String toString() {
        return "B2";
    }
}

interface BI1 extends AutoCloseable {

    default void close() {
        System.out.println("Resource closed...");
    }

    class B3 extends B2 implements BI2 {

        String str;

        B3(String x) {
            str = x;
        }

        public void print(String x) {
            StringBuilder builder = new StringBuilder(x + str);
            System.out.println(builder.reverse());
        }

        public void metodaG() {
            System.out.println("B3 metodaG");
        }

        @Override
        public String toString() {
            return "B3";
        }
    }
}

abstract interface BI2 extends BI3 {
    public void print(String str);
}

interface BI3 {
    void metodaG();

    class LocalBI3 {

        public LocalBI3() {
            System.out.println("LocalBI3 constructor...");
        }
    }
}