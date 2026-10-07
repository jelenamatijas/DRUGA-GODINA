import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class TuristickaAgencijaClient {
  
  /**
   * @param args
   * @throws RemarshalException
   * @throws NotBoundException
   * @throws RemoteException
   * @throws MalformedURLException
   */
  public static void main(String[] args) throws MalformedURLException,
    RemoteException, NotBoundException {
    
    // kreiramo RMISecurityManager
    //System.setSecurityManager(new RMISecurityManager());
    // trazimo server po imenu na adresi i portu na kom trenutno osluskuje
    TuristickaAgencijaInterface t = (TuristickaAgencijaInterface) Naming
      .lookup("//localhost:1099/TuristickaAgencija");
    
    Scanner ulaz = new Scanner(System.in);
    System.out
      .println("Dobro dosli u Turisticku agenciju! Izaberite opciju:");
    System.out.println("***************************");
    System.out.println("1 - dodavanje ponude");
    System.out.println("2 - dodavanje korisnika");
    System.out.println("3 - spisak ponuda");
    System.out.println("4 - rezervacija");
    System.out.println("5 - izlaz");
    
    String ulazKorisnika = "";
    while ((ulazKorisnika = ulaz.nextLine()) != null) {
      if (ulazKorisnika.equals("1")) {
        TuristickaPonuda ponuda = new TuristickaPonuda();
        t.dodavanjePonude(ponuda);
      } else if (ulazKorisnika.equals("2")) {
        Korisnik korisnik = new Korisnik();
        t.dodavanjeKorisnika(korisnik);
      } else if (ulazKorisnika.equals("3")) {
        ArrayList<TuristickaPonuda> ponude = t.spisakPonuda();
        System.out.println("Spisak ponuda");
        for (int i = 0; i < ponude.size(); i++) {
          System.out.println("ID: " + ponude.get(i).getIdPonude());
          System.out.println("Naziv: " + ponude.get(i).getNaziv());
          System.out.println("Mjesto: " + ponude.get(i).getMjesto());
          System.out
            .println("Vrijeme: " + ponude.get(i).getVrijeme());
          System.out.println("Cijena: " + ponude.get(i).getCijena());
          System.out.println("***************************");
          System.out.println();
        }
        
      } else if (ulazKorisnika.equals("4")) {
        System.out.println("Unesite vas id:");
        Scanner citac = new Scanner(System.in);
        int idKorisnika = citac.nextInt();
        System.out.println("Unesite id ponude:");
        int idPonude = citac.nextInt();
        System.out.println("Rezultat dodavanja ponude: "
                             + t.rezervisiPonudu(idPonude, idKorisnika));
      } else if (ulazKorisnika.equals("5")) {
        System.exit(0);
      } else {
        System.out.println("POGRESNA KOMANDA!!!");
      }
    }
  }
}
