import java.util.Properties;

public class SistemskeOsibine{
  public static void main (String[] args){
    String nizOsobina[] = {"os.name","java.version"};
    Properties osobine = System.getProperties();
    for (String kljuc: nizOsobina){
    String vrijednost = osobine.getProperty(kljuc);
    System.out.println(kljuc + "=" + vrijednost);
    
    }
  }
}