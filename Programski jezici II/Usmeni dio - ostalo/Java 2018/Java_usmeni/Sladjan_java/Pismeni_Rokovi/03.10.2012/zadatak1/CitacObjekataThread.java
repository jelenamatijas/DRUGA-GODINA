import java.io.*;
import java.net.*;


public class CitacObjekataThread extends Thread
{
  public Socket sock;
 
  
  public CitacObjekataThread() {};
  
  public CitacObjekataThread(Socket s)
  {
    this.sock = s;
      
    start();
  }
  
  public void run()
  {
    String request = new String();
    
    try(BufferedReader br = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.sock.getOutputStream())),true);
        InputStream in = this.sock.getInputStream();
        OutputStream out = this.sock.getOutputStream();
        
        )
    {
      pw.println("OPCIJE:");
      pw.println("1. Ocitavanje objekta iz serijalizovanog fajla");
      pw.println("2. Provjera karakteristika objekta");
      
      request = br.readLine();
      
      if ("1".equalsIgnoreCase(request))
      {
        String directoryName = new String();
        String newFileName = new String();
        int numberOfFiles;
        
        directoryName = br.readLine();//prvo se salje ime foldera
        System.out.println(directoryName);
        File f = new File("new"+directoryName);
        
        if(!f.exists()) f.mkdir();
        
        request = br.readLine();//drugo salje se broj fajlova
        
        numberOfFiles = Integer.valueOf(request);
        
        System.out.println(numberOfFiles);
        
        for(int i=0;i<numberOfFiles;i++)
        {
          pw.println("##SEND##");
          int fileSize = 0;
          
          newFileName = br.readLine();//trece, salje se ime fajla svakog zasebno
          System.out.println(newFileName);
          
          request = br.readLine();//cetvrto, salje se velicina svakog fajla pa se onda prelazi na slanje bajtova
          
          fileSize = Integer.valueOf(request);
          System.out.println(request);
          
          pw.println("##SEND##");
          
          System.out.println("Poslao SEND");
          int count = 0;
        
          byte[] buffer = new byte[1024 * 2014];
          
          OutputStream toFile = new FileOutputStream(f+File.separator+newFileName);
          File newFile = new File(f+File.separator+newFileName);
          
          do
          {
            count = in.read(buffer);
            toFile.write(buffer,0,count);
            toFile.flush();
          }
          while (newFile.length()<fileSize);
          
          
          toFile.close();
          
         
          System.out.println("Upisao");
          
          
        }
        
        pw.println("##SENDTYPE##");
        
        request = br.readLine();
        
        String type = request;
        
        boolean validType = false;
        
         File[] fileList = f.listFiles();
         
        for(File tempFile : fileList)
        {
          ObjectInputStream nois = new ObjectInputStream(new FileInputStream(tempFile));
          Object o = nois.readObject();
          
          if(type.equalsIgnoreCase(o.getClass().getName())) 
          {
            pw.println(o.toString());
            validType = true;
          }
          else continue;
        }
          
          if(!validType) pw.println("Tip koji ste poslali nije medju primljenim objektima!!");
        
      }
      else
      {
        ObjectInputStream ois = new ObjectInputStream(this.sock.getInputStream());
        Object o = ois.readObject();
        String response = new String();
        
        response += o.getClass().getName() + " " + o.hashCode() + " ";
        response += o.getClass().getSuperclass().getName() ;
        
        Class<?>[] nizInterfejsa = o.getClass().getInterfaces();
        
        for(Class<?> k : nizInterfejsa)
        {
          response += " ";
          response += k.getName();
          
        }
        
        System.out.println(response);
        
        pw.println(response);
        
      }
      
      
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    
  }
  
}
    