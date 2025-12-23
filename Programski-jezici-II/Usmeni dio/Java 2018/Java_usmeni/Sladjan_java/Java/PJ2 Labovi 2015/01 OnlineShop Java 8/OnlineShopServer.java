import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;

public class OnlineShopServer implements OnlineShopInterface {

	public OnlineShopServer() throws RemoteException{
	
	}
	
	public String prikazPodataka() throws RemoteException {
		String rezultat = "";
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"proizvodi.txt"));
			String s;
			while ((s = in.readLine()) != null) {				
				rezultat += s.replaceAll("#", " ")+"\n";
			}
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rezultat;
	}

	public boolean dodavanjeProizvoda(String sifra, String naziv, String opis,
			double cijena) throws RemoteException {
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(
					"proizvodi.txt", true));
			pw.println(sifra + "#" + naziv + "#" + opis + "#" + cijena);
			pw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public double kupovina(String sifra, int kolicina) throws RemoteException {
		double rezultat = 0;
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"proizvodi.txt"));
			String s;
			while ((s = in.readLine()) != null) {
				if (s.split("#")[0].equals(sifra)) {
					rezultat = kolicina * Double.parseDouble(s.split("#")[3]);
				}
			}
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rezultat;
	}

	public static void main(String args[]) {
		if (System.getSecurityManager() == null) {
          		System.setSecurityManager(new SecurityManager());
     		}
		try {
			OnlineShopServer obj = new OnlineShopServer();
			OnlineShopInterface stub = (OnlineShopInterface) UnicastRemoteObject.exportObject(obj, 0);
			Registry registry = LocateRegistry.getRegistry();
            	 	registry.rebind("ServerOBJ", stub);
			System.out.println("Server started.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
