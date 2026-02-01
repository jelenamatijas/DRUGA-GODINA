import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;
import java.util.*;

public class OnlineShopClient {
	public static void main(String[] args) {
		System.setSecurityManager(new RMISecurityManager());
		try {
			OnlineShopInterface server = (OnlineShopInterface) Naming
					.lookup("//localhost:1099/Server");
			Scanner in = new Scanner(System.in);
			String opcija = null;
			while (!"4".equals(opcija)) {
				System.out.println("Izaberite opciju: ");
				System.out.println("1 - Pregled proizvoda");
				System.out.println("2 - Dodavanje proizvoda");
				System.out.println("3 - Kupovina proizvoda");
				System.out.println("4 - Kraj rada");

				opcija = in.nextLine();

				switch (opcija) {
				case "1":
					System.out.println("Pregled proizvoda:");
					System.out.println(server.prikazPodataka());
					break;
				case "2":
					System.out.println("Dodavanje proizvoda:");
					System.out.print("Sifra:");
					String sifra = in.nextLine();
					System.out.print("Naziv:");
					String naziv = in.nextLine();
					System.out.print("Opis:");
					String opis = in.nextLine();
					System.out.print("Cijena:");
					double cijena = Double.parseDouble(in.nextLine());
					server.dodavanjeProizvoda(sifra, naziv, opis, cijena);
					break;
				case "3":
					System.out.println("Kupovina proizvoda:");
					System.out.print("Sifra:");
					String sifrap = in.nextLine();
					System.out.print("Kolicina:");
					int kolicina = Integer.parseInt(in.nextLine());
					System.out.println(server.kupovina(sifrap, kolicina));
					break;
				case "4":
					break;
				default:
					System.out.println("Izabrana opcija ne postoji!");
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}