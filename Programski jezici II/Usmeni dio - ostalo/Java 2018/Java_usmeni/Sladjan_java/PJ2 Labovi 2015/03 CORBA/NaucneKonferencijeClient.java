import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import java.util.Scanner;
import java.io.*;

import pracenjenaucnihkonferencija.*;
import pracenjenaucnihkonferencija.NaucneKonferencijePackage.*;

public class NaucneKonferencijeClient {
  public static void main(String[] args) {
    try {
      // inicijalizuj ORB, prosledi mu command-line parametre
      ORB orb = ORB.init(args, null);
      
      // pokupi osnovni Naming context
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      NamingContext ncRef = NamingContextHelper.narrow(objRef);
      // pokupi kanalizu
      NameComponent nc = new NameComponent("NaucneKonferencije", "");
      NameComponent path[] = {nc};
      NaucneKonferencije nKonf = NaucneKonferencijeHelper.narrow(ncRef.resolve(path));
      // koristi CORBA servant objekat
      Scanner ulaz = new Scanner(System.in);
      System.out
        .println("Dobro dosli u sistem za pracenje naucnih konferencija! Izaberite opciju:");
      System.out.println("***************************");
      System.out.println("1 - dodavanje rada");
      System.out.println("2 - upload rada");
      System.out.println("3 - spisak radova");
      System.out.println("4 - izlaz");
      
      String ulazKorisnika = "";
      while ((ulazKorisnika = ulaz.nextLine()) != null) {
        if (ulazKorisnika.equals("1")) {
          Rad rad=new Rad();
          Scanner citac = new Scanner(System.in);
          System.out.println("Unesite naziv rada:");
          rad.naziv=citac.nextLine();
          System.out.println("Unesite kratak sadrzaj rada:");
          rad.kratakSadrzaj=citac.nextLine();
          System.out.println("Unesite id rada:");
          rad.idRada=citac.nextInt();
          System.out.println("Unesite broj stranica rada:");
          rad.brojStranica=citac.nextInt();
          boolean ok=nKonf.dodavanjeRada(rad);
          System.out.println("Zavrsen unos rada: "+ok);
        }
        else if(ulazKorisnika.equals("3")){
          System.out.println("Spisak trenutno prijavljenih radova:");
          Radovi radovi=new Radovi();
          radovi=nKonf.pregledRadova();
          System.out.println("duzina "+ radovi.radovi.length);
          for(int i=0; i<radovi.radovi.length; i++){
            if(radovi.radovi[i]!=null){
              System.out.println(i+" "+"Naziv rada: "+radovi.radovi[i].naziv+"\n Kratak sadrzaj: "+radovi.radovi[i].kratakSadrzaj+"\n Broj stranica: "+radovi.radovi[i].brojStranica);
              System.out.println("********************************");
            }
          }
        }else if(ulazKorisnika.equals("2")){
          try{
            //unos naziva fajla
            System.out.println("Unesite putanju do fajla koji zelite uploadovati!");
            Scanner citac = new Scanner(System.in);
            String putanja=citac.nextLine();
            File fajlZaUpload=new File(putanja);
            //ocitavanje sadrzaja kao byte
            FileInputStream fin = new FileInputStream(fajlZaUpload);
            byte sadrzajFajla[] = new byte[(int)fajlZaUpload.length()];
            fin.read(sadrzajFajla);
            fin.close();
            //konverzija u String cisto za testiranje...
            String sadrzaj = new String(sadrzajFajla);
            System.out.println("Ocitano : ");
            System.out.println(sadrzaj);
            //kreiranje objekta RadZaUpload
            RadZaUpload rad=new RadZaUpload();
            //podesavanje atributa
            rad.naziv=putanja.substring(putanja.lastIndexOf("\\")+1, putanja.length());
            System.out.println(rad.naziv);
            rad.sadrzaj=sadrzajFajla;
            //poziv metode za upload
            System.out.println("Uspjesan upload: "+nKonf.uploadRada(rad));
          }catch(Exception ex){
            ex.printStackTrace();
          }
        }else if(ulazKorisnika.equals("4")){
          System.exit(0);
        }else{
          System.out.println("POGRESNA KOMANDA!!!");
        }
        
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}