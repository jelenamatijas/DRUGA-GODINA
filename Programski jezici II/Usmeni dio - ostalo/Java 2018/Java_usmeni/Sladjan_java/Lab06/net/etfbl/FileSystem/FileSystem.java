package net.etfbl.FileSystem;


import java.util.Scanner;
import java.util.Arrays;
import java.lang.String;
import java.io.*;


public class FileSystem
{
  private File currentPath;
 
  
  public FileSystem () {};
  
  public FileSystem (String s)
  {
    this.currentPath = new File (s);
  }
  
  public FileSystem (File s)
  {
    this.currentPath = s;
  }
  
  public void changeDir (String name)
  {
    if ("..".equals(name))
    {
      if (currentPath.getParentFile() != null) currentPath = currentPath.getParentFile();
    }
    else
    {
      File f = new File (currentPath.getPath() + File.separator + name);
      if (f.exists()) currentPath = f;
    }
      
  }
  
  
  public void listFiles()
  {
    if (currentPath != null && currentPath.exists())
    {
      File[] s = currentPath.listFiles();
      System.out.println("Sadrzaj direktorijuma: ");
      
      for (File f : s)
      {
        if (f.isDirectory() && !f.isHidden())
        {
          System.out.println("<DIR> " + f.getName());
        }
        else 
        {
          if (f.isFile() && !f.isHidden())
          {
            System.out.println("<FILE> " + f.getName());
          }
        }
      }
    }
    else System.out.println("Putanja direktorijuma nije validna!!!!");
  }
  
  public void createDirectory (String name)
  {
    if (currentPath != null && currentPath.exists())
    {
      File f = new File (currentPath + File.separator + name);
      if (f.exists()) System.out.println("Direktorijum vec postoji!!!");
      else
      {
        f.mkdir();
      }
    }
    else System.out.println("Putanja TEKUCEG direktorijuma nije validna!!!");
  }
      
  public void delete (String name)
  {
     if (currentPath != null && currentPath.exists())
     {
       File f = new File (currentPath + File.separator + name);
       if (f.exists())
       {
       f.delete();
      
       if (f.isDirectory())System.out.println ("Direktorijum " + name + "je IZBRISAN");
       if (f.isFile()) System.out.println ("Fajl " + name + "je IZBRISAN");
       }
     }
     else System.out.println ("Putanja do TEKUCEG direktorijuma nije validna");
  }
    
  public void copyImageFile (String source,String destination)
  {
    if (source.endsWith("jpg") && destination.endsWith("jpg") && !source.equals(destination))
    {
      File f = new File (currentPath + File.separator + source);
      File f1 = new File (currentPath + File.separator + destination);
      
      if (f.exists()&& !f1.exists())
      {
        FileInputStream in = null;
        FileOutputStream out = null;
        try
        {
           in = new FileInputStream (f);
           f1.createNewFile();
           out = new FileOutputStream (f1);
           int b;
           while( (b = in.read())!= -1)
        {
          out.write(b);
        }
        }
        catch (Exception ex)
        {
          ex.printStackTrace();
        }
        finally
        {
          try
          {   
            if (in != null) in.close();
            if (out != null) out.close();
          }
          catch (Exception ex)
          {
            ex.printStackTrace();
          }
        }
      }
      else System.out.println("Zadani jpg fajl ne postoji ili vec postoji kopija!!!");
    }
    else System.out.println("Greska u nazivima fajlova!!!");
  }
  
  public String path()
  {
    return this.currentPath.getPath();
  }
  
   public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Jednostavni fajl sistem menadzer");
        File s = new File(System.getProperty("user.home"));
        FileSystem f = new FileSystem(s);

        boolean firstRun = true;
        String option="";
        
        do{           
           if (option.startsWith("md")) {
                String path = option.split(" ")[1];
                f.createDirectory(path);
            } else if (option.startsWith("cd")) {
                String path = option.split(" ")[1];
                f.changeDir(path);
            } else if (option.startsWith("del")) {
                String path = option.split(" ")[1];
                f.delete(path);
            } else if (option.startsWith("copy")) {
                String source = option.split(" ")[1];
                String dest = option.split(" ")[2];
               
                    f.copyImageFile(source, dest);
                
            } else if ("dir".equals(option)) {
                f.listFiles();
            } 
             else {
              if(!firstRun){
                System.out.println("Opcija ne postoji");                
              } else {
                firstRun = false;
              }
            }
             System.out.println("\nOpcije:\n"
                + "md name == kreiranje foldera sa proslijedjenim imenom\n"
                + "dir == prikaz sadrzaja direktorijuma\n"
                + "cd name == otvaranje drugog direktorijuma\n"
                + "del name == brisanje fajla ili direktorijuma\n"
                + "copy source destination == kopiranje jpg slika u istom folderu\n"
                + "word == prikaz svih word datoteka\n"
                + "quit == izlaz\n");
            System.out.print(">> " + f.path());
        }while (!"quit".equals(option = scan.nextLine()));

    }

  
}