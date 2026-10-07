package usmeni.primjeri2;

public class C1 {
    public static C1 ref;

    public static void main(String[] args) {
        C1 c1 = new C1();
        C1 c2 = new C2();
        ref = c1;
        try {
            System.out.println(c2.metoda(c2));
            System.out.println(c2.metoda(c1));
        } catch (CE2 e) {
            System.out.println("C1- CE2 catch");
        } catch (CE1 e) {
            System.out.println("C1- CE1 catch");
        } catch (Throwable e) {
            System.out.println("exception");
        } finally {
            System.out.println("finally");
        }
        System.out.println(c2.metoda(ref));
    }

    int metoda(C1 c) {
        if (c instanceof C1) {
            System.out.println("method 1");
        } else
            throw new CE2();
        return 1;
    }
}

class C2 extends C1 {
    int a[] = new int[3];

    int metoda(C1 c) throws RuntimeException {
        try {
            if (errorCheck() && c instanceof C2)
                throw new CE2("Error 2");
            else if (errorCheck() && c instanceof C1)
                return a[3];
            else
                throw new CE1("Error 1");
        } catch (CE1 e) {
            System.out.println("C2 - CE2");
        }
        ref = null;
        return 0;
    }

    boolean errorCheck() {
        return true;
    }
}

class CE1 extends RuntimeException {
    public CE1(String s) {
        System.out.println("CE1 - 2");
    }
}

class CE2 extends CE1 {
    public CE2() {
        super("s");
    }

    public CE2(String s) {
        super(s);
        System.out.println("CE2 - 2");
    }
}