import java.io.*;

public class Players {
public static void main(String[] args) throws IOException, ClassNotFoundException {
System.out.println("Constructing objects:");
Player1 b1 = new Player1();
Player2 b2 = new Player2();
ObjectOutputStream o = new ObjectOutputStream( new FileOutputStream("Players.out"));
System.out.println("Saving objects:");
o.writeObject(b1);
o.writeObject(b2);
o.close();
ObjectInputStream in = new ObjectInputStream( new FileInputStream("Players.out"));
System.out.println("Recovering b1:");
b1 = (Player1)in.readObject();
System.out.println(b1.b);
System.out.println("Recovering b2:");
b2 = (Player2)in.readObject();
}
}
class Player1 implements Serializable{
//int a = 5;
transient int b = 6;
public Player1(){ System.out.println("G3 konstruktor"); }
private void writeObject(ObjectOutputStream out)throws IOException{
System.out.println("G3 writeObject");
//out.write(a);
//out.write(b);
}
//private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException{
	//System.out.println("Read object ser");
	//b = in.read();
//}
private void readExternal(ObjectInputStream in)throws IOException, ClassNotFoundException{
System.out.println("G3 readObject");
//a = in.read();
b = in.read();
} }
class Player2 implements Externalizable {
public Player2() {
System.out.println("Player2 Constructor");
}
public void writeExternal(ObjectOutput out) throws IOException {
System.out.println("Player2.writeExternal");
}
public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
System.out.println("Player2.readExternal");
}
}