import java.io.*;
import java.util.*;

public class G1{
  
 public static void main(String args[]) throws Exception{
   
 G2 g2=new G2();
 G3 g3=new G3("a");
 ObjectOutputStream cout=new ObjectOutputStream(new FileOutputStream("G1.out"));
  cout.writeObject(g2);
  cout.writeObject(g3);
  cout.close();
  ObjectInputStream cin=new ObjectInputStream(new FileInputStream("G1.out"));
  G2 g22=(G2)cin.readObject();
  System.out.println(g22.a);
  System.out.println(g22.b);
  
  G3 g33=(G3)cin.readObject();
  System.out.println(g33.a);
  System.out.println(g33.b);
  cin.close();
  }
 
  }

class G2 implements Externalizable{
int a= 1;
transient int b=2;

public G2(){  //MORA POSTOJATI poziva se prije readExternal
System.out.println("G2 konstruktor");
}
public void writeExternal(ObjectOutput out)throws IOException{
out.write(3);//
out.write(4);//
System.out.println("G2 writeExternal");
//cout.close();
}
public void readExternal(ObjectInput in)throws IOException,ClassNotFoundException{
  a = in.read();
  b = in.read();
System.out.println("G2 readExternal");
}
}

class G3 implements Serializable{
int a=5;
transient int b=6;
public G3(String s){
System.out.println("G3 konstruktor");
}
private void writeObject(ObjectOutputStream out)throws IOException{
System.out.println("G3 writeObject");
out.write(a);
out.write(b);
//out.close();
}
private void readObject(ObjectInputStream in)throws IOException,ClassNotFoundException{
System.out.println("G3 readObject");
a=in.read();
b=in.read();
//in.close();
}
}

