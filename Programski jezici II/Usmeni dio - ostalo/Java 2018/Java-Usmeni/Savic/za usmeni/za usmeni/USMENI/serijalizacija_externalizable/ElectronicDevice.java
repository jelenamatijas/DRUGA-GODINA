import java.io.*;

public class MiniPlayer extends Mp3player {
MiniPlayer() { System.out.print("mini "); }
 public static void main(String[] args) {
 MiniPlayer m = new MiniPlayer();
 try {
 FileOutputStream fos = new FileOutputStream("dev.txt");
 ObjectOutputStream os = new ObjectOutputStream(fos);
 os.writeObject(m); os.close();
 FileInputStream fis = new FileInputStream("dev.txt");
 ObjectInputStream is = new ObjectInputStream(fis);
MiniPlayer m2 = (MiniPlayer) is.readObject(); is.close();
} catch (Exception x) { System.out.print("x "); }
 } }

 class ElectronicDevice { ElectronicDevice() { System.out.print("ed "); }}
 class Mp3player extends ElectronicDevice  implements Serializable {
 Mp3player() { System.out.print("mp "); }
}

