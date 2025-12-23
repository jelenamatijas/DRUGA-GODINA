import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.*;

import calculator.*;

public class CalculatorServer {
  public static void main(String[] args) {
    try {
      ORB orb = ORB.init(args, null);
      
      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootpoa.the_POAManager().activate();

      CalculatorServant countRef = new CalculatorServant();

      org.omg.CORBA.Object ref = rootpoa.servant_to_reference(countRef);
      Calculator href = CalculatorHelper.narrow(ref);
      
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      NamingContext ncRef = NamingContextHelper.narrow(objRef);

      NameComponent nc = new NameComponent("Calculator", "");
      NameComponent path[] = {nc};
      ncRef.rebind(path, href);
      
      System.out.println("Waiting for clients");
			orb.run();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
