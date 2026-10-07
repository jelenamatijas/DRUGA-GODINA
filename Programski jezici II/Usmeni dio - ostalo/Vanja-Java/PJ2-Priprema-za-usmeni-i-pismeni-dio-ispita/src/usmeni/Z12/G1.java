package usmeni.Z12;

// g1.java

import java.io.*;

class G1 {
    public static void main(String args[]) throws Exception {
        G2 g2 = new G2();
        G3 g3 = new G3("a1");
        G4 g4 = new G4();
        G5 g5 = new G5();
        ObjectOutputStream cout =
                new ObjectOutputStream(new FileOutputStream("g1.out"));
        cout.writeObject(g2);
        cout.writeObject(g3);
        cout.writeObject(g4);
        cout.writeObject(g5);
        ObjectInputStream cin =
                new ObjectInputStream(new FileInputStream("g1.out"));
        G2 g22 = (G2) cin.readObject();
        System.out.println(g22.a + "\n" + g22.b);
        G3 g33 = (G3) cin.readObject();
        System.out.println(g33.a + "\n" + g33.b);
        G4 g44 = (G4) cin.readObject();
        System.out.println(g44.x + "\n" + g44.y);
        G5 g55 = (G5) cin.readObject();
        System.out.println(g55.x + "\n" + g55.y + "\n" + g55.g3);
        cin.close();
    }
}

class G2 implements Externalizable {
    public int a = 1;
    transient int b = 2;

    public G2() {
        System.out.println("G2 konstruktor");
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.write(3);
        out.write(4);
        System.out.println("G2 writeExternal");
    }

    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        System.out.println("G2 readExternal");
    }
}

class G3 implements Serializable {
    static int a = 5;
    transient int b = 6;

    public G3(String s) {
        System.out.println("G3 konstruktor");
    }

    public void writeObject(ObjectOutputStream out) throws IOException {
        System.out.println("G3 writeObject");
        out.write(a);
        out.write(b);
    }

    public void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        System.out.println("G3 readObject");
        a = in.read();
        b = in.read();
    }
}

class G4 implements Serializable {
    volatile int x = 1;
    volatile int y = 2;

    public G4() {
        System.out.println("G4 konstruktor");
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        System.out.println("G4 writeObject");
        out.writeInt(5);
        out.writeInt(6);
    }

    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        System.out.println("G4 readObject");
    }
}

class G6 {
    public G6() {
        System.out.println("G6()");
    }
}

class G5 extends G4 implements Serializable {
    G3 g3 = new G3("a1");

    public G5() {
        System.out.println("G5 konstruktor");
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        System.out.println("G5 writeObject");
        out.writeInt(0xffffffff);
        out.write(7);
        out.writeObject(g3);
    }

    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        System.out.println("G5 readObject");
        x = in.read();
        y = in.read();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.write(3);
        out.write(4);
        System.out.println("G5 writeExternal");
    }

    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {
        System.out.println("G5 readExternal");
    }


}