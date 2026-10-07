// D1.java

public class D1 extends D3 implements DI {
    public static void main(String[] args) {
        D3 niz[] = {new D3(), new D2(), new DI()};
        for (int i = 0; i < niz.length; i++) {
            niz[i].metoda();
        }
    }
    public D1 metoda() {
        System.out.println("D1: metoda()");
        return (D1) super.metoda();
    }
}
class D2 extends D3 {
    public D2 metoda() {
        System.out.println("D2: metoda()");
        return new D2();
    }
}

class D3 {
    public D2 metoda() {
        System.out.println("D3: metoda()");
        return new D3();
    }
}

interface DI {
    D3 metoda();
}
