package Projektni_Zadatak.pricalo.server.izvjestaj;

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
  
  public static String path = "Projektni_Zadatak" + File.separator + "pricalo" + File.separator +
                              "server" + File.separator + "izvjestaji";
  
  public Izvjestaj() { }
  
   public Izvjestaj(String sadrzaj)
   {
    date = new SimpleDateFormat("yyyy#MM#dd").format(Calendar.getInstance().getTime());
    
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
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + File.separator + i.getDate()));
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