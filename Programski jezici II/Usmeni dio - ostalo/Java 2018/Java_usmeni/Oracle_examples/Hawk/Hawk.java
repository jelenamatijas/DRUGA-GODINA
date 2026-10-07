class Hawk extends Raptor {
  static{System.out.print(" sbird ");}
public static void main(String[] args) {
System.out.print("pre ");
new Hawk();
System.out.println("hawk ");
}
}

class Raptor extends Bird {
  { System.out.print("r3 "); }
static { System.out.print("r1 "); }
public Raptor() { System.out.print("r2 "); }

static { System.out.print("r4 "); }
}
class Bird {
  static{System.out.print(" s1 ");}
{ System.out.print("b1 "); }
public Bird() { System.out.print("b2 "); }
}