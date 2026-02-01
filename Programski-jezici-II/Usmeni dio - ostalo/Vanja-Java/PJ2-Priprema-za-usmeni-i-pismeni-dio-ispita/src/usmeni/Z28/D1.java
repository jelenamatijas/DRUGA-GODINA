package usmeni.Z28;

class D1 extends D3 implements DI {
    public static int c;
    public static D2 d2 = new D2();

    public static void main(String[] args) {
        D3 niz[] = {new D3(), new D2()};
        for (int i = 0; i < niz.length; i++) {
            niz[i].metoda();
        }
        DI1[] niz2 = {new D2(), new DI1() {
            public D2 metoda2() {
                return new D2();
            }
        }, new D2()};
        for (int i = 0; i < niz2.length; i++) {
            niz2[i].metoda2();
        }
    }

    public D3 metoda() {
        System.out.println("D1: metoda()");
        return super.metoda();
    }
}

class D2 extends D3 implements DI1 {
    public D2 metoda() {
        System.out.println("D2: metoda()");
        return new D2();
    }

    public D2 metoda2() {
        System.out.println("D2: metoda2()");
        if (D1.c++ == 0)
            return D1.d2;
        else
            return (D2) ((D3) (new D1()));
    }
}


class D3 {
    public D3 metoda() {
        System.out.println("D3: metoda()");
        return new D3();
    }
}

interface DI {
    D3 metoda();
}

interface DI1 extends DI {
    default D3 metoda() {
        return new D3();
    }

    D2 metoda2();
}