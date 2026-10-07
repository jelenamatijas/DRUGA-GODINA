import java.util.Comparator;

public class PoredjenjeZaTopPet implements Comparator<ZaTopPet> {

 @Override
 public int compare(ZaTopPet arg0, ZaTopPet arg1) {
  int is=0;
  if(arg0.getBrojKompanija()>arg1.brojKompanija)
   is=-1;
  else if (arg0.getBrojKompanija()>arg1.brojKompanija)
   is=1;
  else
   is=0;
  return is;
 }

}
