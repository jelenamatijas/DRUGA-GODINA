import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface TuristickaAgencijaInterface extends Remote {
 
 //dodavanje ponude
 boolean dodavanjePonude(TuristickaPonuda ponuda) throws RemoteException;
 
 //spisak ponuda
 ArrayList<TuristickaPonuda> spisakPonuda() throws RemoteException;
 
 //dodavanje korisnika
 boolean dodavanjeKorisnika(Korisnik korisnik) throws RemoteException;
 
 //rezervacija
 boolean rezervisiPonudu(int idPonude, int idKorisnika) throws RemoteException;

}
