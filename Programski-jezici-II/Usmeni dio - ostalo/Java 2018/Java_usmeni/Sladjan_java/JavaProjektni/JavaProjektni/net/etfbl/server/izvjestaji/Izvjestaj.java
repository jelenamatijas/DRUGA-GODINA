package net.etfbl.server.izvjestaj;
import java.io.*;
import java.net.*;
import java.util.*;

public class Izvjestaj implements Serializable {
  private Date datum;
  private String sadrzaj;
  private GenerickiPrilog<String> prilog;
  
  public static String path = "net" + File.separator + "etfbl" + File.separator +
                              "server" + File.separator + "izvjestaji" + File.separator;
  
  public Izvjestaj() { }
  
  public Izvjestaj(String sadrzaj) {
    datum = new Date();
    this.sadrzaj = sadrzaj;
    prilog = new GenerickiPrilog<String>();
  }
  
  @Override
  public String toString() {
    return sadrzaj;
  }
  
  public String getDate() {
    String res = "";
    res += datum.getTime();
    return res;
  }
  
  public static void save(Izvjestaj i) {
    try {
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + i.getDate()));
      oos.writeObject(i);
      oos.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static Izvjestaj load(String name) {
    try {
      Izvjestaj temp;
      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path + name));
      temp = (Izvjestaj)ois.readObject();
      ois.close();
      return temp;
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
    