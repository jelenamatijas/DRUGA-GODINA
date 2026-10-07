import java.util.Scanner;
import java.lang.Math;
import java.lang.List;
import java.lang.ArrayList;


public class IstorijaIscrtavanja
{
  private List<List<? extends Oblik>> iscrtavanja;
  
  public IstorijaIscrtavanja ()
  {
    iscrtavanja = new ArrayList<List<? extends Oblik>>();
  }
  
  public IstorijaIscrtavanja (ArrayList<List<? extends Oblik>> m)
  {
    this.iscrtavanja = m;
  }
  
  public void dodajUListuIscrtavanja(List<? extends Oblik> lista)
  {
    iscrtavanja.add(lista);
    for(Oblik o : lista) o.iscrtaj();
  }
  
  public void ispisIscrtavanja()
  {
    