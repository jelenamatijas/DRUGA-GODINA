import java.io.*;

public class TestExternal implements Externalizable{
  private String s;
  
  public TestExternal(){
    System.out.println("Konst");
  }
  
  public TestExternal(String s){ this.s = s;}
  
  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
    s = (String) in.readObject();
  }
  
  public void writeExternal(ObjectOutput out) throws IOException{
    out.writeObject(s);
  }
  
  public static void main(String[] args){
    TestExternal t = new TestExternal("A");
    try{
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.out"));
      oos.writeObject(t);
      oos.close();
    } catch(Exception ex){
      ex.printStackTrace();
    }
    System.out.println(t);
    TestExternal tt = null;
    try{
      ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.out"));
      tt = (TestExternal) ois.readObject();
      ois.close();
    } catch(Exception ex){
      ex.printStackTrace();
    }
    System.out.println(tt);
  }
  
  public String toString(){
    return s;
  }
}