public class ZaTopPet {

 public String grad;
 public int brojKompanija;

 public ZaTopPet(String grad, int brojKompanija) {
  super();
  this.grad = grad;
  this.brojKompanija = brojKompanija;
 }

 public String getGrad() {
  return grad;
 }

 public void setGrad(String grad) {
  this.grad = grad;
 }

 public int getBrojKompanija() {
  return brojKompanija;
 }

 public void setBrojKompanija(int brojKompanija) {
  this.brojKompanija = brojKompanija;
 }

 @Override
 public String toString() {
  return "[grad=" + grad + ", brojKompanija=" + brojKompanija
    + "]";
 }
 
 

}
