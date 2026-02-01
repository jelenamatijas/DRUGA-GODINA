package usmeni.Z10;

// g1.java

import java.io.*;

class G1 {
    public static void main(String args[]) throws Exception {
        G2 g2 = new G2();
        G3 g3 = new G3("a1");
        G5 g5 = new G5();
        ObjectOutputStream cout =
                new ObjectOutputStream(new FileOutputStream("g1.out"));
        cout.writeObject(g2);
        cout.writeObject(g3);
        cout.writeObject(g5);
        ObjectInputStream cin =
                new ObjectInputStream(new FileInputStream("g1.out"));
        G2 g22 = (G2) cin.readObject();
        System.out.println(g22.a);
        System.out.println(g22.b);
        G3 g33 = (G3) cin.readObject();
        System.out.println(g33.a);
        System.out.println(g33.b);
        ;
        System.out.println(g33.value);
        G5 g55 = (G5) cin.readObject();
        System.out.println(g55.a);
        System.out.println(g55.naziv);
        System.out.println(g55.godine);
        cin.close();
    }
}

class G2 implements Externalizable, Serializable {
    int a = 1;
    int b = 2;

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

    private void writeObject(ObjectOutputStream out) throws IOException {
        System.out.println("G2 writeObject");
        out.writeLong(10);
        out.writeInt(2);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        System.out.println("G2 readObject");
        a = in.readInt();
        b = in.readInt();

    }
}

class G3 implements Serializable {
    transient int a = 5;
    public static int value = 10;
    volatile int b = 6;

    public G3(String s) {
        System.out.println("G3 konstruktor");
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        System.out.println("G3 writeObject");
        out.writeInt(0xFFFFAB12);
    }

    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        System.out.println("G3 readObject");
        a = in.read();
        b = in.read();
    }
}

class G4 {
    int a = 14;
    String naziv = "";

    public G4(String naziv) {
        this.naziv = naziv;
        System.out.println("G4(S)");
    }
}

class G5 extends G4 implements Serializable {
    int godine = 21;

    public G5() {
        super("Banja Luka");
        System.out.println("G5()");
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