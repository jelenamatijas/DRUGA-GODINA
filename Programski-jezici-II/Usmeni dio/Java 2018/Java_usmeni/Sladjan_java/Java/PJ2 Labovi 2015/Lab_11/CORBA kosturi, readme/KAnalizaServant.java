//implementiranje servanta

import java.lang.String;
import java.util.ArrayList;

public class AdvertismentServant {

  
  public void createAd(String s)
  {
    Ad a = new Ad(s.split("#")[0],s.split("#")[1],s.split("#")[2],s.split("#")[3]);
    
    synchronized(AdvertismentServer.adList)
    {
      AdvertismentServer.adList.add(a);
      
      ObjcetOutput out = new ObjectOutput(new FileOutputStream("OglasiCORBA"));
      
      out.writeObject(AdvertismentServer.adList);
      
      out.close();
      
    }
    
    
  }

}
