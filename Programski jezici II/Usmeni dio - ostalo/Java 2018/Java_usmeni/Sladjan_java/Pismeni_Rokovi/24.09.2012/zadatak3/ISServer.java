//implementiranje servera
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.util.ArrayList;

public class ISServer {
  
  
  
  public static void main(String[] args) {
    try {
      // inicijalizuj ORB, prosledi mu command-line parametre
      ORB orb = ORB.init(args, null);
    
      // Kreiraj servanta i registruj ga kod ORB-a
      ISServant reader = new ISServant();
      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootpoa.the_POAManager().activate();
org.omg.CORBA.Object ref=rootpoa.servant_to_reference(reader);
// pokupi osnovni Naming context
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      NamingContext ncRef = NamingContextHelper.narrow(objRef);

      // Registruj servant objekat kod naming servisa
      NameComponent nc = new NameComponent("IS", "");
      NameComponent path[] = {nc};
      ncRef.rebind(path, ref);
      
      // chekaj na pozive klijenata
      System.out.println("ISServer is waiting for clients...");
      java.lang.Object sync = new java.lang.Object();
      synchronized(sync){
        sync.wait();
      }

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
