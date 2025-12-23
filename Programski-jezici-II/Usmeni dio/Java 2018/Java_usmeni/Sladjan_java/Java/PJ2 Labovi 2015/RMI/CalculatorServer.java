import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorServer implements CalculatorInterface {

	public CalculatorServer() throws RemoteException {
		super();
	}

	public int add(int x, int y) throws RemoteException {
		return x + y;
	}

	public int sub(int x, int y) throws RemoteException {
		return x - y;
	}

	public static void main(String args[]){
 		if (System.getSecurityManager() == null) {
          		System.setSecurityManager(new SecurityManager());
     		}
 		try {
           	 CalculatorServer obj = new CalculatorServer();
           	 CalculatorInterface stub = (CalculatorInterface) UnicastRemoteObject.exportObject(obj, 0);

            	 // Bind the remote object's stub in the registry
            	 Registry registry = LocateRegistry.getRegistry();
            	 registry.bind("Server", stub);

           	 System.err.println("Server started");
        	} catch (Exception ex) {
           	   ex.printStackTrace();
       		}
	  	
	}
}
