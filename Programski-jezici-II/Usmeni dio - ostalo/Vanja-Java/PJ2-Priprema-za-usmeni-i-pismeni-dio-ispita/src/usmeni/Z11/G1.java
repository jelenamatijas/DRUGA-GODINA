package usmeni.Z11;

import java.io.*;

class G1 {
    public static void main(String[] args) throws Exception {
        G3 g1 = new G3();
        G2 g2 = new G2(g1);
        try (ObjectOutputStream o = new ObjectOutputStream(
                new FileOutputStream("data.bin"))) {
            o.writeObject(g1);
            o.writeObject(g2);
        } finally {
            System.out.println("Data saved");
        }
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("data.bin"))) {
            g1 = (G3) in.readObject();
            System.out.println(g1.i + " " + g1.multiplier + " " + g1.counter);
            g2 = (G2) in.readObject();
            System.out.println(g2.multiplier + " " + g2.counter);
        } finally {
            System.out.println("Data loaded");
        }
        System.out.println(g2.g3.i);
        for (int i = 0; i < g2.counter; i++) {
            System.out.println(G2.multiplier * g1.i);
        }
    }
}

class G2 extends G1 implements Serializable {
    static int multiplier = 10;
    transient int counter = 5;
    G3 g3;

    public G2() {
        System.out.println("G2()");
    }

    public G2(G3 g3) {
        if (g3 != null)
            this.g3 = g3;
        System.out.println("G2(G3)");
    }

    public void writeObject(ObjectOutputStream out) throws IOException {
        System.out.println("G2 writeObject");
        out.write(counter);
    }

    public void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        System.out.println("G2 readObject");
        counter = in.read();
    }
}

class G3 extends G2 implements Externalizable {
    transient int i = 0xa;

    public G3() {
        super();
        System.out.println("G3()");
    }

    public void writeExternal(ObjectOutput out)
            throws IOException {
        System.out.println("G3.writeExternal");
    }

    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        System.out.println("G3.readExternal");
        G2.multiplier = 1337;
    }
}