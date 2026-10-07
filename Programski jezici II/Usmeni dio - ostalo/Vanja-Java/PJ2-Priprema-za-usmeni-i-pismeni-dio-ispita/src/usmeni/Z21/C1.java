package usmeni.Z21;

class C1 {
    public static C1 ref;

    public C1() {
        this(ref);
        System.out.println("C1()");
    }

    public C1(C1 c1) {
        System.out.println("C1(C1)");
    }

    public static void main(String[] args) throws Exception {
        C1 c1 = new C1();
        C1 c2 = new C2();
        ref = c1;
        try {
            System.out.println(c1.m());
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

    C1 metoda(C1 c) throws CE1 {
        System.out.println("C1 metoda");
        C2 c2 = new C2();
        if (c instanceof C1) {
            System.out.println("method 1");
        } else
            throw new CE2();
        return this;
    }

    int m() throws RuntimeException {
        int x = 125691 % 3;
        int y = 5;
        try {
            y = 25 / x;
        } catch (RuntimeException ex) {
            throw new CE1("CE1111");
        } finally {
            System.out.println("finally...");
            return 1;
        }
    }
}

class C2 extends C1 {

    public C2() {
        System.out.println("C2()");
    }

    C1 metoda(C1 c) throws CE1 {
        System.out.println("C2 metoda");
        C1 a[] = new C1[3];
        for (int i = 0; i < 3; i++) {
            a[i] = new C1();
        }
        try {
            if (errorCheck() || c instanceof C2)
                throw new CE2("Error 2");
            else if (errorCheck() && c instanceof C1)
                return a[2 + 1];
            else
                throw new CE2("Error 1");
        } catch (CE1 e) {
            System.out.println("C2 - CE2");
        } finally {
            ref = null;
            return new C1();
        }
    }

    boolean errorCheck() {
        return false;
    }
}

class CE1 extends Exception {
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