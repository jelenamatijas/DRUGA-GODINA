/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package labio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Milan
 */
public class LabIO 
{

    /**
     * @param args the command line arguments
     */
    private static final String PATH = "D:\\Milan\\NAUKA\\2014\\Java\\net\\2.put\\LabIO\\";
    
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
    
    public static void main(String[] args) 
    {/*
        // TODO code application logic here
        double racunari=0;
        double monitori=0;
        double telefoni=0;
        
        
        try
        {
            PrintWriter outRacunari = new PrintWriter(new BufferedWriter(new FileWriter(PATH+"prodajaRacunara.txt")));
            PrintWriter outMonitori = new PrintWriter(new BufferedWriter(new FileWriter(PATH+"prdajaMonitori.txt")));
            PrintWriter outTelefoni = new PrintWriter(new BufferedWriter(new FileWriter (PATH+"prodajaTelefoni.txt")));
            
            BufferedReader in = new BufferedReader(new FileReader(PATH+"prodaja.txt"));
            
            String s,pom[];
            while((s = in.readLine()) != null)
            {
                pom = s.split("#");
                
                if(pom[0].equals("racunar"))
                {
                    racunari += Integer.parseInt(pom[2]) * Double.parseDouble(pom[3]);
                    outRacunari.println(s);
                }else if (pom[0].equals("monitor"))
                {
                    monitori += Integer.parseInt(pom[2]) * Double.parseDouble(pom[3]);
                    outMonitori.println(s);
                }else if (pom[0].equals("telefon"))
                {
                    telefoni += Integer.parseInt(pom[2]) * Double.parseDouble(pom[3]);
                    outTelefoni.println(s);
                }
             }
             outRacunari.close();
             outMonitori.close();
             outTelefoni.close();
             in.close();
             
             PrintWriter sumarno = new PrintWriter(new BufferedWriter(new FileWriter(PATH+"sumarno.txt")));
             sumarno.println("Mnitora je prodano: "+monitori+" a racunara "+racunari);
             sumarno.close();
          }catch(Exception ex){
            ex.printStackTrace();
        }
    */
    
        Scanner scan = new Scanner(System.in);
        System.out.println("    JEDNOSTAVNI FILE SISTEM");
        currentPath = new File(System.getProperty("user.home"));

        System.out.println("Opcije:\n"
                    + "md name == kreiranje foldera sa proslijedjenim imenom\n"
                    + "dir == prikaz sadrzaja direktorijuma\n"
                    + "cd name == otvaranje drugog direktorijuma\n"
                    + "del name == brisanje fajla ili direktorijuma\n"
                    + "copy source destination == kopiranje jpg slika u istom folderu\n"
                    + "word == prikaz svih word datoteka\n"
                    + "quit == izlaz");

        String opcija;
        System.out.println(">>" + currentPath);
        while (!(opcija=scan.nextLine()).equals("quit"))
        {
            if (opcija.startsWith("md")) createDicercory(opcija.split(" ")[1]);
            else
            if (opcija.startsWith("dir")) listFiles();
            else
            if (opcija.startsWith("cd")) changeDir(opcija.split(" ")[1]);
            else
            if (opcija.startsWith("del")) delete(opcija.split(" ")[1]);
            else
            if (opcija.startsWith("word")) listWordDoc();
            else
            try
            {
            if (opcija.startsWith("copy")) copyImageFile(opcija.split(" ")[1], opcija.split(" ")[2]);
            //popraviti - stalno se izvrsava
            /*
            else if (option.startsWith("copy")) {
                String source = option.split(" ")[1];
                String dest = option.split(" ")[2];
                try {
                    copyImageFile(source, dest);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            */
            else System.out.println("Opcija ne postiji");
            }catch (Exception ex){ex.printStackTrace();}
            
        }
    }
    
}
