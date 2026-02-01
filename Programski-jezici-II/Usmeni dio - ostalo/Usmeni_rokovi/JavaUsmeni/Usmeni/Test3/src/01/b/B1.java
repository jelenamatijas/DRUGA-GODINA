// B1.java

public class B1 {

    B2 b2;
    private int x = 10;
    private int y = 5;
    
    public B1() { 
        System.out.println("B1()"); 
    }
    
    static class B2 {
        B3 b3;
        int z = 5;
        
        public B2() {  System.out.println("B2()"); }
        public int metoda() {
            return y + z;
        }
    }
    
    protected static class B3 {
        public B3() { System.out.println("B3()"); }
        
        public int metoda() {
            return x * 2;
        }
        
        class B4 extends B3 {
            public B4() { System.out.println("B4()"); }
            public void metoda(int n) { 
                System.out.println(n); 
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
    }
}