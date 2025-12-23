import java.util.Comparator;

public class PoredjenjePartnera implements Comparator<Partner> {

 @Override
 public int compare(Partner arg0, Partner arg1) {
  int is=0;
  if(arg0.getNazivKompanije().charAt(0)>arg1.getNazivKompanije().charAt(0))
  is=1;
  else if(arg0.getNazivKompanije().charAt(0)<arg1.getNazivKompanije().charAt(0))
  is=-1;
  else
   is=0;
  return is;
 }

}
