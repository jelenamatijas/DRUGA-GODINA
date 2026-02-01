package usmeni.Z7;

//HEAP 1000MB
public class A {

    int id;
    A a;
    B b;
    long[] nizLong = new long[10_000_000];
    int[] nizInt = new int[5_000_000];
    B bArray[][] = new B[2][5];

    {
        b = new B(0);
    }

    public A(int id) {
        System.out.println("A: " + id);
        this.id = id;
    }

    public A(A a, int id, B b) {
        System.out.println("A: " + id);
        this.a = a;
        this.b = b;
        this.id = id;
    }

    @Override
    protected void finalize() {
        System.out.println(id + "finallize");
    }

    public static void main(String[] args) {
        B[] bArr = new B[5];
        for (int i = 0; i < 3; i++) {
            bArr[i] = new B(i);
            new A(null, i, bArr[i]);
        }
        A a0 = new A(0);
        a0.bArray[0] = bArr;
        bArr = null;
        System.gc();
        for (int i = 0; i < 5; i++)
            a0.bArray[1][i] = new B(i);
        A a1 = new A(new A(10), 1, new B(10));
        a0.a = a1.a;
        A a2 = new A(2);
        A a3 = new A(a2, 3, new B(11));
        B b0 = new B(0);
        a1 = null;
        A a4 = new A(a3, 4, new B(12));
        A a5 = new A(a4, 5, a0.bArray[0][0]);
        a0 = null;
        System.gc();
    }
}

class B {

    float[] f = new float[2_500_000];
    char[] c = new char[5_000_000];
    public int id2 = 0;

    public B(int id2) {
        System.out.println("B: " + id2);
        this.id2 = id2;
    }

    @Override
    protected void finalize() {
        System.out.println(id2 + "finallize");
    }
}