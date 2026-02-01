import java.rmi.Remote;
import java.rmi.RemoteException;

interface OnlineShopInterface extends Remote {
	String prikazPodataka() throws RemoteException;
	boolean dodavanjeProizvoda(String sifra, String naziv, String opis, double cijena) throws RemoteException;
	double kupovina(String sifra, int kolicina) throws RemoteException;
}
