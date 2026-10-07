/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package labio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Milan
 */
public class Main {
    private static File currentPath;
    
    //changeDir
    public static void changeDir(String name)
    {
        if("..".equals(name))
        currentPath = currentPath.getParentFile();
        else
        {
            File f = new File(currentPath.getPath() + File.separator + name);
            currentPath = f.exists() ? f : currentPath;
        }
     System.out.println(">> " + currentPath);
    }
    
    //listFiles
    public static void listFiles()
    {
        System.out.println(currentPath);
        
        File[] list = currentPath.listFiles();
        //File pom;
        for(File pom : list)
        {
            if(pom.isDirectory())
                System.out.println("<DIR>" + pom.getName());
            else if(pom.isFile())
                System.out.println(pom.getName());
        }
    }
    
    //createDirectory
    public static void createDicercory(String name)
    {
        File f = new File(currentPath.getPath() + File.separator + name);
        if (f.exists())
            System.out.println("Direktorijum vec postoji");
        else 
        {
            f.mkdir();
            System.out.println("Kreiran je direktorijum "+name);
        }
     }
    
    //delete
    public static void delete(String name)
    {
        File f = new File(currentPath.getPath() + File.separator + name);
        if (f.exists()) f.delete();
        else System.out.println("Fajl " + name + "ne postoji!");
    }
    
    //copyImageFile
    public static void copyImageFile(String sourceName, String destinationName)throws IOException
    {
        if (sourceName.endsWith("jpg") && destinationName.endsWith("jpg") && sourceName != destinationName)
        {
            File src = new File(currentPath.getPath() + File.separator + sourceName);
            File dest = new File(currentPath.getPath() + File.separator + destinationName);
            if (!src.exists()) 
            {
                System.out.println("Ne postoji orginalna slika");
                return;
            }
            try
            {
                FileInputStream in = new FileInputStream(src);
                FileOutputStream out = new FileOutputStream(dest);
                int c;
                while((c = in.read()) != -1)
                    out.write(c);
                if (in != null) in.close();
                if (out != null) out.close();
            }catch (Exception ex){ex.printStackTrace();}
        }
    }
    
    //listWordDoc
    public static void listWordDoc()
    {
        File f = new File(currentPath.getPath());
        File list[] = f.listFiles();
        for (File pom : list)
        {
            if (pom.getName().endsWith("doc") || pom.getName().endsWith("docx"))
                System.out.println(pom.getName());
        }
    }
    
}
