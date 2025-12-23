import java.io.*;
public class Player1
{
  public static void main(String [] args)
  {
    try{
      System.out.println(args[0]);
      if (args[0].equals("1"))
      {
	ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pisi.txt"));
	oos.writeObject(new Player());
	oos.close();
      }
      else
      {
	ObjectInputStream ois = new ObjectInputStream(new FileInputStream("pisi.txt"));
	System.out.println((Player)ois.readObject());
	ois.close();
      }
    }catch(Exception ex)
    {
      ex.printStackTrace();
    }
  }
}
class Player implements Externalizable {
  public Player() {
    System.out.println("Player1 Constructor");
  }
  public void writeExternal(ObjectOutput out)	throws IOException {
    System.out.println("Player1.writeExternal");
    //out.writeObject(this);
  }
  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    System.out.println("Player1.readExternal");
    //in.readObject();
  }
}