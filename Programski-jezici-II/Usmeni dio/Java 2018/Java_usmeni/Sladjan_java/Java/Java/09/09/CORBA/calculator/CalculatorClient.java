import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import java.util.*;
import calculator.*;

public class CalculatorClient {
  public static void main(String[] args) {
    try {
      // inicijalizuj ORB, proslijedi mu command-line parametre
      ORB orb = ORB.init(args, null);
      
      // pokupi osnovni Naming context
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      // umjesto klasicnog kastovanja, ovde se poziva narrow()
      NamingContext ncRef = NamingContextHelper.narrow(objRef);
      
      // definiši name component
      NameComponent nc = new NameComponent("Calculator", "");
      NameComponent path[] = {nc};
      // pronadji objekat pod nazivom "counter" i kastuj ga pomocu narrow()
      Calculator calculator = CalculatorHelper.narrow(ncRef.resolve(path));
      Scanner in = new Scanner(System.in);
      System.out.print("Unesi prvi broj: ");
      int o1 = in.nextInt();
      System.out.print("Unesi drugi broj: ");
      int o2 = in.nextInt();
      // koristi CORBA servant objekat
      System.out.println("Zbir: " + calculator.add(o1, o2));
      System.out.println("Razlika: " + calculator.sub(o1, o2));

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}