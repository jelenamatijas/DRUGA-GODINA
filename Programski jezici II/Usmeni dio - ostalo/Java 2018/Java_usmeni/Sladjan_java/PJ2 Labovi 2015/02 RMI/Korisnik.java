import java.io.Serializable;
import java.util.Scanner;

public class Korisnik implements Serializable {
  
  private Integer registracioniBroj;
  private String ime;
  private String prezime;
  private Integer novacNaRacunu;
  
  public Korisnik() {
    super();
    Scanner ulaz = new Scanner(System.in);
    System.out.println("Unesite podatke o korisniku!");
    System.out.println("Ime:");
    this.ime = ulaz.nextLine();
    System.out.println("Prezime:");
    this.prezime = ulaz.nextLine();
    System.out.println("Novac na racunu:");
    this.novacNaRacunu = ulaz.nextInt();
    System.out.println("Registracioni broj:");
    this.registracioniBroj = ulaz.nextInt();
    System.out.println("Unos podataka zavrsen!");
  }
  
  public Korisnik(Integer registracioniBroj, String ime,
                              String prezime, Integer novacNaRacunu) {
    super();
    this.registracioniBroj = registracioniBroj;
    this.ime = ime;
    this.prezime = prezime;
    this.novacNaRacunu = novacNaRacunu;
  }
  
  public Integer getRegistracioniBroj() {
    return registracioniBroj;
  }
  
  public void setRegistracioniBroj(Integer registracioniBroj) {
    this.registracioniBroj = registracioniBroj;
  }
  
  public String getIme() {
    return ime;
  }
  
  public void setIme(String ime) {
    this.ime = ime;
  }
  
  public String getPrezime() {
    return prezime;
  }
  
  public void setPrezime(String prezime) {
    this.prezime = prezime;
  }
  
  public Integer getNovacNaRacunu() {
    return novacNaRacunu;
  }
  
  public void setNovacNaRacunu(Integer novacNaRacunu) {
    this.novacNaRacunu = novacNaRacunu;
  }
  
}
