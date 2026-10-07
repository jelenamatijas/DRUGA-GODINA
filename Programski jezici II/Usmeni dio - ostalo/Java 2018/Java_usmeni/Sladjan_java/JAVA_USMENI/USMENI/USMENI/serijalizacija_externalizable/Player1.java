import java.io.*;
class Player1 implements Externalizable {
  public Player1() {
    System.out.println("Player1 Constructor");
  }
  public void writeExternal(ObjectOutput out)
      throws IOException {
    System.out.println("Player1.writeExternal");
  }
  public void readExternal(ObjectInput in)
     throws IOException, ClassNotFoundException {
    System.out.println("Player1.readExternal");
  }
}

class Player2 implements Externalizable {
  Player2() {
    System.out.println("Player2 Constructor");
  }
  public void writeExternal(ObjectOutput out)
      throws IOException {
    System.out.println("Player2.writeExternal");
  }
  public void readExternal(ObjectInput in)
     throws IOException, ClassNotFoundException {
    System.out.println("Player2.readExternal");
  }
}
