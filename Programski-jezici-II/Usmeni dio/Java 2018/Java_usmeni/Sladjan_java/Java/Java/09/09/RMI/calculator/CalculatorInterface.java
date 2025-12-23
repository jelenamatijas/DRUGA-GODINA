import java.rmi.Remote;
import java.rmi.RemoteException;

interface CalculatorInterface extends Remote {
	int add(int x, int y) throws RemoteException;
	int sub(int x, int y) throws RemoteException;
}
