package net.etfbl.io;

import java.io.File;

public class FileTest2 {
  public FileTest2(String s) {
    try {
      File f = new File(s);
      System.out.println("separatorChar: " + File.separatorChar);
      System.out.println("pathSeparatorChar: " + File.pathSeparatorChar);
      System.out.println("getName: " + f.getName());
      System.out.println("getPath: " + f.getPath());
      System.out.println("getAbsolutePath: " + f.getAbsolutePath());
      System.out.println("getCanonicalPath: " + f.getCanonicalPath());
      System.out.println("isAbsolute: " + f.isAbsolute());
      System.out.println("isDirectory: " + f.isDirectory());
      System.out.println("isFile: " + f.isFile());
      System.out.println("lastModified: " + f.lastModified());
      System.out.println("length: " + f.length());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
	    new FileTest2("D:\\Eclipse\\workspace");
	    System.out.println("====================");
	    new FileTest2("D:\\test\\..\\test");
	    System.out.println("====================");
	    new FileTest2("..");
  }
}