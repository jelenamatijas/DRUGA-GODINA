import java.util.Scanner;
import java.io.Serializable;

public class TuristickaPonuda implements Serializable{
  
  private Integer idPonude;
  private String naziv;
  private String mjesto;
  private String vrijeme;
  private Integer cijena;
  
  public TuristickaPonuda(){
    Scanner ulaz=new Scanner(System.in);
    System.out.println("Unesite podatke o turistickoj ponudi!");
    System.out.println("Naziv:");
    this.naziv=ulaz.nextLine();
    System.out.println("Mjesto:");
    this.mjesto=ulaz.nextLine();
    System.out.println("Vrijeme:");
    this.vrijeme=ulaz.nextLine();
    System.out.println("Cijena:");
    this.cijena=ulaz.nextInt();
    System.out.println("Id ponude:");
    this.idPonude=ulaz.nextInt();
    System.out.println("Zavrsen unos podataka!");
  }
  
  public TuristickaPonuda(Integer idPonude, String naziv, String mjesto,
                          String vrijeme, Integer cijena) {
    super();
    this.idPonude = idPonude;
    this.naziv = naziv;
    this.mjesto = mjesto;
    this.vrijeme = vrijeme;
    this.cijena = cijena;
  }
  
  public Integer getIdPonude() {
    return idPonude;
  }
  
  public void setIdPonude(Integer idPonude) {
    this.idPonude = idPonude;
  }
  
  public String getNaziv() {
    return naziv;
  }
  
  public void setNaziv(String naziv) {
    this.naziv = naziv;
  }
  
  public String getMjesto() {
    return mjesto;
  }
  
  public void setMjesto(String mjesto) {
    this.mjesto = mjesto;
  }
  
  public String getVrijeme() {
    return vrijeme;
  }
  
  public void setVrijeme(String vrijeme) {
    this.vrijeme = vrijeme;
  }
  
  public Integer getCijena() {
    return cijena;
  }
  
  public void setCijena(Integer cijena) {
    this.cijena = cijena;
  }
  
}
