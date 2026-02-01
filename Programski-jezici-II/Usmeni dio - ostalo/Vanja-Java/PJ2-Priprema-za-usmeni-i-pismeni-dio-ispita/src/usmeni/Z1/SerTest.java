package usmeni.Z1;


import java.io.*;

public class SerTest {
    public static void main(String[] args) throws Exception {
        G1 g1 = new G1();
        G2 g2 = new G2();
        G3 g3 = new G3(g1);
        ObjectOutputStream cout =
                new ObjectOutputStream(new FileOutputStream("g1.out"));
        cout.writeObject(g3);
        System.out.println(G2.i);
        cout.writeObject(g2);
        System.out.println(G2.i);
        cout.writeObject(g1);
        cout.close();
        System.out.println("Data saved!!");
        ObjectInputStream cin =
                new ObjectInputStream(new FileInputStream("g1.out"));
        G3 g33 = (G3) cin.readObject();
        System.out.println(g33.g1.x + " " + g33.a + " " + g33.c);
        G2 g22 = (G2) cin.readObject();
        System.out.println(g22.g1 + " " + g22.b);
        G1 g11 = (G1) cin.readObject();
        System.out.println(g11.x + " " + g11.val);
        System.out.println(g11 == g33.g1);

    }
}

class G1 implements Externalizable {
    transient int x = 5;
    double val = 10.4;

    public G1() {
        System.out.println("g1");
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(x);
        out.writeDouble(val);
        System.out.println("g1 writeExternal");
    }

    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        System.out.println("g1 readExternal");
        x = in.readInt();
        val = in.readDouble();
    }
}

class G2 implements Serializable {
    public static int i = 1;
    public G1 g1;
    public static String c = "CCC";
    public volatile int a = -1, b = -2;

    public G2() {
        System.out.println("G2()");
        this.a = 10;
        this.b = 20;
    }

    public G2(G1 g1) {
        this.g1 = g1;
        System.out.println("G2(g1)");
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        System.out.println("G2 writeObject" + i++);
        out.writeObject(g1);
    }

    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        System.out.println("G2 readObject");
        this.g1 = (G1) in.readObject();
    }
}

class G3 extends G2 {
    public void G3() {
        System.out.println("G3()");
    }

    public G3(G1 g1) {
        super(g1);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        System.out.println("G3 writeObject");
    }

    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        System.out.println("G3 readObject");

    }
}