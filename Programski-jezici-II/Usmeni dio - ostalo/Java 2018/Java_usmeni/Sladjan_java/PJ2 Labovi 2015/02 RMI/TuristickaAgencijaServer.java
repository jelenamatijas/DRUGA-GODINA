import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class TuristickaAgencijaServer extends UnicastRemoteObject implements 
  TuristickaAgencijaInterface {
  
  public ArrayList<TuristickaPonuda> ponude = new ArrayList<>();
  public ArrayList<Korisnik> korisnici = new ArrayList<>();
  
  //key id ponude, value id korisnika
  public HashMap<Integer, Integer> rezervacije = new HashMap<Integer, Integer>();
  
  protected TuristickaAgencijaServer() throws RemoteException {
    super();
  }
  
  @Override
  public boolean dodavanjePonude(TuristickaPonuda ponuda)
    throws RemoteException {
    this.ponude.add(ponuda);
    return true;
  }
  
  @Override
  public ArrayList<TuristickaPonuda> spisakPonuda() throws RemoteException {
    return ponude;
  }
  
  @Override
  public boolean dodavanjeKorisnika(Korisnik korisnik)
    throws RemoteException {
    this.korisnici.add(korisnik);
    return true;
  }
  
  
  @Override
  public boolean rezervisiPonudu(int idPonude, int idKorisnika)
    throws RemoteException {
    boolean isOk=false;
    if(!rezervacije.containsKey(idPonude)){     
      rezervacije.put(idPonude, idKorisnika);
      isOk=true;
    }
    return isOk;
  }
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    // kreiramo RMISecurityManager
    //System.setSecurityManager(new RMISecurityManager());
    // instanciramo serverski objekat
    TuristickaAgencijaServer pt;
    try {
      pt = new TuristickaAgencijaServer();
      // startamo server na localhost-u na portu 1099 sa imenom koje ce
      // klijent koristiti pri njegovom pozivu
      Naming.rebind("//localhost:1099/TuristickaAgencija", pt);
      System.out.println("Ready to work...");
    } catch (RemoteException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  
}
