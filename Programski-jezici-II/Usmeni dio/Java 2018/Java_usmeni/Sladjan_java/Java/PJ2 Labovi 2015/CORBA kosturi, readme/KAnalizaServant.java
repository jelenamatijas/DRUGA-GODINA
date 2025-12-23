//implementiranje servanta
import kriptoanaliza.*;
import kriptoanaliza.KAnalizaPOA;

public class KAnalizaServant extends KAnalizaPOA{
//implementiranje merode vratiCezarovuSifru iz inetrfejsa KAnaliza
  public String vratiCezarovuSifru(String otvoreniTekst){
    String cezar="";
    for(int i=0; i<otvoreniTekst.length(); i++){
      cezar+=(char)(otvoreniTekst.charAt(i)+3);
    }
    
    return cezar;
  }
}
