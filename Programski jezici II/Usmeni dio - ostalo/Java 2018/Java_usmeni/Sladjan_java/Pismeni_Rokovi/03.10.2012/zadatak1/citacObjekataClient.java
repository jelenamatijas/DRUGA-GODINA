import java.util.Scanner;
import java.net.*;
import java.io.*;
import java.util.Random;


public class citacObjekataClient
{
  public static void main(String[] args)
  {
    Scanner scan = new Scanner(System.in);
    Random rand = new Random();
    File f1 = new File("Folder1");
    File f2 = new File("Folder2");
    
    if (!f1.exists()) f1.mkdir();
    if (!f2.exists()) f2.mkdir();
    
    ;
    try
    {
      InetAddress ip;
      ip = InetAddress.getByName("127.0.0.1");
    
      
    
      DomaciFilm df1 = new DomaciFilm("Orlovi Rano Lete","1983:11:12","ROMAN","DRAMA");
      DomaciFilm df2 = new DomaciFilm("Balkanski Spijun","1974:1:4","ROMAN","KOMEDIJA");
      
      StraniFilm sf1 = new StraniFilm("Alien","1991:5:4","ROMAN","NAUCNA FANTASTIKA");
      StraniFilm sf2 = new StraniFilm("Beautiful Mind","1995:6:2","ISTINITA PRICA","DRAMA");
      
      try(
      ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(f1+File.separator+df1.name+".mv",true));
      ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(f1+File.separator+df2.name+".mv",true));
      ObjectOutputStream oos3 = new ObjectOutputStream(new FileOutputStream(f2+File.separator+sf1.name+".mv",true));
      ObjectOutputStream oos4 = new ObjectOutputStream(new FileOutputStream(f2+File.separator+sf2.name+".mv",true));
          )//zatvoren try with resources
      {
        oos1.writeObject(df1);
        oos2.writeObject(df2);
        oos3.writeObject(sf1);
        oos4.writeObject(sf2);
        
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
      
      
      try( 
           Socket sock = new Socket (ip,9000);
           BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
           PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())),true);
           OutputStream out = sock.getOutputStream();
          )
      {
        System.out.println(br.readLine());
        System.out.println(br.readLine());
        System.out.println(br.readLine());
        
        String request = new String();
        
        request = scan.nextLine();
        
        pw.println(request);
        
        if("1".equalsIgnoreCase(request))
        {
          pw.println(f1);//salje se ime direktorijuma
          
          int brojacFajlova = 0;
          String[] nizs;
          nizs = f1.list();
          
          for(String s : nizs) ++brojacFajlova;
          
          pw.println(brojacFajlova);//salje se broj fajlova
          
          File[] nizFajlova = f1.listFiles();
          
          for(File tempFile : nizFajlova)
          {
            br.readLine();
            pw.println(tempFile.getName());
            pw.println(tempFile.length());
            
            System.out.println(br.readLine());
            
            byte[] buffer = new byte[1024*1024];
            
            InputStream input = new FileInputStream(tempFile);
            
            int count = 0;
            
            while((count = input.read(buffer))>=0)
            {
              out.write(buffer,0,count);
              out.flush();
            }
            
          }
          
          br.readLine();
          System.out.println("Unesite tip objekta: ");
          request = scan.nextLine();
          pw.println(request);
          
          System.out.println(br.readLine());
        }//zatvoren if
        else
        {
          ObjectInputStream fromFile = new ObjectInputStream(new FileInputStream(f2+File.separator+"Beautiful Mind.mv"));
          Object o = fromFile.readObject();
          ObjectOutputStream oosNet = new ObjectOutputStream(sock.getOutputStream());
          oosNet.writeObject(o);
          System.out.println(br.readLine());
        }
        
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
      
      
      
    }
    catch(Exception ex)
    {
      System.out.println(ex);
      ex.printStackTrace();
    }
  }
  
}