import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.net.*;
import java.util.*;

public class CalculatorClient {
  public static void main(String[] args) {
	 if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
  	try {
  	            String host = (args.length < 1) ? null : args[0];
            Registry registry = LocateRegistry.getRegistry(host);
            CalculatorInterface server = (CalculatorInterface) registry.lookup("Server");
	    Scanner in = new Scanner(System.in);
      	    System.out.print("Unesi prvi broj: ");
      	    int o1 = in.nextInt();
      	    System.out.print("Unesi drugi broj: ");
      	    int o2 = in.nextInt();
      	    System.out.println("Zbir: " + server.add(o1, o2));
      	    System.out.println("Razlika: " + server.sub(o1, o2));
        } catch (Exception e) {
            e.printStackTrace();
        }
  }
}
