// Memory.java

public class Memory {
    int id;
    double[] doubles = new double[10000000];
    long[] longs = new long[6000000];
    Memory[] mys = new Memory[3];
    Memory m;
    Dumb d;
    
    public Memory(Memory m) {
        this.m = m;
        if (m != null)
            id = m.id + 1;
    }
    
    public static void main(String[] args) {
        Memory m0 = new Memory(null);
        Memory m1 = new Memory(m0);
        Memory m2 = new Memory(m1);
        Memory m3 = new Memory(m2);
        Memory m4 = m3;
        Memory m5 = new Memory(m0);
        Memory[] ms = new Memory[3];
        ms[0] = new Memory(m2);
        ms[1] = new Memory(m3);
        ms[2] = new Memory(m2);
        m1 = m2 = m3 = m5 = null;
        System.gc();
        ms[0] = new Memory(ms[0]);
        ms[1] = new Memory(ms[1]);
        ms[2] = new Memory(ms[2]);
        ms[2].m = ms[0];
        m3 = new Memory(ms[0] = null);
        ms[0] = new Memory(ms[0]);
        System.gc(); // 1
    }
}

class Dumb {
    float[] floats = new float[20000000];
    int[] ints = new int[40000000];
}