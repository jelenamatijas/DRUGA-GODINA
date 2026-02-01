import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Partner {

 private String nazivKompanije;
 private String vlasnikKompanije;
 private String adresa;
 private String brojTelefona;
 private String grad;

 public String getNazivKompanije() {
  return nazivKompanije;
 }

 public void setNazivKompanije(String nazivKompanije) {
  this.nazivKompanije = nazivKompanije;
 }

 public String getVlasnikKompanije() {
  return vlasnikKompanije;
 }

 public void setVlasnikKompanije(String vlasnikKompanije) {
  this.vlasnikKompanije = vlasnikKompanije;
 }

 public String getAdresa() {
  return adresa;
 }

 public void setAdresa(String adresa) {
  this.adresa = adresa;
 }

 public String getBrojTelefona() {
  return brojTelefona;
 }

 public void setBrojTelefona(String brojTelefona) {
  this.brojTelefona = brojTelefona;
 }

 public String getGrad() {
  return grad;
 }

 public void setGrad(String grad) {
  this.grad = grad;
 }

 public void unosPartnera() {
  System.out.println("UNOS PODATAKA O PARTNERU");
  Scanner citac = new Scanner(System.in);
  System.out.println("Naziv kompanije:");
  nazivKompanije = citac.nextLine();
  System.out.println("Vlasnik kompanije:");
  vlasnikKompanije = citac.nextLine();
  System.out.println("Adresa:");
  adresa = citac.nextLine();
  System.out.println("Grad:");
  grad = citac.nextLine();
  System.out.println("Broj telefona:");
  brojTelefona = citac.nextLine();
 }

 public String partnerKaoString() {
  return "\n" + nazivKompanije + "#" + vlasnikKompanije + "#" + adresa
    + "#" + brojTelefona + "#" + grad;
 }

 public void upisURegistartor(String partner) {
  try {
   PrintWriter pisac = new PrintWriter(new FileWriter(
     "registrator.txt", true));
   pisac.write(partner);
   pisac.close();
  } catch (IOException e) {
   System.out.println("IOException!!!");
  }
 }

 public static ArrayList<Partner> listaPartnera() {
  ArrayList<Partner> partneri = new ArrayList<Partner>();
  try {
   BufferedReader citac = new BufferedReader(new FileReader(
     "registrator.txt"));
   String linija = "";
   Partner partner;
   while ((linija = citac.readLine()) != null) {
    String lin[] = linija.split("#");
    partner = new Partner();
    partner.setNazivKompanije(lin[0]);
    partner.setVlasnikKompanije(lin[1]);
    partner.setAdresa(lin[2]);
    partner.setBrojTelefona(lin[3]);
    partner.setGrad(lin[4]);
    partneri.add(partner);
   }
   citac.close();
  } catch (FileNotFoundException e) {
   System.out.println("FAJL NIJE PRONADJEN!");
  } catch (IOException e) {
   System.out.println("IoE");
  }
  return partneri;
 }

 // test Partner
 public static void main(String args[]) {
  /*
   * for (int i = 0; i < 5; i++) { Partner partner = new Partner();
   * partner.unosPartnera();
   * partner.upisURegistartor(partner.partnerKaoString()); }
   */
  ArrayList<Partner> partneriIzFajla = Partner.listaPartnera();
  for (int i = 0; i < partneriIzFajla.size(); i++) {
   System.out.println(partneriIzFajla.get(i).getNazivKompanije());
  }
 }
}
