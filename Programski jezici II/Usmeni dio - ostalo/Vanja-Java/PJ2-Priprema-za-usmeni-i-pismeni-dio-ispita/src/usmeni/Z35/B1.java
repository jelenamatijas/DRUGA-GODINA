package usmeni.Z35;

// B1.java

import java.io.IOException;

/*class B1 implements BI1 {
    //{
    String j = "j";

    public B1() {
        super();
        System.out.println("B1()");
    }

    public void print(String str) {
        System.out.println(str.isEmpty() ? "" : str.charAt(0));
    }

    public static void main(String... argv) throws Exception {
        B1 b1 = new B1();
        B1 b2 = new B2();
        BI1 b3 = new B3("b3");
        BI2 b4 = new B2();

        b1.print(b1.j);
        b2.print("av");
        b2.close();
        BI2.BI3 tmp = (BI2.BI3) b2.clone(); // compile error BI3 navedeno samo, ali je to ugnje\deni interfejs i ne pristupa mu se direktno
        System.out.println(tmp == b2);
        System.out.println(((B2) tmp).str == ((B2) b2).str);
        b4.print("b3");
        ((BI1) b3).close();
        try (B3 b5 = new B3("a")) {
            b5.print("Zadnja linija?");
        }

    }
}

class B2 extends B1 implements BI2, BI2.BI3 {

    static BI1 b1 = new B1();
    String str = "";

    public B2() {
        System.out.println("B2()");
    }

    public void close() {
        System.out.println("B2 closed...");
    }

    public void print(String str) {
        str = str + 'a';
        System.out.println(str);
    }
}

interface BI1 extends AutoCloseable {

    default void close() throws IOException {
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

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}

abstract interface BI2 extends BI1 {
    public void print(String str);

    public interface BI3 extends BI2 {
        default Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }
    }

}*/