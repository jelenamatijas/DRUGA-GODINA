package usmeni.Z20;

class M {

    int id = 0;
    float[] fNiz = new float[1_000_000];
    long[] lNiz = new long[2_500_000];
    B b = new B(0);
    M mNiz[][] = new M[2][3];

    public M(int id) {
        if (id % 2 == 0)
            this.id = id;
        else
            this.id = id + 2;
        System.out.println("M: " + this.id);
    }

    public M(B b, int id) {
        this.id = id;
        this.b = b;
        System.out.println("M: " + this.id);
    }

    @Override
    protected void finalize() {
        System.out.println(this.id + "finalize");
    }

    public static void main(String args[]) {
        M m1 = new M(1);
        M m2 = new M(2);
        M m3 = new M(3);
        B b1 = new B(1);
        B b2 = new B(m3, 2);
        B b3 = new B(m2, 25);
        M m4 = new M(b1, 4);
        M m5 = new M(b2, 5);
        m1.mNiz[0][1] = new M(new B(41), 12);
        m1.mNiz[1][1] = new M(new B(m1, 23), 21);
        m1.mNiz[1][2] = m2;
        m1 = m2 = m5 = null;
        System.gc();
        m1.mNiz[1][1] = m5;
        m3.b = null;
        //b1.m = m1;
        //b2.m = new M(32);
        System.gc();
    }
}

class B {
    int id = 0;
    char cNiz[] = new char[10_000_000];
    int iNiz[] = new int[2_500_000];
    // M m = new M(2); Otkomentarisati za izvorni kod!

    public B(int id) {
        this.id = (id > 1) ? id : (id + 1);
        System.out.println("B: " + this.id);
    }

    public B(M m, int id) {
        this.id = id;
        //this.m = m;
        System.out.println("B: " + this.id);
    }

    @Override
    protected void finalize() {
        System.out.println(this.id + "finalize");
    }
}