//implementiranje klijenta
import org.omg.CORBA.*;
import org.omg.CosNaming.*;

import oglasi.*;
import oglasi.AdvertismentPackage.*;

public class AdvertismentClient {
  public static void main(String[] args) {
    try {
      // inicijalizuj ORB, prosledi mu command-line parametre
      ORB orb = ORB.init(args, null);
      
      // pokupi osnovni Naming context
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      NamingContext ncRef = NamingContextHelper.narrow(objRef);
// pokupi kanalizu
      NameComponent nc = new NameComponent("Advertisment", "");
      NameComponent path[] = {nc};
      Advertisment adv = AdvertismentHelper.narrow(ncRef.resolve(path));

      // koristi CORBA servant objekat
      adv.createAd("Marko#Markovic#Matematika#Ispit");

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
