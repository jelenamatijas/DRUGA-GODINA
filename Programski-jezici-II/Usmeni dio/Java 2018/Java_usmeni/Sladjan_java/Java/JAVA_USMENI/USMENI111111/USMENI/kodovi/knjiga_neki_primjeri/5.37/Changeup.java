 import java.io.*;
 public class Changeup {
 public static void main(String[] args) throws IOException {
 new Changeup().go();
 new Changeup().go2();
 new Changeup().go3();
 }
 void go() { throw new IllegalArgumentException(); }

 void go2() throws FileNotFoundException { }

 void go3() {
 try { throw new Exception(); }
 catch (Throwable th) { throw new NullPointerException(); }
 } }