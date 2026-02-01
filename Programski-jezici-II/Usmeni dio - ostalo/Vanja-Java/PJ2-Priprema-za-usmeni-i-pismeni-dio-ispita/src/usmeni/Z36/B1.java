package usmeni.Z36;

/*class B1 {
    B2 b2;
    private static int x = 10;
    private int y = 5;

    public B1() {
        System.out.println("B1()");
    }

    static class B2 implements B3.B4.BI34 {
        B3 b3;
        private int z = 5;

        public B2() {
            System.out.println("B2()");
        }

        public int metoda() {
            return z * 2;
        }

    }

    protected static class B3 {
        public B3() {
            System.out.println("B3()");
        }

        public int metoda() {
            return B1.x * 2;
        }

        class B4 extends B3 {
            public B4() {
                System.out.println("B4()");
            }

            public void metoda(int n) {
                System.out.println(n);
            }

            interface BI34 {
                int metoda();
            }
        }
    }

    static class B5 extends B2 {
        B3.B4.BI34[] arr;

        public B5(B3.B4.BI34... args) {
            arr = args;
            System.out.println("B5()");
        }

        public void izvrsi() {
            for (B3.B4.BI34 e : arr)
                System.out.println(e.metoda());
        }

        static class B6 extends B5 implements B3.B4.BI34 {
            public B6() {
                System.out.println("B6()");
            }
        }
    }

    protected int metoda() {
        return x;
    }

    public static void main(String[] args) {
        B1 b1 = new B1();
        b1.b2 = new B1.B2();
        B1.B3 b3 = new B1.B3();
        b1.b2.b3 = b3;
        System.out.println(
                b1.metoda() + "\n" +
                        b1.b2.metoda() + "\n" +
                        b3.metoda() + "\n" +
                        b1.b2.b3.metoda());
        B5 b5 = new B1.B5(new B5.B6(), new B2(), () -> B1.x * 2);
        b5.izvrsi();
    }
}*/