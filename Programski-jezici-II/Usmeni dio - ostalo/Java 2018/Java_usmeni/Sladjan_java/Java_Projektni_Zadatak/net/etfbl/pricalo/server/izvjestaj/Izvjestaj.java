package net.etfbl.pricalo.server.izvjestaj;

import java.util.Scanner;
import java.lang.String;
import java.io.*;
import java.net.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
public class Izvjestaj implements Serializable
{
  private String name;
  private String date;
  private String sadrzaj;
  private GenerickiPrilog<String> prilog;
  
  public static String path = "net" + File.separator + "etfbl" + File.separator + "pricalo" + File.separator +
                              "server" + File.separator + "izvjestaji";
  
  public Izvjestaj() { }
  
   public Izvjestaj(String sadrzaj)
   {
    date = new SimpleDateFormat("yyyy#MM#dd_HH#mm#ss").format(Calendar.getInstance().getTime());
    
    this.sadrzaj = sadrzaj;
    prilog = new GenerickiPrilog<String>();
  }
   
   public String toString() {
    return sadrzaj;
  }
  
  public String getDate() {
    
    return date;
  }
  
  public static void save(Izvjestaj i) {
    try {
      File f = new File ("net" + File.separator + "etfbl" + File.separator + "pricalo" + File.separator + "server" + File.separator + "izvjestaji");
      if (!f.exists() && !f.isDirectory()) f.mkdir();
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + File.separator + i.getDate()+".izv"));
      oos.writeObject(i);
      oos.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static Izvjestaj read(String name) {
    try {
      Izvjestaj temp;
      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path + File.separator + name));
      temp = (Izvjestaj)ois.readObject();
      ois.close();
      return temp;
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

   
}//zatvorena klasa