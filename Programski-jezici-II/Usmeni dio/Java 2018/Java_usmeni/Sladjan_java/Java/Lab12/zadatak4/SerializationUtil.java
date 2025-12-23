package Lab12.zadatak4;

import java.io.*;


public class SerializationUtil <T> 
{
  public static <T> T readObject(String fullPath)
  {
    File f = new File(fullPath);
    ObjectInputStream ois = null;
    T t = null;
    if (f.exists())
    {
      try
      {
        ois = new ObjectInputStream(new FileInputStream(f));
        t = (T) ois.readObject();
        ois.close();
        return t;
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
    }
    else 
    {
      System.out.println("Navedeni fajl ne postoji ili putanja do fajla nije dobra");
      
    }
    return t;
  }
  
  public static <T> void save (T object,String path,String fileName)
  {
    File f = new File(path);
    ObjectOutputStream oos = null;
    if (f.exists())
    {
      try
      {
        oos = new ObjectOutputStream(new FileOutputStream(f));
        oos.writeObject(f+fileName);
        oos.close();
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
    }
    else System.out.println("Putanja do mjesta upisa fajla ne valja");
  }
}
          
          
          
          