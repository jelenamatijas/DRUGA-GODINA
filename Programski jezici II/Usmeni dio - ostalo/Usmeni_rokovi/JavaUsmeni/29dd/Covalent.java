class Bonds {
Bonds force() { return new Bonds(); }
}
public class Covalent extends Bonds {
Covalent force() { return new Covalent(); }
public static void main(String[] args) {
new Covalent().go(new Covalent());
}
void go(Covalent c) {
go2(new Bonds().force(), c.force());
}
void go2(Bonds b, Covalent c) {
Covalent c2 =(Covalent)b;
Bonds b2 = (Bonds)c;
}
}