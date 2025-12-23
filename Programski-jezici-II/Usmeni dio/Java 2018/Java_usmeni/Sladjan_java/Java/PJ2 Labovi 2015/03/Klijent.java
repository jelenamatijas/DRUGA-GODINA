public class Klijent{
  private String ime;
  private String prezime;
  private String jmbg;
  private double stanjeNaRacunu;
  
  public Klijent(){
  }

  public Klijent(String ime, String prezime, String jmbg, double stanjeNaRacunu){
    this.ime = ime;
    this.prezime = prezime;
    this.jmbg = jmbg;
    this.stanjeNaRacunu = stanjeNaRacunu;
  }
  
  public void umanjiStanje(double vrijednost) throws StanjeNaRacunuException{
    System.out.println("Klijentu " + ime + " " + prezime + " umanjuje se stanje na racunu za " + vrijednost);
     if(stanjeNaRacunu < vrijednost) 
       throw new StanjeNaRacunuException();
     else
       stanjeNaRacunu -=vrijednost;
    
  }
  
  @Override
  public String toString(){
    return "Klijent: " + ime + " " + prezime + " JMBG: " + jmbg + " ima na racunu: " + stanjeNaRacunu;
  }
}