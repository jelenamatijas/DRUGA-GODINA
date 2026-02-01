 import java.io.*;
 class ElectronicDevice { ElectronicDevice() { System.out.print("ed "); }}
 class Mp3player extends ElectronicDevice implements Serializable {
 Mp3player() { System.out.print("mp "); }
 }
 