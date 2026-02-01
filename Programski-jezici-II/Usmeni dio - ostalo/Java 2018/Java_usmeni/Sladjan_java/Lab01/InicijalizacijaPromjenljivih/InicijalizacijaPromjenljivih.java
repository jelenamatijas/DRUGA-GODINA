public class InicijalizacijaPromjenljivih
{
  public static void main (String args[])
  {
    int vrijednost = 0, duplaVrijednost = 0;
    double kolicnikSaDva = 0;
    char znakPodatka = '\0';
    
    vrijednost = 7;
    duplaVrijednost = 2* vrijednost;
    
    kolicnikSaDva = (double)vrijednost / (double)2;
    
    znakPodatka = 'S';
    
    System.out.println("Vrijednost = " + vrijednost + ", duplaVrijednost = " + duplaVrijednost +
                       ", kolicnikSaDva = " + kolicnikSaDva + ", znakPodatka = " + znakPodatka);
  }
  
  
}