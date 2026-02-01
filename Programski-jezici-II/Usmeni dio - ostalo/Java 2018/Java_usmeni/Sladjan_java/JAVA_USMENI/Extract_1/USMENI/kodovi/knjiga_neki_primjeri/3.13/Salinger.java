 import java.io.*;
public class Salinger extends Author {
 private void write(int x) { }
 protected void write(long x) throws FileNotFoundException { }
 protected int write(short x) { return 7; }
 public void write() { }
 }