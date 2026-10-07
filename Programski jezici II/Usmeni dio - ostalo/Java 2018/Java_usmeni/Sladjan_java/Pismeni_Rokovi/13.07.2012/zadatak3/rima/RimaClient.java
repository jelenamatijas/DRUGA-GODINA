import java.rmi.*;
import java.rmi.registry.*;
import java.io.*;
import java.util.ArrayList;



public class RimaClient
{
  public static void main(String[] args)
  {
    try
    {
      RimaInterface ri = (RimaInterface) Naming.lookup("//localhost:1099/server");
      
      BufferedReader br = new BufferedReader(new FileReader("pjesma.txt"));
      
      String citanje = new String();
      
      String[] pjesma;
      
      ArrayList<String> list = new ArrayList<String>();
      
      while((citanje= br.readLine()) != null)
      {
        list.add(citanje);
      }
      
      pjesma = new String[list.size()];
      
      System.out.println("PJESMA");
      System.out.println("");
      for (int i=0;i<list.size();i++)
      {
        pjesma[i] = list.get(i);
        System.out.println(pjesma[i]);
      }
      
      
      System.out.println("");
      System.out.println("=====================================");
      System.out.println("");
      
      System.out.println(ri.rima(pjesma));
      
      
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
    }
    
  }
  
  
}
      
      
      
      