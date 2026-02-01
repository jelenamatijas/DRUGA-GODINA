package usmeni.Z6;


//1300MB
class A {
    public long[] longs = new long[10_000_000]; // 80
    public char[] chars = new char[15_000_000]; // 30
    public A a;
    public B b = new B();
    public A[] am[] = new A[2][3];
    public int id;
    static int globalId = 1;

    public A() {
        this.id = globalId++;
        System.out.println("A() " + id);
    }

    public A(A a) {
        this();
        this.a = a;
        System.out.println("A(A) ");
    }

    public A(A a, B b) {
        this(a);
        this.b = b;
        System.out.println("A(A, B) ");
    }

    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new A(a1);
        A a3 = new A(a2);
        B b1 = new B();
        A a4 = new A(a1, b1);
        a4.am[0][0] = new A(a4);
        a4.am[0][1] = new A(a4.am[0][0], new B());
        a1 = a2 = a3 = null;
        a4.am[1][0] = new A(); // Trebalo bi da ovdje bude OutOfMemoryError
        System.gc(); // 680 MB
        A tmp[] = new A[3];
        tmp[0] = new A(); // 850
        tmp[1] = new A(tmp[1], b1); // 1020
        tmp[2] = new A(tmp[0]); // 1190
        b1 = null;
        a4.am[0] = tmp;
        a4.am[0][0] = null;
        System.gc(); //
    }

    public void finalize(){
        System.out.println("A finalize");
    }
}

class B {
    public float[][] floats = new float[10_000][1000]; // 40
    public int[] ints = new int[5_000_000]; // 20

    public void finalize(){
        System.out.println("A finalize");
    }

    public B(){
        System.out.println("B()");
    }
}