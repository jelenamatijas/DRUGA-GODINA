//implementiranje klijenta
import org.omg.CORBA.*;
import org.omg.CosNaming.*;

import kriptoanaliza.*;
import kriptoanaliza.KAnaliza;
import kriptoanaliza.KAnalizaHelper;

public class KAnalizaClient {
  public static void main(String[] args) {
    try {
      // inicijalizuj ORB, prosledi mu command-line parametre
      ORB orb = ORB.init(args, null);
      
      // pokupi osnovni Naming context
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      NamingContext ncRef = NamingContextHelper.narrow(objRef);
// pokupi kanalizu
      NameComponent nc = new NameComponent("KAnaliza", "");
      NameComponent path[] = {nc};
      KAnaliza kAnaliza = KAnalizaHelper.narrow(ncRef.resolve(path));

      // koristi CORBA servant objekat
      System.out.println("KAnaliza returned: " + kAnaliza.vratiCezarovuSifru("dijana"));

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
